#include <stdio.h>
#include <limits.h>

int reverse(int x)
{
	int ret = 0;
	int tail_num = 0;
	while(x != 0)
	{
		if(ret < -214748364 || ret > 214748364)//若反转后的部分数大于INT_MAX/10或小于INT_MIN/10
			return 0;
		tail_num = x % 10;
		x /= 10;
		ret = ret * 10 + tail_num;//反转后的数
	}
	return ret;
}


int main()
{
	int num = 2112214112;
	int ret = reverse(num);
	printf("reverse(%d) is %d\n",num,ret);
	return 0;
}
