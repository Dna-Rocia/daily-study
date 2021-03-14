package com.daily.javabsc.interview.recursion;


import com.daily.javabsc.tree.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rocia
 * @description :链表反转
 * @CreateTime 2018-08-17-13:24
 */
public class Test3Controller {


    public Node reverseLinkedList(Node head) {
        if (head == null || head.getNext() == null) return head;
        Node newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }


    public static void main(String[] a) {
        Test3Controller test3Controller = new Test3Controller();
        Test2Controller controller = new Test2Controller();

        Node.printLinkedList(test3Controller.reverseLinkedList(controller.createLinkedList(new ArrayList<>())));
        Node.printLinkedList(test3Controller.reverseLinkedList(controller.createLinkedList(Arrays.asList(1))));
        Node.printLinkedList(test3Controller.reverseLinkedList(controller.createLinkedList(Arrays.asList(1, 2, 3))));
    }


}
