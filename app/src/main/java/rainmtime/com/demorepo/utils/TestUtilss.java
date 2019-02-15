package rainmtime.com.demorepo.utils;

/**
 * Created by chunyu on 2019/2/13 4:05 PM.
 * Email: 746431278@qq.com
 */
public class TestUtilss {

    public static int pivotIndex(int[] a, int left, int right) {

        int posiValue = a[left];

        while (left < right) {
            while (posiValue < a[right] && left < right) {
                right--;
            }

            if (left < right) {
                a[left] = a[right];
                left++;
            }

            while (posiValue > a[left] && left < right) {
                left++;
            }

            if (left < right) {
                a[right] = a[left];
                right++;
            }
        }
        a[left] = posiValue;
        return left;
    }

    public static int maxSumOfArray(int[] a) {
        int maxSum = 0;
        int currentSum = 0;
        int i = 0;
        while (i < a.length) {
            currentSum += a[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }

            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }


}
