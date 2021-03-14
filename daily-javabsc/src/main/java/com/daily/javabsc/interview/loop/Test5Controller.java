package com.daily.javabsc.interview.loop;


import com.daily.javabsc.interview.recursion.Test2Controller;
import com.daily.javabsc.tree.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rocia
 * @description :链表 1，2,3,2,5 删除 节点为2的
 * @CreateTime 2018-08-18-11:42
 */
public class Test5Controller {

    public Node deleteIfEquals(Node head, int value) {

        while (head != null && head.getValue() == value) {
            head = head.getNext();
        }

        if (head == null) {
            return null;
        }

        //保证prev是最后一个节点，信息不为null
        Node prev = head;

        //prev下一个节点是否为空
        while (prev.getNext() != null) {
            //prev下一个节点是我们要删除的值
            if (prev.getNext().getValue() == value) {
                //删除他，指针跳过他直达下一个
                prev.setNext(prev.getNext().getNext());
            } else {
                //prev下一个节点不是我们要删除的值，就移动一位
                prev = prev.getNext();
            }
        }

        return head;
    }


    public static void main(String[] a) {

        Test2Controller create = new Test2Controller();
        Test5Controller delete = new Test5Controller();

        Node.printLinkedList(
                delete.deleteIfEquals(create.createLinkedList(new ArrayList<>()), 1)
        );
        Node.printLinkedList(
                delete.deleteIfEquals(create.createLinkedList(Arrays.asList(1, 2, 3, 4, 1, 5)), 1)
        );
        Node.printLinkedList(
                delete.deleteIfEquals(create.createLinkedList(Arrays.asList(2, 2, 2, 3, 3, 2)), 2)
        );
    }


}
