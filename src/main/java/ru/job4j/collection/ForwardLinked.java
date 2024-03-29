package ru.job4j.collection;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> tmp = new Node<>(value, null);
        if (head != null) {
            tmp.next = head;
        }
        head = tmp;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tmp = head;
            head = null;
            head = tmp.next;
        return tmp.value;
    }

    public boolean revert() {
        boolean rsl = false;
         if (head != null && head.next != null) {
             Node<T> src = head;
             Node<T> out = new Node<>(head.value, null);
             while (src.next != null) {
                 src = src.next;
                 out = new Node<>(src.value, out);
             }
             head = out;
             rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}