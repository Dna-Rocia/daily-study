package com.daily.javabsc.tree.contl;


import com.daily.javabsc.tree.TreeCreator;
import com.daily.javabsc.tree.TreeNode;

/**
 * @author Rocia
 * @description :
 * @CreateTime 2018-08-21-16:43
 */
public class TreeTraversal {

    //前序遍历：根->左->右
    public void preOrder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    //中序遍历：左->根->右
    public void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    //后序遍历：左->右->根
    public void postOrder(TreeNode root) {
        if (root == null) return;

        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }


    //后序遍历：左->右->根
    public String postOrder(String preOrder, String inOrder) {
        //树节点：对象  则返回null
        if (preOrder.isEmpty()) return "";

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        //注意要跳过父节点
        return
                postOrder(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex)) +
                        postOrder(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex)) +
                        rootValue;
    }


    public static void main(String[] str) {
        TreeCreator creator = new TreeCreator();
        TreeTraversal treeTraversal = new TreeTraversal();
        TreeNode node = creator.createSampleTree();

        treeTraversal.preOrder(node);
        System.out.println();
        treeTraversal.inOrder(node);
        System.out.println();
        treeTraversal.postOrder(node);
        System.out.println();


        System.out.println("1=============");
        TreeNode treeNode = creator.createTree("ABDEGCF", "DBGEACF");
        treeTraversal.postOrder(treeNode);
        System.out.println();
        treeTraversal.postOrder(creator.createTree("", ""));
        System.out.println();
        treeTraversal.postOrder(creator.createTree("A", "A"));
        System.out.println();
        treeTraversal.postOrder(creator.createTree("AB", "BA"));
        System.out.println();

        System.out.println("2=============");
        treeTraversal.postOrder(creator.createTree("ABDEGCF", "DBGEACF"));
        System.out.println();
        treeTraversal.postOrder(creator.createTree("", ""));
        System.out.println();
        treeTraversal.postOrder(creator.createTree("A", "A"));
        System.out.println();
        treeTraversal.postOrder(creator.createTree("AB", "BA"));
        System.out.println();
    }
}
