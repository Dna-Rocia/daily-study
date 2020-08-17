package com.daily.javabsc.tree;

/**
 * @author Rocia
 * @description :
 * @CreateTime 2018-08-21-16:27
 */
public class TreeCreator {

    public TreeNode createSampleTree() {
        TreeNode root = new TreeNode('A');
        root.setLeft(new TreeNode('B'));
        root.getLeft().setLeft(new TreeNode('D'));
        root.getLeft().setRight(new TreeNode('E'));
        root.getLeft().getRight().setLeft(new TreeNode('G'));
        root.setRight(new TreeNode('C'));
        root.getRight().setRight(new TreeNode('F'));
        return root;
    }

    /**
     * 根据前、中序进行建树
     *
     * @param preOrder：前序
     * @param inOrder：中序
     * @return： 构建好的树
     */
    public TreeNode createTree(String preOrder, String inOrder) {
        //树节点：对象  则返回null
        if (preOrder.isEmpty()) return null;

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        TreeNode treeNode = new TreeNode(rootValue);

        //注意要跳过父节点
        treeNode.setLeft(
                createTree(preOrder.substring(1, 1 + rootIndex),
                        inOrder.substring(0, rootIndex))
        );
        treeNode.setRight(
                createTree(preOrder.substring(1 + rootIndex),
                        inOrder.substring(1 + rootIndex))
        );
        return treeNode;
    }

}
