main.out : main.o List.o Stack.o LinkQueue.o CQueue.o
	gcc -g -o $@ $^
main.o :main.c List.h Stack.h LinkQueue.h CQueue.h
	gcc -g -c -o $@ $<
List.o :List.c List.h
	gcc -g -c -o $@ $<
Stack.o :Stack.c Stack.h
	gcc -g -c -o $@ $<
LinkQueue.o :LinkQueue.c LinkQueue.h
	gcc -g -c -o $@ $<
CQueue.o :CQueue.c CQueue.h
	gcc -g -c -o $@ $<
.PHONY:clean
clean:
	rm main.out *.o
