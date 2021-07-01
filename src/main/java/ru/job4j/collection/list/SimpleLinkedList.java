package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> firstNode = null;
    private Node<E> root = null;
    private int size;
    private int modCount = 0;

    @Override
    public void add(E value) {
        if (root == null) {
            root = new Node<E>(value, null);
            firstNode = root;
        } else {
            root.next = new Node<E>(value, null);
            root = root.next;
            if (firstNode != null && firstNode.next == null) {
                firstNode.next = root;
            }
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int i = 0;
        Node<E> tmp = firstNode;
        while (i < index) {
            tmp = tmp.next;
            i++;
        }
        return tmp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (pos == 0) {
                    root = firstNode;
                }
                Node<E> tmp = root;
                root = tmp.next;
                pos++;
                return tmp.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}