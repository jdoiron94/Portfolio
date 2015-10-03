SRCS=main.c zalloc.c zalloc_simple.c zalloc_best.c zalloc_worst.c zalloc_quick.c
OBJS=${SRCS:.c=.o}
BINS=zalloc_test zalloc_simple_test zalloc_best_test zalloc_worst_test zalloc_quick_test
CC=gcc
CFLAGS=-g -Wall
DIFF=diff
DIFFFLAGS=--strip-trailing-cr

all: clean zalloc_simple_test zalloc_test zalloc_best_test zalloc_worst_test zalloc_quick_test test

clean:
	- rm zalloc_test_output
	- rm zalloc_simple_test_output
	- rm zalloc_best_test_output
	- rm zalloc_worst_test_output
	- rm zalloc_quick_test_output
	- rm *.o
	- rm ${OBJS}
	- rm ${BINS}
	- rm -rf *.dSYM

clobber: clean
	- rm *~

test: zalloc_test zalloc_simple_test
	- ./zalloc_test > zalloc_test_output 2>&1
	- ./zalloc_simple_test > zalloc_simple_test_output 2>&1
	- ./zalloc_best_test > zalloc_best_test_output 2>&1
	- ./zalloc_worst_test > zalloc_worst_test_output 2>&1
	- ./zalloc_quick_test > zalloc_quick_test_output 2>&1

zalloc_test: main.o zalloc.o
	${CC} ${CFLAGS} -o zalloc_test main.o zalloc.o

zalloc_simple_test: main.o zalloc_simple.o
	${CC} ${CFLAGS} -o zalloc_simple_test main.o zalloc_simple.o
	
zalloc_best_test: main.o zalloc_best.o
	${CC} ${CFLAGS} -o zalloc_best_test main.o zalloc_best.o

zalloc_worst_test: main.o zalloc_worst.o
	${CC} ${CFLAGS} -o zalloc_worst_test main.o zalloc_worst.o
	
zalloc_quick_test: main.o zalloc_quick.o
	${CC} ${CFLAGS} -o zalloc_quick_test main.o zalloc_quick.o