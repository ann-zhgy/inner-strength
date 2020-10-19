package cn.ann.part01._01complexity;

import cn.ann.util.Times;

/**
 * Description：斐波那契数算法比较
 * <p>
 * Date: 2020-8-29 13:52
 *
 * @author 88475
 * @version v1.0
 */
public class Fibonacci {
    public static long fibonacciRecurse(int n) {
        if (n < 0) throw new RuntimeException("无效参数");
        if (n <= 1) return n;
        return fibonacciRecurse(n - 1) + fibonacciRecurse(n - 2);
    }

    public static long fibonacciFor(int n) {
        if (n < 0) throw new RuntimeException("无效参数");
        if (n <= 1) return n;
        long frist = 0, second = 1;
        for (int i = 0; i < n - 1; i++) {
            long sum = frist + second;
            frist = second;
            second = sum;
        }
        return second;
    }

    public static long fibonacciFormula(int n) {
        if (n < 0) throw new RuntimeException("无效参数");
        if (n <= 1) return n;
        double v = Math.sqrt(5);
        return (long) ((Math.pow((1 + v) / 2, n) - Math.pow((1 - v) / 2, n)) / v);
    }

    public static void main(String[] args) {
        Times.test("fibonacciFor", () -> System.out.println(fibonacciFor(50)));
        Times.test("fibonacciFormula", () -> System.out.println(fibonacciFormula(50)));
        Times.test("fibonacciRecurse", () -> System.out.println(fibonacciRecurse(50)));
    }
}
