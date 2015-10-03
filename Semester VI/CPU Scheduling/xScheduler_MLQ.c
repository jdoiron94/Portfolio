#include "xScheduler.h"
#include "xList.h"
#include "xProcess.h"
#include "xSystem.h"

#include <assert.h>
#include <stdlib.h>

static int quantum;

static xList_Ptr highestPriority;
static xList_Ptr lowestPriority;
static xProcess_PCB_ptr currentProcess;

/**
 * Method which destroys a given queue's processes and the queue itself
*/
static void destroyList(xList_Ptr list)
{
  while (xList_first(list) != NULL) {
    currentProcess = (xProcess_PCB_ptr) xList_data(xList_first(list));
    currentProcess -> state = xProcess_terminated;
    xList_remove(list, NULL);
    xProcess_destroy(currentProcess);
  }
  xList_destroy(list);
}

/**
 * Method which handles the first come, first serve side
*/
static void fcfs()
{
  do {
    if (currentProcess -> state == xProcess_terminated) {
      xProcess_destroy(currentProcess);
    } else {
      currentProcess -> state = xProcess_ready;
      xList_insert(lowestPriority, xList_last(lowestPriority), currentProcess);
    }
    currentProcess = (xProcess_PCB_ptr) xList_data(xList_first(lowestPriority));
    xList_remove(lowestPriority, NULL);
  } while (currentProcess -> state != xProcess_ready);
}

/**
 * Method which handles the multiple queues by determining which process should run. Terminated processes
 * are destroyed and the current process moves to the lower priority queue. The next available, highest
 * priority process is then set as the current process.
*/
static void mlq()
{
  do {
    if (currentProcess -> state == xProcess_terminated) {
      xProcess_destroy(currentProcess);
    } else {
      currentProcess -> state = xProcess_ready;
      currentProcess -> priority = 0;
      xList_insert(lowestPriority, xList_last(lowestPriority), currentProcess);
    }
    currentProcess = (xProcess_PCB_ptr) xList_data(xList_first(highestPriority));
    xList_remove(highestPriority, NULL);
  } while (currentProcess -> state != xProcess_ready);
}

/**
 * Method which dispatches the next process to run, depending on if the fcfs or mlq method should be used.
 * FCFS is used when there is no process to run in the highest priority queue, but there are processes
 * in the lower priority queue, in which case, the first process is run. MLQ is described on its method.
*/
static void select()
{
  if (xList_first(highestPriority) == NULL && xList_first(lowestPriority) != NULL) {
    fcfs();
  } else {
    mlq();
  }
  currentProcess -> state = xProcess_running;
  xSystem_set_timer(xSystem_time() + quantum);
  xSystem_dispatch(currentProcess);
}

/**
 * Method which initializes the queue to an empty list and then adds a lowest priority method.
*/
void xScheduler_initialise(int raw_quantum)
{
  highestPriority = xList_create();
  lowestPriority = xList_create();
  currentProcess = xProcess_create(0);
  currentProcess -> state = xProcess_running;
  quantum = raw_quantum;
  xList_insert(lowestPriority, xList_last(lowestPriority), process);
}

/**
 * Method which destroys all processes within and the queues themselves.
*/
void xScheduler_finalise(void)
{
  destroyList(highestPriority);
  destroyList(lowestPriority);
}

/**
 * Method which adds a process to the queue and assigns it to being ready.
*/
void xScheduler_process_start(xProcess_PCB_ptr process)
{
  char buffer[1000];
  snprintf(buffer, 1000, "Setting Process %d to Ready", process -> number);
  xSystem_kernel_message(buffer);
  process -> state = xProcess_ready;
  process -> priority = 1;
  xList_insert(highestPriority, xList_last(highestPriority), process);
}

/**
 * Method which handles a process's end, by setting its state to terminated.
*/
void xScheduler_process_end(void)
{
  if (currentProcess -> number != 0) {
    currentProcess -> state = xProcess_terminated;
  }
  select();
}

/**
 * Method which sets the currently running process's state to the waiting state and then selects a new
 * process to run.
*/
void xScheduler_io_start(void)
{
  if (currentProcess -> number != 0) {
    currentProcess -> state = xProcess_waiting;
  }
  select();
}

/**
 * Method which assigns the given process's state to being ready.
*/
void xScheduler_io_end(xProcess_PCB_ptr process)
{
  process -> state = xProcess_ready;
}

/**
 * Method which occurs when the timer runs out, selecting a new process to run.
*/
void xScheduler_timer_event(void)
{
  select();
}
