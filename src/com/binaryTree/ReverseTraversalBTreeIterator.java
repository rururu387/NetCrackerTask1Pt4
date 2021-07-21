package com.binaryTree;

import java.util.NoSuchElementException;

public class ReverseTraversalBTreeIterator<T> extends BidirectionalBinaryTreeIterator<T>
{
    public ReverseTraversalBTreeIterator(SimpleBinaryTree<T> tree)
    {
        super(tree);
    }

    @Override
    public BTreeNode<T> getFront()
    {
        return BTreeNode.getLeftestLeaf(tree.getRoot());
    }

    @Override
    public BTreeNode<T> getBack()
    {
        return tree.getRoot();
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

        if (currentNode == tree.getRoot())
        {
            currentNode = endNode;
            return;
        }

        if (!currentNode.hasParent())
        {
            throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
        }

        if (currentNode.getParent().hasRightChild() && !currentNode.isRightChild())
        {
            currentNode = BTreeNode.getLeftestLeaf(currentNode.getParent().getRightChild());
            return;
        }

        currentNode = currentNode.getParent();
    }

    @Override
    public void prev()
    {
        if (currentNode == beginNode)
        {

            //Trying to get previous of the root node will result in raised exception
            throw new NoSuchElementException("Tried to call prev() of a root node");
        }

        if (currentNode == front)
        {
            currentNode = beginNode;
            return;
        }

        if (currentNode == endNode)
        {

            //If user wants to get prev() of termination node
            //Return value becomes the last node of a reverse traversal (always root)
            currentNode = tree.getRoot();
            return;
        }

        if (currentNode.hasRightChild())
        {
            currentNode = currentNode.getRightChild();
            return;
        }

        if (currentNode.hasLeftChild())
        {
            currentNode = currentNode.getLeftChild();
            return;
        }

        while (currentNode != tree.getRoot())
        {
            if (!currentNode.hasParent())
            {
                throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
            }

            if (currentNode.getParent().hasLeftChild() && !currentNode.isLeftChild())
            {
                currentNode = currentNode.getParent().getLeftChild();
                return;
            }

            currentNode = currentNode.getParent();
        }

        //Something is wrong with my algorithm if execution comes here...
        //throw new DeveloperIsAFoolException    );
        throw new Error();
    }
}
