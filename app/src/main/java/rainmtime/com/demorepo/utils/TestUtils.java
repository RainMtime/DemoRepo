package rainmtime.com.demorepo.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

                if (j < i || j > 6 * i) {
                    a[j] = 0;
                } else {
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
//
//    public static void pre(@NonNull TreeNode root) {
//        Log.i("chunyu-test", "" + root.value);
//        if (root.left != null) {
//            pre(root.left);
//        }
//
//        if (root.right != null) {
//            pre(root.right);
//        }
//    }

    public static void pre(@NonNull TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode item = stack.pop();
            Log.i("chunyu-test", item.value);

            if (item.right != null) {
                stack.push(item.right);
            }

            if (item.left != null) {
                stack.push(item.left);
            }
        }
    }

    public static class TreeNode {

        TreeNode left;

        TreeNode right;

        String value;

        public TreeNode(String value) {
            this.value = value;
        }
    }


    public static void maopao(int n) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void chaRuSort() {
        int[] a = new int[N];

        for (int i = 1; i < N; i++) {
            int key = a[i];
            int j = i - 1;
            while ((j > 0) && (key < a[j])) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }


    public static ArrayList<Integer> quicksort(List<Integer> a, int length, int left, int right) {
        int value = a.get((left + right) / 2);

        List<Integer> subleftList = new ArrayList<>();
        List<Integer> subRightList = new ArrayList<>();

        for (Integer item : a) {
            if (item < value) {
                subleftList.add(item);
                subRightList.add(item);
            }
        }

//        return  concat(subleftList,value,subRightList);

        return null;
    }


    //归并排序
    public static void mergeSort(int[] arr, int[] res, int start, int end) {
        if (start > end) {
            return;
        }
        int len = end - start;
        int mid = len / 2 + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        int k = start;
        mergeSort(arr, res, start1, end1);
        while ((start1 <= end1) && (start2 <= end2)) {
            res[k++] = arr[start1] > arr[start2] ? res[start1++] : res[start2++];
        }
        while (start1 <= end1) {
            res[k++] = arr[start1++];
        }

        while (start2 <= end2) {
            res[k++] = arr[start2++];
        }

        for (k = start; k <= end; k++) {
            arr[k] = res[k];
        }

    }

    //二分查找

    public static int biSearch(int[] arr, int start, int end, int value) {

        int mid = (end + start) / 2;
        if (value == arr[mid]) {
            return mid;
        }
        if (value < arr[mid]) {
            biSearch(arr, start, mid, value);
        } else {
            biSearch(arr, mid + 1, end, value);
        }

        return -1;
    }


    //   选择排序
    public static void selectSort(int[] arr, int len, int[] res) {
        for (int i = 0; i < len; i++) {
            int value = arr[i];
            for (int j = i + 1; j < len; j++) {
                if (value > arr[j]) {
                    value = arr[j];
                }
            }
            res[i] = value;
        }
    }

    //希尔排序 是插入排序的一个增加版本
    public static void shellSort(int[] arr, int len) {
        for (int i = 1; i < len; i++) {
            int value = arr[i];

            int j = i;
            while ((j > 0) && arr[j] < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j + 1] = value;
        }
    }

    //堆排序
    public void sort(int[] arr) {

        int len = arr.length - 1;
        int benginIndex = (len - 1) / 2;

    }


    public int fab(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (n == 3) {
            return 1 + 2 + 1;
        }
        return fab(n - 1) + fab(n - 2) + fab(n - 3);
    }


    public int fab2(int n) {
        int f1 = 1;
        int f2 = 1;
        int f3 = 0;
        int i = 3;
        while (i <= n) {
            f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
            i++;
        }
        return f3;
    }

    /**
     * @return 约瑟夫环，问题
     */
    public static int yuesefu(int n, int m) {
        int last = 0;
        if (n < 1 || m < 1) {
            return -1;
        }

        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    

}

