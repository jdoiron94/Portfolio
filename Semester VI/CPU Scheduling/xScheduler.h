#ifndef _XSCHEDULER_H_
#define _XSCHEDULER_H_

#include "xProcess.h"

void xScheduler_initialise(int quantum);
void xScheduler_finalise(void);

void xScheduler_process_start(xProcess_PCB_ptr process);
void xScheduler_process_end(void);

void xScheduler_io_start(void);
void xScheduler_io_end(xProcess_PCB_ptr process);
void xScheduler_timer_event(void);

#endif
