# Top level makefile, the real shit is at src/Makefile

default: all

.DEFAULT:
	cd src && $(MAKE) $@

install:
	cd src && $(MAKE) $@


clean:
	cd src && make $@ && cd ../deps && make clean

.PHONY: install clean
