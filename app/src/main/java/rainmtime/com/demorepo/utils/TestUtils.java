package rainmtime.com.demorepo.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        mergeSort(arr, res, start2, end2);
        while ((start1 <= end1) && (start2 <= end2)) {
            res[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
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


    public static int binarysearch(int a[], int start, int end, int value) {
        if (start < end) {
            return -1;
        }
        int midIndex = (start + end) / 2;
        int midValue = a[(start + end) / 2];

        if (midValue == value) {
            return midIndex;
        } else if (midValue > value) {
            return binarysearch(a, start, midIndex, value);
        } else {
            return binarysearch(a, midIndex + 1, end, value);
        }
    }

    /**
     * 快速排序
     */
    public static void qsort(int[] a, int left, int right) {

        int pivot = partIndex(a, left, right);
        qsort(a, left, pivot - 1);
        qsort(a, pivot + 1, right);
    }

    public static int partIndex(int[] a, int left, int right) {

        int pivotValue = a[left];
        while (left < right) {
            while (pivotValue <= a[right] && left < right) {
                right--;
            }
            if (left < right) {
                a[left] = a[right];
                ++left;
            }

            while (pivotValue >= a[left] && left < right) {
                left++;
            }

            if (left < right) {
                a[right] = a[left];
                right--;
            }
        }
        a[left] = pivotValue;

        return left;
    }


    public static class ConsumerAndProducer {
        private static String Lock = "lock";

        private final static int capacity = 10;
        private int count = 0;


        public class ConSumer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i <= 5; i++) {

                    //sleep(3000)

                    synchronized (Lock) {
                        if (count <= 10) {
                            count++;
                        } else {
                            try {
                                Lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }

        public class Producer implements Runnable {
            @Override
            public void run() {
                //sleep(3000)

                synchronized (Lock) {
                    if (count <= 0) {
                        count--;
                    } else {
                        try {
                            Lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public static class SingleInstance {

        private static final SingleInstance mInstance = new SingleInstance();

        private SingleInstance() {
        }

//        public synchronized SingleInstance getInstance() {
//            if (mInstance == null) {
//                mInstance = new SingleInstance();
//            }
//            return mInstance;
//        }


        public static SingleInstance getmInstance() {
            return mInstance;
        }
    }


    public static class Server {

        public static void main() {
            try {
                ServerSocket serverSocket = new ServerSocket(10086);
                Socket socket = serverSocket.accept();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Server() {


        }
    }

    public static class Hanlder implements Runnable {

        public Socket socket;

        public Hanlder(Socket socket) {

            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class sumOfNumClass {
        int k = 0;
        private static final int N = 20;
        int[] res = new int[N];


        public void sumOfNum(int[] a, int n, int sum) {

            if (n <= 0 && sum <= 0) {
                return;
            }

            if (k >= 0) {
                if (sum == a[n - 1]) {
                    for (int i = 0; i < k; i++) {
                        //print a[i]
                    }
                    //print sum
                }
            }

            res[k++] = a[n - 1];
            sumOfNum(a, n - 1, sum - a[n - 1]);
            k--;
            sumOfNum(a, n - 1, sum);

        }


    }

    /**
     * @param a 待处理数组
     * @param n 数组数目
     * @return
     */
    public static int maxSum(int[] a, int n) {
        int currentSum = 0;
        int maxSum = a[0];

        for (int i = 0; i < n; i++) {
            currentSum = (a[i] > a[i] + currentSum) ? a[i] : a[i] + currentSum;
            maxSum = maxSum > currentSum ? maxSum : currentSum;
        }

        return maxSum;
    }

    //冒泡
    public static void maoPaoSort(int[] a) {
        if (a == null || a.length <= 0) {
            return;
        }


        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

    }

    //计数排序
    public static void countSort(int[] a) {
        if (a == null || a.length <= 0) {
            return;
        }

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            count = 0;

            for (int j = 0; j < a.length; j++) {
                if (a[i] < a[j]) {
                    count++;
                }
            }
            int temp = a[count];
            a[count] = a[i];
            a[i] = temp;
        }
    }

    //计数排序
    public static int[] mergeSort(int[] a, int[] b) {

        if (a == null || a.length <= 0) {
            return b;
        }

        if (b == null || b.length <= 0) {
            return a;
        }
        int aIndex = 0;
        int bIndex = 0;
        int[] result = new int[a.length + b.length];

        int realIndex = 0;
        while (aIndex < a.length && bIndex < b.length) {
            result[realIndex++] = a[aIndex] < b[bIndex] ? a[aIndex++] : b[bIndex++];
        }

        if (aIndex < a.length) {
            while (bIndex < b.length) {
                result[realIndex++] = b[bIndex++];
            }
        }

        if (bIndex < b.length) {
            while (aIndex < a.length) {
                result[realIndex++] = a[aIndex++];
            }
        }
        return result;
    }
    //快速排序

    public static void kuaisuSort(int[] a, int left, int right) {

        int pivot = pivotIndex(a, left, right);

        if (pivot + 1 < right) {
            kuaisuSort(a, pivot + 1, right);
        }

        if (left < pivot - 1) {
            kuaisuSort(a, left, pivot - 1);
        }


    }

    public static int pivotIndex(@NonNull int[] a, int left, int right) {

        int value = a[left];

        while (left < right) {

            while (value <= a[right] && left <= right) {
                right--;
            }

            if (left < right) {
                a[left] = a[right];
                left++;
            }

            while (value >= a[left] && left < right) {
                left++;
            }

            if (left < right) {
                a[right] = a[left];
                right--;
            }
        }
        a[left] = value;
        return left;

    }

    public static class Node {

        Node next;
        int value;
    }

    public static class Node2 {
        Node2 front;
        Node2 next;
        int value;
    }

    public static Node reverseLinedList(Node node) {
        if (node == null && node.next == null) {
            return node;
        }

        Node firstNode = node;
        Node secondNode = node.next;

        while (secondNode != null) {
            Node temp = secondNode.next;
            secondNode.next = firstNode;
            firstNode = secondNode;
            secondNode = temp;
        }
        return firstNode;
    }

    public static Node2 reverseLinedList(Node2 node2) {
        if (node2 == null && node2.next == null) {
            return node2;
        }

        Node2 firstNode = node2;

        while (firstNode != null) {
            if (firstNode.next == null) {
                break;
            }
        }

        Node2 frontNode = firstNode;

        while (frontNode != null) {
            Node2 temp = frontNode.front;
            frontNode.front = frontNode.next;
            frontNode.next = temp;

            frontNode = temp;
        }
        return firstNode;
    }


    public static boolean isHasCircle(Node node) {

        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }


    public TreeNode mergeTree(TreeNode tree1, TreeNode tree2) {

        if (tree1 == null && tree2 == null) {
            return null;
        }

        if (tree1 == null) {
            return tree2;
        }

        if (tree2 == null) {
            return tree1;
        }

        TreeNode result = new TreeNode(tree1.value + tree2.value);
        result.left = mergeTree(tree1.left, tree2.left);

        result.right = mergeTree(tree1.right, tree2.right);
        return result;
    }


    public int getHeight(TreeNode treeNode) {
        int height = 0;
        if (treeNode == null) {
            return height;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(treeNode);
        int currentNum = 1;

        while (!queue.isEmpty()) {

            int childNum = 0;
            while (currentNum > 0) {
                TreeNode node = queue.peek();

                if (node.left != null) {
                    queue.add(node.left);
                    childNum++;

                }

                if (node.right != null) {
                    queue.add(node.right);
                    childNum++;
                }
            }
            currentNum = childNum;
            height++;

        }
        return height;

    }


}

