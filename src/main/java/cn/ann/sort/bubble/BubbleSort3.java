package cn.ann.sort.bubble;

import cn.ann.sort.Sort;

/**
 * Description：对部分有序的数组进行优化
 * <p>
 * Date: 2020-11-13 11:20
 *
 * @author 88475
 * @version v1.0
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int i = array.length; i > 0; i--) {
            int sortedIndex = 1;
            for (int begin = 0; begin < i - 1; begin++) {
                if (compare(begin, begin + 1) > 0) {
                    swap(begin, begin + 1);
                    sortedIndex = begin + 2;
                }
            }
            i = sortedIndex;
        }
    }
}
