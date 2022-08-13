/*
�����������Լ��������Ʊ������ʱ������ƣ�ÿ�����Ƕ�����ѡ����۹�Ʊ���
��ô�������ڵ� i �죬�������Ҫ�ڽ�������Ʊ����ô������׬����Ǯ�أ�

��Ȼ��������������������Ʊ�����ǿ϶����룺�����������ʷ��͵���Ĺ�Ʊ�ͺ��ˣ�
̫���ˣ�����Ŀ�У�����ֻҪ��һ��������¼һ����ʷ��ͼ۸� minprice�����ǾͿ��Լ����Լ��Ĺ�Ʊ����������ġ�
��ô�����ڵ� i ��������Ʊ�ܵõ���������� prices[i] - minprice��

��ˣ�����ֻ��Ҫ�����۸�����һ�飬��¼��ʷ��͵㣬Ȼ����ÿһ�쿼����ôһ�����⣺�����������ʷ��͵�����ģ�
��ô�ҽ���������׬����Ǯ������������������֮ʱ�����Ǿ͵õ�����õĴ𰸡�

*/

#include <stdio.h>

int maxProfit(int* prices, int pricesSize)
{
	int min_price = prices[0];
	int max_profit = 0;
	for (int i = 0; i < pricesSize; i++)
	{
		if (min_price > prices[i])
		{
			min_price = prices[i];
		}
		else if (prices[i]-min_price>max_profit)
		{
			max_profit = prices[i] - min_price;
		}
	}
	return max_profit;
}

int main()
{
	int prices[] = { 7,1,5,3,6,4 };
	int len = sizeof(prices) / sizeof(int);
	maxProfit(prices, len);
	return 0;
}