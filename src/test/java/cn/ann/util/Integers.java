package cn.ann.util;

import java.util.Arrays;
import java.util.Random;

/**
 * Description：数组工具类
 * <p>
 * Date: 2020-11-12 16:33
 *
 * @author 88475
 * @version v1.0
 */
public class Integers {
    private Integers() {
    }

    private static final String BAD_ARGS = "参数不合法";

    public static Integer[] random(int count, int min, int max) {
        if (count <= 0 || min > max) {
            throw new IllegalArgumentException(BAD_ARGS);
        }
        return new Random()
                .ints(min, max)
                .limit(count)
                .boxed()
                .toArray(Integer[]::new);
    }

    public static Integer[] headAscOrder(int min, int max, int disorderCount) {
        Integer[] array = ascOrder(min, max);
        if (disorderCount > array.length) return array;
        reverse(array, array.length - disorderCount, array.length);
        return array;
    }

    public static Integer[] tailAscOrder(int min, int max, int disorderCount) {
        Integer[] array = ascOrder(min, max);
        if (disorderCount > array.length) return array;
        reverse(array, 0, disorderCount);
        return array;
    }

    public static Integer[] ascOrder(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(BAD_ARGS);
        }
        Integer[] array = new Integer[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = min++;
        }
        return array;
    }

    public static Integer[] descOrder(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(BAD_ARGS);
        }
        Integer[] array = new Integer[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = max--;
        }
        return array;
    }

    public static Integer[] copy(Integer[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public static boolean isAscOrder(Integer[] array) {
        if (array == null || array.length == 0) return false;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) return false;
        }
        return true;
    }

    private static void reverse(Integer[] array, int begin, int end) {
        int count = (end - begin) >> 1;
        int sum = begin + end - 1;
        for (int i = begin; i < begin + count; i++) {
            int j = sum - i;
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}
