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
        if (nextForBoundaryValues())
        {
            return;
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
        if (prevForBoundaryValues())
        {
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
