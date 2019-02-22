package rainmtime.com.demorepo.utils;

/**
 * Created by chunyu on 2019/2/18 7:30 PM.
 * Email: 746431278@qq.com
 */
public class LinedListUtils {

    public static class Node {
        int value;
        Node next;
    }


    //反转链表
    public Node reverseNode(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node firstNode = node;
        Node secondNode = node.next;
        firstNode.next = null;

        while (secondNode != null) {
            Node temp = secondNode.next;
            secondNode.next = firstNode;
            firstNode = secondNode;
            secondNode = temp;
        }

        return firstNode;
    }


}
