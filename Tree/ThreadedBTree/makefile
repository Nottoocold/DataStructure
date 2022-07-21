main.out : main.o ThreadTree.o SQueue.o
	gcc -g -o $@ $^
main.o : main.c ThreadTree.h
	gcc -g -c -o $@ $<
ThreadTree.o : ThreadTree.c ThreadTree.h SQueue.h
	gcc -g -c -o $@ $<
SQueue.o : SQueue.c SQueue.h
	gcc -g -c -o $@ $<

.PHONY:clean
clean:
	rm *.o main.out
