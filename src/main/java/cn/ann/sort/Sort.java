package cn.ann.sort;

import java.text.DecimalFormat;

/**
 * Description：Sort 接口定义 + 工具封装
 * <p>
 * Date: 2020-11-12 11:43
 *
 * @author 88475
 * @version v1.0
 */
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] array;
    private long compareCount;
    private long swapCount;
    private long time;
    private final DecimalFormat FORMAT = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        this.array = array;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    /**
     * 数组排序
     */
    protected abstract void sort();

    /**
     * 元素交换
     *
     * @param index1 index1
     * @param index2 index2
     */
    protected void swap(int index1, int index2) {
        swapCount++;
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    protected int compare(int index1, int index2) {
        compareCount++;
        return array[index1].compareTo(array[index2]);
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(compareCount);
        String swapCountStr = "交换：" + numberString(swapCount);
//        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
//                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    @Override
    public int compareTo(Sort<T> o) {
        return Long.compare(time, o.time);
    }

    private String numberString(long number) {
        if (number < 10000) {
            return "" + number;
        }

        if (number < 100000000) {
            return FORMAT.format(number / 10000.0) + "万";
        }
        return FORMAT.format(number / 100000000.0) + "亿";
    }

//    private boolean isStable() {
//        if (this instanceof RadixSort) {
//            return true;
//        }
//        if (this instanceof CountingSort) {
//            return true;
//        }
//        if (this instanceof ShellSort) {
//            return false;
//        }
//        if (this instanceof SelectionSort) {
//            return false;
//        }
//        Student[] students = new Student[20];
//        for (int i = 0; i < students.length; i++) {
//            students[i] = new Student(i * 10, 10);
//        }
//        sort((T[]) students);
//        for (int i = 1; i < students.length; i++) {
//            int score = students[i].score;
//            int prevScore = students[i - 1].score;
//            if (score != prevScore + 10) return false;
//        }
//        return true;
//    }
}
