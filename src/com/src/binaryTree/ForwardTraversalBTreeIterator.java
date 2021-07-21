package com.src.binaryTree;

import java.util.NoSuchElementException;

public class ForwardTraversalBTreeIterator<T> extends BidirectionalBinaryTreeIterator<T>
{
    public ForwardTraversalBTreeIterator(SimpleBinaryTree<T> tree)
    {
        super(tree);
    }

    @Override
    public BTreeNode<T> getFront()
    {
        return tree.getRoot();
    }

    @Override
    public BTreeNode<T> getBack()
    {
        return BTreeNode.getRightestLeaf(tree.getRoot());
    }

    @Override
    public void next()
    {
        if (currentNode == beginNode)
        {
            currentNode = front;
            return;
        }

        if (currentNode == endNode)
        {

            //Last node was already achieved.
            //Trying to call next() after the termination node will result in raised exception
            throw new NoSuchElementException("Tried to call next() having termination node as current");
        }

        if (currentNode.hasLeftChild())
        {
            currentNode = currentNode.getLeftChild();
            return;
        }

        if (currentNode.hasRightChild())
        {
            currentNode = currentNode.getRightChild();
            return;
        }

        //This node is a leaf. Going up the tree searching the node that wasn't visited
        while (currentNode != tree.getRoot())
        {
            if (!currentNode.hasParent())
            {
                throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
            }

            if (currentNode.getParent().hasRightChild() && !currentNode.isRightChild())
            {
                currentNode = currentNode.getParent().getRightChild();
                return;
            }

            currentNode = currentNode.getParent();
        }

        currentNode = endNode;
    }

    @Override
    public void prev()
    {
        if (currentNode == beginNode)
        {

            //Trying to get previous of the beginNode will result in raised exception
            throw new NoSuchElementException("Tried to call prev() of a root node");
        }

        if (currentNode == tree.getRoot())
        {
            currentNode = beginNode;
            return;
        }

        if (currentNode == endNode)
        {

            //If user wants to get prev() of termination node
            //Return value becomes the last node (leaf) of a forward traversal
            currentNode = BTreeNode.getRightestLeaf(tree.getRoot());
            return;
        }

        if (!currentNode.hasParent())
        {
            throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
        }

        if (currentNode.getParent().hasLeftChild() && !currentNode.isLeftChild())
        {
            currentNode = BTreeNode.getRightestLeaf(currentNode.getParent().getLeftChild());
            return;
        }

        currentNode = currentNode.getParent();
    }
}
