#include <iostream>
#include <vector>
#include <time.h>
using namespace std;

//全局变量
vector<vector<int>> ret;
vector<int> item;

//回溯算法，递归，本质上就是枚举
void backTracking(int n, int k, int startIndex)
{
	if(item.size() == k)//递归终止条件
	{
		ret.push_back(item);//处理结果
		return;
	}
	for(int i = startIndex; i <= n; ++i)
	{
		item.push_back(i);//处理节点
		backTracking(n,k,i+1);//递归
		item.pop_back();//删除刚才处理的节点
	}	
}

vector<vector<int>> combine(int n, int k)
{
	backTracking(n,k,1);//start in 1.
	return ret;	
}

int main()
{
	clock_t s = clock();
	combine(20,5);
	clock_t e = clock();
	cout << "program running time is " << e-s << endl;
	//for(vector<int> nums : ret)
	//{
	//	for(int n : nums)
	//		cout << n << " ";
	//	cout << endl;	
	//}
	return 0;
}	
