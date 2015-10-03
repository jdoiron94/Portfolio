#include "xScheduler.h"

#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include "xList.h"
#include "xProcess.h"
#include "xSystem.h"

/*
 * This scheduler is  incredibly crude.  It just changes  the state of
 * the  process when  events  happen and  keeps  a list  of the  ready
 * processes.   When a scheduling  event (io_blocking  or timer_event)
 * happens it puts the running process at the back of the queue.
 *
 */

static int quantum;

/* All the processes except the running one */
static xList_Ptr queue;

/* The running process */
static xProcess_PCB_ptr current;

/* Pick the process that will be executed next */
static void select(void)
{
  do {
    if (xProcess_terminated == current->state) {
      /* If current process is terminated, destroy it */
      xProcess_destroy(current);
    } else {
      /* Send the current process to the back of the line */
      current->state = xProcess_ready;
      xList_insert(queue, xList_last(queue), current);
    }

    /* The next process in line becomes the current process */
    current = (xProcess_PCB_ptr) xList_data(xList_first(queue));
    xList_remove(queue, NULL);

    /* If that process isn't ready go around again. */
  } while (xProcess_ready != current->state);

  current->state = xProcess_running;
  xSystem_set_timer(xSystem_time() + quantum);
  xSystem_dispatch(current);
}

void xScheduler_initialise(int raw_quantum)
{
  queue = xList_create();
  /* There needs to be at least one running process */
  /* So we create the idle process with number 0 and lowest priority */
  current = xProcess_create(0);
  current->state = xProcess_running;

  quantum = raw_quantum;
}

void xScheduler_finalise(void)
{
  /* Destroy the PCBs for all processes running when we're finalised. */
  while (NULL != xList_first(queue)) {
    current = (xProcess_PCB_ptr) xList_data(xList_first(queue));
    current->state = xProcess_terminated;
    xList_remove(queue, NULL);
    xProcess_destroy(current);
  }

  /* Destroy the resources we created for our scheduler */
  xList_destroy(queue);
}

void xScheduler_process_start(xProcess_PCB_ptr process)
{
  char buffer[1000];

  /* Illustrate the use of xSystem_kernel_message */
  snprintf(buffer, 1000, "Setting Process %d to Ready", process->number);
  xSystem_kernel_message(buffer);

  /* Mark the process as ready and stick it at the end of the queue */
  process->state = xProcess_ready;
  xList_insert(queue, xList_last(queue), process);
}

void xScheduler_process_end(void)
{
  if (0 != current->number) { /* Don't allow the idle process to terminate */
    /* Mark the process as terminated */
    current->state = xProcess_terminated;
  }

  /* Pick another process to run */
  select();
}

void xScheduler_io_start(void)
{
  if (0 != current->number) { /* Don't allow the idle process to block */
    /* Mark the process as waiting for blocking I/O to complete */
    current->state = xProcess_waiting;
  }

  /* Pick another process to run */
  select();
}

void xScheduler_io_end(xProcess_PCB_ptr process)
{
  /* Mark the process as now ready */
  process->state = xProcess_ready;
}

void xScheduler_timer_event(void)
{
  /* Just pick another process to run */
  select();
}
