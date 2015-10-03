SRCS=xList.c
OBJS=${SRCS:.c=.o}
BINS=xList_test
CFLAGS=-g -Wall
CC=gcc
DIFF=diff
DIFFFLAGS=-strip-trailing-cr

# Default target
all: clean unit_tests

# Utility targets
clean:
	- rm *.o
	- rm ${BINS}
	- rm *_output
	- rm -rf *.dSYM

clobber: clean
	- rm *~
	- rm Makefile.bak

depends:
	makedepend -Y *.c

todos:
	grep "TODO" *.c *.h

# Main targets

# Main target tests

# Unit test targets
unit_tests: xList_test
	@ echo "Testing xList."
	- ./xList_test > xList_output 2>&1
	- diff xList_output xList_exemplar

xList_test: xList_test.c xList.c
	${CC} ${CFLAGS} -o xList_test xList_test.c xList.c

# Dependencies created by makedepend

# DO NOT DELETE

xList.o: xList.h
xList_test.o: xList.h
