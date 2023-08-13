package linkedlist;

/**
 * 该程序的说明如下：
 * 用单向环形链表实现约瑟夫问题
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;  //辅助指针，帮助构建环形链表
        //使用for来创建环形链表
        for(int i = 1;i<= nums;i++){
            Boy boy = new Boy(i);   //根据编号创建小孩节点
            if(i == 1){
                first = boy;
                first.setNext(first);   //构成环
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        if(first == null){
            System.out.println("没有节点");
            return;
        }
        //因为first不能动，因此要使用一个辅助指针完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo  从第几个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first;
        //helper 指向圈中最后一个节点
        while(true){
            if(helper.getNext() == first){  //已经是最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //数数前，先让first 和 helper 移动 startNo-1 次
        for(int j = 0;j < startNo - 1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //数数时，让 first 和 helper 指针同时移动 countNum-1 次，然后出圈
        while(true){
            if(helper == first){    //只有一个节点
                break;
            }
            for(int j = 0;j < countNum -1 ;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的就是要出圈的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}