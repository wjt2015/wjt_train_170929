STD=
WARN= -Wall
DEBUG= -g3 -ggdb3
R_CFLAGS= $(STD) $(WARN)  $(DEBUG) $(CFLAGS)
R_LDFLAGS= $(LDFLAGS)


R_CC=$(CC) $(R_CFLAGS)
R_LD=$(CC) $(R_LDFLAGS)

linenoise.o: linenoise.h linenoise.c

linenoise_example: linenoise.o example.o
	$(R_LD) -o $@ $^

.c.o:
	$(R_CC) -c $<

clean:
	rm -f *.o *.a
	
.PHONY:all static clean deps
CC=gcc
OBJ=linenoise.o
STLIBNAME=liblinenoise.a
STLIB_MAKE_CMD=ar crs

all:deps static $(OBJ)

%.o:%.c
	$(CC) -c -g3 -ggdb3 -rdynamic $^

static:$(STLIBNAME)

$(STLIBNAME):$(OBJ)
	$(STLIB_MAKE_CMD) $@ $^
