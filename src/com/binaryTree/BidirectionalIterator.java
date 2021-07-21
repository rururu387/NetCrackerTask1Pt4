package com.binaryTree;

/**
 * This interface may be implemented by many collections
 * @param <T>
 */
public interface BidirectionalIterator<T>
{

    /**
     * Collection DOES NOT CONTAIN an element that this function returns.
     * This element may be imagined as the ones before the first element.
     * Calling prev() to the first iterable element of a collection (back) will give a dummy node that begin() returns.
     * @see <a href = "https://en.cppreference.com/w/cpp/container/map/rend"> C++ - style iterators - this element is an analogue of rend </a>
     */
    T begin();

    /**
     * Collection DOES NOT CONTAIN an element that this function returns.
     * This element may be imagined as the one after the last element.
     * Calling next() to the last iterable element of a collection (back) will give a dummy node that begin() returns.
     * @see <a href = "https://en.cppreference.com/w/cpp/container/map/end"> C++ - style iterators - this element is an analogue of end </a>
     */
    T end();

    /**
     * Move to the next element
     */
    void next();

    /**
     * Move to the previous element
     */
    void prev();

    /**
     * Get current element
     */
    T get();
}
