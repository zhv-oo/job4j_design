package ru.job4j.collection.tree;

import ru.job4j.generics.Predator;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> par = findBy(parent);
        if (par.isPresent() && findBy(child).isEmpty()) {
            par.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    public boolean isBinary() {
        return findByPredicate(obj -> obj.children.size() > 2).isEmpty();
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(obj -> obj.value.equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}