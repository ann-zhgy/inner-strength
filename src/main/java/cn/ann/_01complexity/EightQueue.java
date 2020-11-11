package cn.ann._01complexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description：八皇后
 * <p>
 * Date: 2020-8-28 15:38
 *
 * @author 88475
 * @version v1.0
 */
public class EightQueue {
    // 放置皇后的数量
    private final int MAX = 8;
    // 符合规则的结果
    private final int[] result = new int[MAX];
    // 符合规则的结果集
    private final List<int[]> results = new ArrayList<>();

    /**
     * 放置皇后
     */
    public void placeQueue() {
        placeQueue(0);
    }

    /**
     * 放置第n个皇后.
     *
     * @param n 皇后索引（从0开始）
     */
    private void placeQueue(int n) {
        if (MAX != n) {
            for (int i = 0; i < MAX; i++) {
                result[n] = i;
                if (judge(n)) {
                    placeQueue(n + 1);
                }
            }
        } else {
            results.add(result.clone());
        }
    }

    /**
     * 检测摆放的第n个皇后是否符合规则.
     *
     * @param n 第n个皇后
     * @return true|false
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // result[i] == result[n] 判断是否为同一列
            // Math.abs(n - i) == Math.abs(result[n] - result[i]) 判断是否为同一斜线
            if (result[i] == result[n] || Math.abs(n - i) == Math.abs(result[n] - result[i])) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> getResults() {
        return results;
    }

    public static void main(String[] args) {
        EightQueue queue = new EightQueue();
        queue.placeQueue();
        List<int[]> results = queue.getResults();
        results.forEach(result -> System.out.println(Arrays.toString(result)));
        System.out.println(results.size());
    }

}
