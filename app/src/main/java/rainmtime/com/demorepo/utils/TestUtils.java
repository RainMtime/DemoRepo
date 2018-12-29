package rainmtime.com.demorepo.utils;

import android.util.Log;

/**
 * Created by chunyu on 2018/12/28 7:37 PM.
 * Email: 746431278@qq.com
 */
public final class TestUtils {

    //骰子数目,为了看起来方便这里N假设一定大于等于2，不再做N<=1的情况判断
    private final static int N = 3;

    public static void hahaha() {
        //为了看起来方便忽略掉a[0]
        int[] a = new int[6 * N + 1];

        a[1] = 1;
        a[2] = 1;
        a[3] = 1;
        a[4] = 1;
        a[5] = 1;
        a[6] = 1;

        int[] b = new int[6 * N + 1];
        for (int i = 2; i <= N; i++) {

            for (int j = i; j <= 6 * i; j++) {
                if (j - 1 >= 1) {
                    b[j] += a[j - 1];
                }

                if (j - 2 >= 1) {
                    b[j] += a[j - 2];
                }

                if (j - 3 >= 1) {
                    b[j] += a[j - 3];
                }

                if (j - 4 >= 1) {
                    b[j] += a[j - 4];
                }

                if (j - 5 >= 1) {
                    b[j] += a[j - 5];
                }

                if (j - 6 >= 1) {
                    b[j] += a[j - 6];
                }

            }
            for (int j = 1; j <= 6 * i; j++) {

                if (j<i || j>6*i){
                    a[j] = 0;
                }else {
                    a[j] = b[j];
                    b[j] = 0;
                }
            }
        }

        int sum = 0;
        for (int c = N; c <= 6 * N; c++) {
            sum += a[c];
        }

        Log.i("chunyu-test", "当有N=" + N + "颗骰子的时候，结果如下：\n");
        for (int i = N; i <= 6 * N; i++) {
            Log.i("chunyu-test", "和=" + i + "概率为" + a[i] + "/" + sum + "\n");
        }
    }
}

