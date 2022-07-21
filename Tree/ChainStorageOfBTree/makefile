main.out : main.o BiTree.o 
	gcc -g -o $@ $^
main.o :main.c BiTree.h
	gcc -g -c -o $@ $<
BiTree.o : BiTree.c	BiTree.h
	gcc -g -c -o $@ $<
.PHONY:clean
clean:
	rm *.o main.out		
