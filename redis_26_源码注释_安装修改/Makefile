# Top level makefile, the real shit is at src/Makefile

default: all

.DEFAULT:
	cd src && $(MAKE) $@

install:
	cd src && $(MAKE) $@ && rm -f *.o

clean:
	cd src && make clean

.PHONY: install clean
