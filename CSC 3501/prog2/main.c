#include <stdio.h>
#include <assert.h>

int prog2(int i, int j, int *k, int a[5], int *l);

int main() {
	int k = 6;
	int l = 0i, res;
	int a[5] = {7, 0, 8, 0, 3};

	res = prog2(6,9,&k,a,&l);
	if(res != 9-6) {
		printf("return value should be=%d; got=%d\n", 9-6, res);
      		assert(0);
	}
	if(k != 18) {
		printf("k*3: expected=18, got=%d\n", k);
         	assert(0);
	}
	if(l != 18) {
                printf("array sum: expected=18, got=%d\n", l);
                assert(0);
        }
			
	printf("j-i= %d\n", res);
	printf("k*3= %d\n", k);
	printf("array sum= %d\n\n", l);

	a[1] = l;
	res = prog2(11,17,&k,a,&l);
	if(res != 17-11) {
                printf("return value should be=%d; got=%d\n", 17-11, res);
                assert(0);
        }
        if(k != 18*3) {
                printf("k*3*3: expected=54, got=%d\n", k);
                assert(0);
        }
        if(l != 18*2) {
                printf("array sum 2: expected=36, got=%d\n", l);
                assert(0);
        }
	
	printf("j-i= %d\n", res);
        printf("k*3*3= %d\n", k);
        printf("array sum 2= %d\n\n", l);
	return 0;
}
