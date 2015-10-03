/* Process states and process control block (PCB) fields are taken
 * from Silberschatz, Galvin and Gagne, "Operating Systems Concepts",
 * 6th edition, p.97
 */

#ifndef _XPROCESS_H_
#define _XPROCESS_H_

typedef enum {
  xProcess_new,
  xProcess_ready,
  xProcess_running,
  xProcess_waiting,
  xProcess_terminated
} xProcess_state;

typedef struct xProcess_PCB_* xProcess_PCB_ptr;

typedef struct xProcess_PCB_ {
  xProcess_state state;
  int number;
  long registers[33];
  void* files;
  void* page_table;
  /* Additional scheduler data goes here */
  int priority;
} xProcess_PCB;

void xProcess_initialise(void);
void xProcess_finalise(void);

xProcess_PCB_ptr xProcess_create(int priority);
void xProcess_destroy(xProcess_PCB_ptr process);

char* xProcess_state_name(xProcess_state state);

#endif
