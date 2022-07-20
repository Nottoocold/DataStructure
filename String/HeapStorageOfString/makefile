main.out : HString.o main.o
	cc -g -o $@ $^
main.o:HString.h
HString.o:HString.h
.PHONY:clean
clean:
	rm *.o main.out	
