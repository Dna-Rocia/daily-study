package com.daily.javabsc.interview.recursion;

import com.daily.javabsc.tree.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rocia
 * @description ：创建链表
 * @CreateTime 2018-08-17-9:36
 */
public class Test2Controller {

    public Node createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        //获取第一个节点信息
        Node fristNode = new Node(data.get(0));
        fristNode.setNext(createLinkedList(data.subList(1, data.size())));
        return fristNode;
    }


    public static void main(String[] a) {

        Test2Controller controller = new Test2Controller();

        Node.printLinkedList(controller.createLinkedList(new ArrayList<>()));
        Node.printLinkedList(controller.createLinkedList(Arrays.asList(1)));
        Node.printLinkedList(controller.createLinkedList(Arrays.asList(1, 2, 3)));

    }


}
