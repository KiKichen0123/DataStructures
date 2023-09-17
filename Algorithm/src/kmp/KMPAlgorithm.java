package kmp;

import java.util.Arrays;

/**
 * 该程序的说明如下：
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] ints = kmpNext(str2);
        //因为AB连续，没有进入while，j++ = 2
        System.out.println(Arrays.toString(ints));

    }

    /**
     * kmp搜索算法
     * @param str1  源字符串
     * @param str2  子串
     * @param next  子串对应的部分匹配表
     * @return  -1 没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for(int i = 0,j = 0;i < str1.length();i++){
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i-j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0]=0;  //如果字符串是长度为1 部分匹配值就是0
        for(int i =1,j=0;i<dest.length();i++){
            //kmp算法的核心点
            //回溯找到相等，如果没有，一直到j = 0
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];
            }
            //当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值就是+1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
