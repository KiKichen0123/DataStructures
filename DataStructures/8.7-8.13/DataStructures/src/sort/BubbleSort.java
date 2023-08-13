package sort;

import java.util.Arrays;

/**
 * 该程序的说明如下：
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, 20};

        System.out.println("排序前：" + Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){  //在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false;   //重置flag
            }
        }
    }
}
