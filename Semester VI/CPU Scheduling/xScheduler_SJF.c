#include "xScheduler.h"
#include "xList.h"
#include "xProcess.h"
#include "xSystem.h"

#include <assert.h>
#include <stdlib.h>

static int quantum;
static int previousBurst;
static int currentBurst;
static int futureBurst;

static xList_Ptr queue;
static xProcess_PCB_ptr currentProcess;

/**
 * Method which determines which process should run next, from the queue, based on the shortest job,
 * which is specified by the lowest burst, when CPU is available and when the process is ready to run.
 * Moves the longer jobs to the end of the queue and starts the ready, shortest job, setting its timer
 * with it.
*/
static void select(void)
{
  previousBurst = currentBurst;
  currentBurst = 0.5f * futureBurst + (1 - 0.5f) * futureBurst + previousBurst;
  do {
    if (currentProcess -> state == xProcess_terminated) {
      xProcess_destroy(current);
    }
    if(quantum >= burst) {
      currentProcess -> state = xProcess_ready;
      xList_insert(queue, xList_last(queue), current);
    } else {
      currentProcess -> state = xProcess_ready;
      xList_insert(queue, xList_last(queue), current);
    }
    currentProcess = (xProcess_PCB_ptr) xList_data(xList_first(queue));
    xList_remove(queue, NULL);
  } while (currentProcess -> state != xProcess_ready);
  currentProcess -> state = xProcess_running;
  xSystem_set_timer(xSystem_time() + quantum);
  xSystem_dispatch(current);
}

/**
 * Method which initializes the queue to an empty list and then adds a lowest priority method.
*/
void xScheduler_initialise(int raw_quantum)
{
  queue = xList_create();
  currentProcess = xProcess_create(0);
  currentProcess -> state = xProcess_running;
  currentProcess -> burst = raw_quantum;
  quantum = raw_quantum;
  previousBurst = quantum;
}

/**
 * Method which destroys all processes within and the queue itself.
*/
void xScheduler_finalise(void)
{
  while (xList_first(queue) != NULL) {
    currentProcess = (xProcess_PCB_ptr) xList_data(xList_first(queue));
    currentProcess -> state = xProcess_terminated;
    xList_remove(queue, NULL);
    xProcess_destroy(currentProcess);
  }
  xList_destroy(queue);
}

/**
 * Method which adds a process to the queue and assigns it to being ready.
*/
void xScheduler_process_start(xProcess_PCB_ptr process)
{
  char buffer[1000];
  snprintf(buffer, 1000, "Setting process %d to ready", process -> number);
  xSystem_kernel_message(buffer);
  process -> state = xProcess_ready;
  xList_insert(queue, xList_last(queue), process);
}

/**
 * Method which handles a process's end, by setting its state to terminated.
*/
void xScheduler_process_end(void)
{
  if (currentProcess -> number != 0) {
    currentProcess -> state = xProcess_terminated;
  }
  futureBurst = xSystem_time();
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
