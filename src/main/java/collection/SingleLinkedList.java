package collection;

public class SingleLinkedList {
    private Node first;//定义头节点
    private int nItems;//定义单链表中实际的数据的数目

    //初始化
    public SingleLinkedList() {
        this.first = null;
        this.nItems = 0;
    }

    //添加头节点
    public void addFirst(int data) {
        //新建节点
        Node newNode = new Node(data);
        //将新节点的下一个节点指向旧的头节点
        newNode.next = first;
        //将新节点设为头节点
        first = newNode;

        nItems ++;
    }

    //删除头结点
    public boolean deleteFirst() {
        //判断链表是否为空
        if(isEmpty()) {
            System.out.println("链表为空！");
            return false;
        }
        first = first.next;
        nItems --;
        return true;
    }

    //有序链表的插入，这样简单排序就可以用链表来实现，复杂度为O(N)
    public void add(int data) {
        //创建新节点
        Node newNode = new Node(data);
        //创建要插入节点之前的节点
        Node previous = null;
        //创建要插入节点的位置上原来的节点
        Node current = first;
        //按从小到大的顺序排序
        while(current != null && data > current.data) {
            previous = current;
            current = current.next;
        }
        if(previous == null) {
            first = newNode;
        }else {
            previous.next = newNode;
        }
        newNode.next = current;
        nItems ++;
    }

    //查询某个特定值的节点
    public Node findNode(int data) {
        //定义一个新节点用于查询
        Node current = first;
        while(current != null && current.data != data) {
            if(current.next == null) {
                System.out.println("该节点不存在");
                return null;
            }
            current = current.next;
        }
        return current;
    }

    //删除某个特定值的节点,并返回该节点
    public Node deleteNode(int data) {
        //定义被删除节点之前的节点
        Node previous = null;
        //定义被删除的节点
        Node current = first;
        while(current != null && current.data != data) {
            if(current.next == null) {
                System.out.println("该节点不存在");
                return null;
            }
            previous = current;
            current = current.next;
        }
        if(previous == null) {
            first = first.next;
        }else {
            previous.next = current.next;
        }
        nItems --;
        return current;
    }

    //遍历链表
    public void traverseList() {
        //定义一个节点用于遍历
        Node current = first;
        //判断链表是否为空
        if(current == null) {
            System.out.println("链表为空！");
            return;
        }
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    //链表的长度
    public int size() {
        return nItems;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return first == null;
    }

}
//定义节点
class Node{
    //声明为public，方便存取
    //指向下一个节点
    public Node next;
    //数据域
    public int data;

    public Node(int data) {
        this.data = data;
    }
}
