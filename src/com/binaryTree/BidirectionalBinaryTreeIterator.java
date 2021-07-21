package com.binaryTree;

import java.util.NoSuchElementException;

abstract class BidirectionalBinaryTreeIterator<T> implements BidirectionalIterator<BTreeNode<T>>
{
    protected SimpleBinaryTree<T> tree;
    protected BTreeNode<T> currentNode;


    protected final BTreeNode<T> endNode = new BTreeNode<T>(null);
    protected final BTreeNode<T> beginNode = new BTreeNode<T>(null);

    /**
     * Different iterators have different first and last nodes that are traversed.
     * This is a first iterable node of this iterator.
     * It is cached here to save execution time
     */
    public final BTreeNode<T> front;

    /**
     * Different iterators have different first and last nodes that are traversed.
     * This is a last iterable node of this iterator.
     * It is cached here to save execution time.
     */
    public final BTreeNode<T> back;

    /**
     * {@link #front About return value}
     */
    protected abstract BTreeNode<T> getFront();

    /**
     * {@link #back About return value}
     */
    protected abstract BTreeNode<T> getBack();

    public BidirectionalBinaryTreeIterator(SimpleBinaryTree<T> tree)
    {
        this.tree = tree;
        front = getFront();
        back = getBack();
        currentNode = front;
    }

    @Override
    public BTreeNode<T> get()
    {
        return currentNode;
    }

    public SimpleBinaryTree<T> getTree()
    {
        return tree;
    }

    /**
     * Calls {@link #next next(void)} num times
     */
    public void next(int num)
    {
        for (int i = 0; i < num; i++)
        {
            next();
        }
    }

    /**
     * Calls {@link #prev prev(void)} num times
     */
    public void prev(int num)
    {
        for (int i = 0; i < num; i++)
        {
            prev();
        }
    }

    @Override
    public BTreeNode<T> begin() { return beginNode; }

    @Override
    public BTreeNode<T> end()
    {
        return endNode;
    }

    /**
     * Sets currentNode to {@link #back back}
     */
    public void setToBack()
    {
        currentNode = back;
    }

    /**
     * Sets currentNode to {@link #front front}
     */
    public void setToFront()
    {
        currentNode = front;
    }

    //Decompose repeated code
    protected boolean nextForBoundaryValues()
    {
        if (currentNode == beginNode)
        {
            currentNode = front;
            return true;
        }

        if (currentNode == back)
        {
            currentNode = endNode;
            return true;
        }

        if (currentNode == endNode)
        {

            //Last node was already achieved.
            //Trying to call next() after the termination node will result in raised exception
            throw new NoSuchElementException("Tried to call next() having termination node as current");
        }
        return false;
    }

    protected boolean prevForBoundaryValues()
    {
        if (currentNode == beginNode)
        {

            //Trying to get previous of the root node will result in raised exception
            throw new NoSuchElementException("Tried to call prev() of a root node");
        }

        if (currentNode == front)
        {
            currentNode = beginNode;
            return true;
        }

        if (currentNode == endNode)
        {

            //If user wants to get prev() of termination node
            //Return value becomes the last node of a reverse traversal (always root)
            currentNode = tree.getRoot();
            return true;
        }

        return false;
    }
}
