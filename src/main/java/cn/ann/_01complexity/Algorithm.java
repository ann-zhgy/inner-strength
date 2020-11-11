package cn.ann._01complexity;

/**
 * Description：什么是算法
 * <p>
 * Date: 2020-8-29 13:40
 *
 * @author 88475
 * @version v1.0
 */
public class Algorithm {
    // 计算 a 和 b 的和
    public static int plus(int a, int b) {
        return a + b;
    }

    // 计算 1 + 2 + 3 + 4 + ... + n 的和
    public static int sum(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("53 + 65 = " + plus(53, 65));
        System.out.println("1 ~ 20 sum = " + sum(20));
    }
}
