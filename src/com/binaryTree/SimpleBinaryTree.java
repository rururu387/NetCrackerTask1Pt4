package com.binaryTree;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

import java.util.List;

public class SimpleBinaryTree<T>
{
    private BTreeNode<T> root;

    public SimpleBinaryTree(BTreeNode<T> root)
    {
        this.root = root;
    }

    protected static<T> int getHeight(BTreeNode<T> node)
    {
        if (node == null)
        {
            return 0;
        }

        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    public int getHeight()
    {
        return getHeight(root);
    }

    public BTreeNode<T> getRoot()
    {
        return root;
    }
}
