package cn.ann._01complexity;

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
    private static final String INVALID_ARGUMENT = "无效参数";

    public static long fibonacciRecurse(int n) {
        if (n < 0){
            throw new IllegalArgumentException(INVALID_ARGUMENT);
        }
        if (n <= 1){
            return n;
        }
        return fibonacciRecurse(n - 1) + fibonacciRecurse(n - 2);
    }

    public static long fibonacciFor(int n) {
        if (n < 0) throw new IllegalArgumentException(INVALID_ARGUMENT);
        if (n <= 1) return n;
        long first = 0, second = 1;
        for (int i = 0; i < n - 1; i++) {
            long sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /** 数学公式 */
    public static long fibonacciFormula(int n) {
        if (n < 0){
            throw new RuntimeException(INVALID_ARGUMENT);
        }
        if (n <= 1){
            return n;
        }
        double v = Math.sqrt(5);
        return (long) ((Math.pow((1 + v) / 2, n) - Math.pow((1 - v) / 2, n)) / v);
    }

    public static void main(String[] args) {
        int num = 50;
        Times.test("fibonacciFor", () -> System.out.println(fibonacciFor(num)));
        Times.test("fibonacciFormula", () -> System.out.println(fibonacciFormula(num)));
        Times.test("fibonacciRecurse", () -> System.out.println(fibonacciRecurse(num)));
    }
}
