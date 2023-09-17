package dac;

/**
 * 该程序的说明如下：
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c){
        if(num == 1){
            System.out.println("第1个盘从 " + a + "->" + b);
        }else{
            hanoiTower(num-1,a,c,b);
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            hanoiTower(num-1,b,a,c);
        }
    }
}
