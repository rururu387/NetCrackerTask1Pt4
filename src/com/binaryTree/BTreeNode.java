package com.binaryTree;

public class BTreeNode<T>
{
    T content;
    private BTreeNode<T> parent;
    private BTreeNode<T> leftChild;
    private BTreeNode<T> rightChild;

    public BTreeNode(T content)
    {
        this.content = content;
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    //Function searches for a Node that has no left children and it's ancestors have it in a left subtree
    //If a tree was a sorted binary tree function would always return the smallest node
    public static<T> BTreeNode<T> getLeftestNode(BTreeNode<T> root)
    {
        BTreeNode<T> retVal = root;
        while (retVal.hasLeftChild())
        {
            retVal = retVal.getLeftChild();
        }

        return retVal;
    }

    //Function searches for a Node that has no right children and it's ancestors have it in a right subtree
    //If a tree was a sorted binary tree function would always return the largest node
    public static<T> BTreeNode<T> getRightestNode(BTreeNode<T> root)
    {
        BTreeNode<T> retVal = root;
        while (retVal.hasRightChild())
        {
            retVal = retVal.getRightChild();
        }

        return retVal;
    }

    //Function searches for a LEAF that is righ to all other leaves
    public static<T> BTreeNode<T> getLeftestLeaf(BTreeNode<T> root)
    {
        BTreeNode<T> retVal = root;

        while(retVal.hasChild())
        {
            if (retVal.hasLeftChild())
            {
                retVal = retVal.getLeftChild();
            }
            else
            {
                retVal = retVal.getRightChild();
            }
        }

        return retVal;
    }

    //Function searches for a LEAF that is right to all other leaves
    public static<T> BTreeNode<T> getRightestLeaf(BTreeNode<T> root)
    {
        BTreeNode<T> retVal = root;

        while(retVal.hasChild())
        {
            if (retVal.hasRightChild())
            {
                retVal = retVal.getRightChild();
            }
            else
            {
                retVal = retVal.getLeftChild();
            }
        }

        return retVal;
    }

    public BTreeNode<T> getParent()
    {
        return parent;
    }

    public BTreeNode<T> getLeftChild()
    {
        return leftChild;
    }

    public BTreeNode<T> getRightChild()
    {
        return rightChild;
    }

    public boolean hasRightChild()
    {
        return rightChild != null;
    }

    public boolean hasLeftChild()
    {
        return leftChild != null;
    }

    public boolean hasChild()
    {
        return hasRightChild() || hasLeftChild();
    }

    public boolean hasParent()
    {
        return parent != null;
    }

    public boolean isLeftChild()
    {
        return hasParent() && this.parent.hasLeftChild() && this.parent.getLeftChild() == this;
    }

    public boolean isRightChild()
    {
        return hasParent() && this.parent.hasRightChild() && this.parent.getRightChild() == this;
    }

    void setParent(BTreeNode<T> parent)
    {
        this.parent = parent;
    }

    public void setLeftChild(BTreeNode<T> leftChild)
    {
        if (leftChild.hasParent())
        {
            leftChild.getParent().setParent(null);
        }
        this.leftChild = leftChild;
        leftChild.setParent(this);
    }

    public void setRightChild(BTreeNode<T> rightChild)
    {
        if (rightChild.hasParent())
        {
            rightChild.getParent().setParent(null);
        }
        this.rightChild = rightChild;
        rightChild.setParent(this);
    }

    public T getContent()
    {
        return content;
    }

    @Override
    public String toString()
    {
        return content.toString();
    }
}