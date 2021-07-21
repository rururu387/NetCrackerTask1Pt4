package com.binaryTree;

import java.util.NoSuchElementException;

public class CenteredTraversalBTreeIterator<T> extends BidirectionalBinaryTreeIterator<T>
{
    enum LastNode
    {
        Parent,
        Child
    }

    LastNode lastNode;

    //boolean isLastOperationNext = true;

    public CenteredTraversalBTreeIterator(SimpleBinaryTree<T> tree)
    {
        super(tree);
        lastNode = LastNode.Parent;
    }

    @Override
    public BTreeNode<T> getFront()
    {
        return BTreeNode.getLeftestNode(tree.getRoot());
    }

    @Override
    public BTreeNode<T> getBack()
    {
        return BTreeNode.getRightestNode(tree.getRoot());
    }

    private BTreeNode<T> findNodeThatIsALeftChild(BTreeNode<T> node)
    {
        while (node != tree.getRoot() && node.hasParent() && !node.isLeftChild())
        {
            node = node.getParent();
        }

        if (node == tree.getRoot())
        {
            node = endNode;
            return node;
        }

        if (node.isLeftChild())
        {
            node = node.getParent();
            lastNode = LastNode.Child;
            return node;
        }

        //Something is wrong with my algorithm if execution comes here...
        //throw new DeveloperIsAFoolException    );
        throw new Error();
    }

    private BTreeNode<T> findNodeThatIsARightChild(BTreeNode<T> node)
    {
        while (node != tree.getRoot() && node.hasParent() && !node.isRightChild())
        {
            node = node.getParent();
        }

        if (node == tree.getRoot())
        {
            node = endNode;
            return node;
        }

        if (node.isRightChild())
        {
            node = node.getParent();
            lastNode = LastNode.Child;
            return node;
        }

        //Something is wrong with my algorithm if execution comes here...
        //throw new DeveloperIsAFoolException    );
        throw new Error();
    }

    @Override
    public void next()
    {
        //isLastOperationNext = true;

        if (currentNode == beginNode)
        {
            currentNode = front;
            lastNode = LastNode.Parent;
            return;
        }

        if (currentNode == endNode)
        {

            //Last node was already achieved.
            //Trying to call next() after the termination node will result in raised exception
            throw new NoSuchElementException("Tried to call next() having termination node as current");
        }

        if (currentNode == back)
        {
            currentNode = endNode;
            return;
        }

        switch (lastNode)
        {
            case Parent:
            {
                if (currentNode.hasRightChild())
                {
                    currentNode = BTreeNode.getLeftestNode(currentNode.getRightChild());
                    lastNode = LastNode.Parent;
                    return;
                }

                if (!currentNode.hasParent())
                {
                    throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
                }

                if (currentNode.isLeftChild())
                {
                    currentNode = currentNode.getParent();
                    lastNode = LastNode.Child;
                    return;
                }

                currentNode = findNodeThatIsALeftChild(currentNode);
                return;
            }
            case Child:
            {
                if (currentNode.hasRightChild())
                {
                    currentNode = BTreeNode.getLeftestNode(currentNode.getRightChild());
                    lastNode = LastNode.Parent;
                    return;
                }

                currentNode = findNodeThatIsALeftChild(currentNode);
            }
        }
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
            lastNode = LastNode.Parent;
            return;
        }

        if (currentNode == endNode)
        {

            //If user wants to get prev() of termination node
            //Return value becomes the last node of a reverse bypass (always root)
            currentNode = back;
            lastNode = LastNode.Parent;
            return;
        }

        //isLastOperationNext = false;

        switch (lastNode)
        {
            case Parent:
            {
                if (currentNode.hasLeftChild())
                {
                    currentNode = BTreeNode.getRightestNode(currentNode.getLeftChild());
                    lastNode = LastNode.Parent;
                    return;
                }

                if (!currentNode.hasParent())
                {
                    throw new IllegalStateException("Data corrupted. Non-root nodes must have parents. Current node hasn't: " + currentNode.toString());
                }

                if (currentNode.isRightChild())
                {
                    currentNode = currentNode.getParent();
                    lastNode = LastNode.Child;
                    return;
                }

                currentNode = findNodeThatIsARightChild(currentNode);
                return;
            }
            case Child:
            {
                if (currentNode.hasLeftChild())
                {
                    currentNode = BTreeNode.getRightestNode(currentNode.getLeftChild());
                    lastNode = LastNode.Parent;
                    return;
                }

                currentNode = findNodeThatIsARightChild(currentNode);
                return;
            }
        }
    }
}
