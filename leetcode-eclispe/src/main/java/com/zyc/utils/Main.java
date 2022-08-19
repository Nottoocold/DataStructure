package com.zyc.utils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter a num: ");
        int num = sc.nextInt();
        int count = 0;//二进制表示num中1的个数
        while (num != 0) {
            count++;
//            num = num & (num - 1);
            num &= num - 1;
        }
        System.out.println("count is " + count);
        System.out.println(4>>>3);
    }
}
