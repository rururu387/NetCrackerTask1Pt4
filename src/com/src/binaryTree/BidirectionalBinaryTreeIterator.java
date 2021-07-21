package com.src.binaryTree;

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
}
