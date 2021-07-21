import com.binaryTree.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryTreeTest
{
    private SimpleBinaryTree<Integer> tree;

    /**
     * <img src = "file:/TestingTree.png"> Visual tree representation </a>
     */
    public BinaryTreeTest()
    {
        BTreeNode<Integer> root = new BTreeNode<>(6);
        BTreeNode<Integer> node1 = new BTreeNode<>(1);
        BTreeNode<Integer> node2 = new BTreeNode<>(2);
        BTreeNode<Integer> node3 = new BTreeNode<>(3);
        BTreeNode<Integer> node4 = new BTreeNode<>(4);
        BTreeNode<Integer> node5 = new BTreeNode<>(5);
        BTreeNode<Integer> node6 = new BTreeNode<>(6);
        BTreeNode<Integer> node7 = new BTreeNode<>(7);
        BTreeNode<Integer> node8 = new BTreeNode<>(8);
        BTreeNode<Integer> node9 = new BTreeNode<>(9);
        BTreeNode<Integer> node10 = new BTreeNode<>(10);
        BTreeNode<Integer> node11 = new BTreeNode<>(11);
        root.setLeftChild(node3);
        node3.setLeftChild(node2);
        node2.setLeftChild(node1);
        node3.setRightChild(node5);
        node5.setLeftChild(node4);
        root.setRightChild(node8);
        node8.setLeftChild(node7);
        node8.setRightChild(node10);
        node10.setLeftChild(node9);
        node10.setRightChild(node11);
        tree = new SimpleBinaryTree<Integer>(root);
    }

    @Test
    public void testFrowardTraversalNext()
    {
        ForwardTraversalBTreeIterator<Integer> forwardIterator = new ForwardTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (forwardIterator.setToFront(); forwardIterator.get() != forwardIterator.end(); forwardIterator.next())
        {
            actual.add(forwardIterator.get().getContent());
        }
        List<Integer> expected = List.of(6, 3, 2, 1, 5, 4, 8, 7, 10, 9, 11);
        assertEquals(expected, actual);
    }

    @Test
    public void testForwardTraversalPrev()
    {
        ForwardTraversalBTreeIterator<Integer> forwardIterator = new ForwardTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (forwardIterator.setToBack(); forwardIterator.get() != forwardIterator.begin(); forwardIterator.prev())
        {
            actual.add(forwardIterator.get().getContent());
        }

        List<Integer> expected = new LinkedList<>(List.of(6, 3, 2, 1, 5, 4, 8, 7, 10, 9, 11));
        Collections.reverse(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testReverseTraversalNext()
    {
        ReverseTraversalBTreeIterator<Integer> reverseIterator =new ReverseTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (; reverseIterator.get() != reverseIterator.end(); reverseIterator.next())
        {
            actual.add(reverseIterator.get().getContent());
        }

        List<Integer> expected = List.of(1, 2, 4, 5, 3, 7, 9, 11, 10, 8, 6);
        assertEquals(expected, actual);
    }

    @Test
    public void testReverseTraversalPrev()
    {
        ReverseTraversalBTreeIterator<Integer> reverseIterator = new ReverseTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (reverseIterator.setToBack(); reverseIterator.get() != reverseIterator.begin(); reverseIterator.prev())
        {
            actual.add(reverseIterator.get().getContent());
        }

        List<Integer> expected = new LinkedList<>(List.of(1, 2, 4, 5, 3, 7, 9, 11, 10, 8, 6));
        Collections.reverse(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testCenteredTraversalNext()
    {
        CenteredTraversalBTreeIterator<Integer> centeredIterator = new CenteredTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (; centeredIterator.get() != centeredIterator.end(); centeredIterator.next())
        {
            actual.add(centeredIterator.get().getContent());
        }

        List<Integer> expected = IntStream.range(1, 12).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void testCenteredTraversalPrev()
    {
        CenteredTraversalBTreeIterator<Integer> centeredIterator = new CenteredTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        for (centeredIterator.setToBack(); centeredIterator.get() != centeredIterator.begin(); centeredIterator.prev())
        {
            actual.add(centeredIterator.get().getContent());
        }

        List<Integer> expected = IntStream.range(1, 12).boxed().collect(Collectors.toList());
        Collections.reverse(expected);
        assertEquals(expected, actual);
    }



    @Test
    public void testCenteredTraversalMultiDirection()
    {
        CenteredTraversalBTreeIterator<Integer> centeredIterator = new CenteredTraversalBTreeIterator<>(tree);
        List<Integer> actual = new LinkedList<>();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.prev();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.next();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.prev();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.prev();
        actual.add(centeredIterator.get().getContent());
        centeredIterator.prev();
        actual.add(centeredIterator.get().getContent());

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 7, 6, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void countTreeLength()
    {
        assertEquals(4, tree.getHeight());
    }
}
