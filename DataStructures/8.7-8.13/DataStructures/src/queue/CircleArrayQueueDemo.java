package queue;

import java.util.Scanner;

/**
 * 该程序的说明如下：
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个环形队列
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' ';  //接收用户输入
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scan.next().charAt(0);    //接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scan.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.print("请输入要添加的数据：");
                    int n = scan.nextInt();
                    arrayQueue.addQueue(n);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序退出~");
    }
}

class CircleArray{
    private int maxSize;    //数组的最大容量
    private int front;  //队列头
    private int rear;   //队列尾
    private int[] arr;  //数组模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("满了，不能添加数据");
            return;
        }
        arr[rear]=n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1)%maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列是空的");
            return;
        }
        //遍历
        for(int i = front,count = 0;count<size();i=(i+1)%maxSize,count++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    /*
    rear=4
    maxSize=6
    front =1 size()=(4+6-1)%6=3
    i=1 count=0
    arr[1]
    i=(1+1)%6=2 count=1
    arr[2]
    i=(2+1)%6=3 count=2
    arr[3]

     */
    //求出当前队列有效数据的个数
    public int size(){
       return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }
}
