package com.daily.javabsc.tree;

/**
 * @author Rocia
 * @description : 树的学习
 * @CreateTime 2018-08-21-15:21
 */
public class TreeNode {

    private final char value;
    private TreeNode left;
    private TreeNode right;


    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public char getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
