#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> ret;
vector<int> temp;

void backTracking(vector<int> &nums,vector<bool> &used)
{
	if(temp.size() == nums.size())//temp集合中元素个数满足条件
	{
		ret.push_back(temp);
		return;
	}
	for(int i = 0; i < nums.size(); ++i)//遍历nums集合
	{
		if(!used[i])//如果没有被访问过
		{
			temp.push_back(nums[i]);
			used[i]=true;//标记
			backTracking(nums,used);//递归
			used[i]=false;//标记刚才访问过的元素
			temp.pop_back();//回溯，即删除刚才访问过的元素
		}
	}
}

vector<vector<int>> permute(vector<int> &nums)
{
	vector<bool> used{false};
	backTracking(nums,used);
	return ret;
}

int main()
{
	vector<int> t = {1,2,3};
	permute(t);
	for(vector<int> temp: ret)
	{
		for(int n : temp)
			cout << n << " ";
		cout << endl;
	}
	return 0;
}
