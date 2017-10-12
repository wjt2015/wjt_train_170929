
WARN= -Wall
CFLAGS=-g3 -ggdb3
DEBUG= -g3 -ggdb3

R_CFLAGS= $(WARN) $(DEBUG) $(CFLAGS)
R_LDFLAGS= $(LDFLAGS)


R_CC=$(CC) $(R_CFLAGS)
R_LD=$(CC) $(R_LDFLAGS)

linenoise.o: linenoise.h linenoise.c

linenoise_example: linenoise.o example.o
	$(R_LD) -o $@ $^

.c.o:
	$(R_CC) -c $<

clean:
	rm -f linenoise_example *.o
