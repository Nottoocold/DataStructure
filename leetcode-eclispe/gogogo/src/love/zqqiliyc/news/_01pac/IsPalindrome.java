package love.zqqiliyc.news._01pac;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zyc
 * @date 3月 02 - 2023 星期四 20:29
 */
public class IsPalindrome {

    /*
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 例如，121 是回文，而 123 不是。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/palindrome-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    // 此法没有考虑到x很大时，导致反转后可能溢出的问题
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x <= 9) {
            return true;
        }
        int y = x;
        int count = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        while (x != 0) {
            ++count;
            deque.addFirst(x % 10);
            x /= 10;
        }
        int r = 0;
        while (!deque.isEmpty()) {
            Integer i = deque.removeLast();
            r = (int) (r + i * Math.pow(10, --count));
        }
        return y == r;
    }

    public boolean isPalindrome0(int x) {
         // x是负数或x的个数是0 都不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int y = 0;
        // 为了防止溢出，只反转一半数字
        while (!(x < y)){
            y = y * 10 + x % 10;
            x /= 10;
        }
        return x == y || (x == y / 10);
    }

    public static void main(String[] args) {
        IsPalindrome instance = new IsPalindrome();
        boolean r = instance.isPalindrome0(12321);
        System.out.println(r);
    }

}
