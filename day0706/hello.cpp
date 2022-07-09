#include "../include/f"

void test_input(void)
{
    int curVal = 0, val = 0;
    cout << "please input some nums:\n";
    if (cin >> curVal)
    {
        int count = 1;
        while (cin >> val)
        {
            if (curVal == val)
            {
                count++;
            }
            else
            {
                cout << curVal << " is read " << count << " times.\n";
                curVal = val;
                count = 1;
            }
        }
        cout << curVal << " is read " << count << " times.\n";
    }
}

int main()
{
    /*int nums[][4] = {{1, 2, 3, 4}, {2, 3, 1, 5}, {1, 3, 5, 6}};
    int row = sizeof(nums) / sizeof(nums[0]);
    int col = sizeof(nums[0]) / sizeof(nums[0][0]);
    printNums(&nums[0][0], row, col);
    cout << "\n";
    printNUms__(nums,row,col);
    cout << "\n";*/
    /*int sum = 0, value = 0;
    while (cin >> value)
    {
        sum += value;
    }
    cout << "Sum is " << sum << endl;*/
    // test_input();
    /*cout << "int is " << sizeof(int) << " bytes and short is " << sizeof(short) << " bytes." << endl;
    cout << "float is " << sizeof(float) << " bytes and double is " << sizeof(double) << " bytes." << endl;
    cout << "bool is " << sizeof(bool) << " bytes.\n";
    cout << "long is " << sizeof(long) << " bytes and long long is " << sizeof(long long) << " bytes.\n";*/

    // C++引用类型,引用为对象起了另外一个名字,引用类型引用(refers to)另外一种类型,通过将声明符写成 &d 的形式来定义引用类型,其中d是声明的变量名
    // int val = 1;
    // int &refVal = val; // refVal 指向 val , 将refVal和val绑定(bing)在一起
    // int &refVal1;//引用类型需要初始化
    // int &refVal3 = refVal;
    // cout << "val is " << val << " refVal is " << refVal << endl; // 1
    // refVal = 2;                                                  //相当于val=2
    // cout << "val is " << val << " refVal is " << refVal << endl; // 2
    // cout << refVal3;//2
    // int i = refVal;
    // cout << "\n" << i;//2
    // double d = 3.14;
    // int &dou = d;//类型不匹配
    // int *p = &val;
    // cout << refVal << " " << *p << endl; // 11
    // refVal = 2;
    // cout << refVal << " " << *p << endl; // 22
    // *p = 3;
    // cout << refVal << " " << *p << endl; // 33
    // int &rp = *p;
    // cout << "rp is " << rp << endl;
    // rp = 4;
    // cout << "rp is " << rp << " *p is " << *p << endl;
    // char *cp = 0;
    // char *cp1 = nullptr; // c++11新增关键字

    // int val = 1;
    // int *p;
    // int *&rp = p;        // rp是一个对int*指针的引用
    // rp = &val;           //等价于p=&val
    // cout << *rp << endl; //等价于*p
    // *rp = 2;             //等价于*p=2
    // cout << *rp << endl;

    int val = 1;
    //int val1 = 2;
    const int *const p = &val; // p一旦初始化就不能再更改地址，并且不能修改*p中的值，只能访问*p
    cout << *p << endl;
    //*p = 3;
    // p = &val1;
    val = 2; //本尊可以改
    cout << *p << endl;
    
    return 0;
}