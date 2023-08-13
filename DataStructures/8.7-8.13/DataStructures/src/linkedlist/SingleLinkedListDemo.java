package linkedlist;

import java.util.Stack;

/**
 * 该程序的说明如下：
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);



        singleLinkedList.addByOrder(new HeroNode(5,"刘玮鹏","史莱姆"));

        singleLinkedList.update(new HeroNode(1,"宋哥","及时雨！"));

        singleLinkedList.del(5);

        singleLinkedList.list();

        System.out.println("获取单链表中有效节点的个数:" + getLenth(singleLinkedList.getHead()));

        System.out.println("查找单链表中的倒数第k个节点:" + findLastIndexNode(singleLinkedList.getHead(), 1));

        System.out.println("==============进行逆序打印==============");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("==============进行反向遍历，破坏数据结构==============");
        reversePrint(singleLinkedList.getHead());
//        singleLinkedList.list();
    }

    //求单链表中有效节点的个数
    public static int getLenth(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if(head.next == null)
            return null;

        int size = getLenth(head);
        //进行一个index的校验
        if(index <= 0 || index > size){
            return null;
        }

        HeroNode cur = head.next;
        for(int i = 0;i<size-index;i++){
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reversetList(HeroNode head){
        if(head.next == null || head.next.next == null){
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");

        while(cur != null){
            next = cur.next;
            cur.next=reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //利用stack栈进行反向遍历，并不破坏单链表的数据结构
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur=cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表
    public static HeroNode addTwoLinkedList(HeroNode head1,HeroNode head2){
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode newNode = new HeroNode(0, "", "");
        HeroNode cur3 = newNode;
        while(cur1 != null && cur2 != null){
            if(cur1.no >= cur2.no){
                cur3.next = cur1;
                cur1 = cur1.next;
            }else{
                cur3.next = cur2;
                cur2 = cur2.next;
            }
        }
        if(cur1 != null){
            cur3.next = cur1;
        }else{
            cur3=cur2.next;
        }
        return newNode.next;
        }
    }

class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单链表
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //遍历链表
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //插入或添加排序
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("你要插入的节点%d已存在",heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据no编号修改节点的信息
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("未找到要修改的节点");
        }
    }

    //删除节点
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("未找到要删除的节点");
        }
    }

    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
