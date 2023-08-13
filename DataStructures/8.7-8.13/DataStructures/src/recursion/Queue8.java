package recursion;

/**
 * 该程序的说明如下：
 */
public class Queue8 {
    int max = 8;    //表示共有多少个皇后
    int[] array = new int[max]; //保存皇后位置的结果
    static int count = 0;   //共有多少解法
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共右%d解法",count);
        System.out.printf("\n一共判断冲突的次数%d次",judgeCount);
    }

    private void check(int n){
        if(n == max){   //八个皇后已经放好
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for(int i =0;i<max;i++){
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){
                check(n+1);
            }
        }
    }

    private boolean judge(int n){
        judgeCount++;
        for(int i = 0;i<n;i++){
            if(array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
        for(int i = 0;i < array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
