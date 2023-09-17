package binarysearchnorecursion;

/**
 * 该程序的说明如下：
 * 非递归二分查找
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int index = binarySearch(arr, 8);
        System.out.println("idnex = "+index);
    }

    /**
     * 二分查找的非递归实现
     * @param arr   待查找的数组
     * @param target    需要查找的数
     * @return  返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length -1;
        while(left <= right){
            int mid = (left + right)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
