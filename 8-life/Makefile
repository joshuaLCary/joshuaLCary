
CPPFLAGS=-I../lib -I/home/clausoa/include
CFLAGS=-Wall -Werror -pedantic -Ofast 
LDFLAGS=-L/home/clausoa/lib
LIBS= -llife -lm -lbsd


all: life

life: life.o 
	$(CC) $(CFLAGS) -o $@ $^  $(LIBS) -lm $(LDFLAGS)

life.o: life.c

check: all
	./life -nw

clean:
	@echo -n Cleaning...
	@rm  life *.o  *~ 2>/dev/null || true
	@echo done
