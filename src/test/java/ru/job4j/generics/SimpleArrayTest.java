package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenStringAndRemoveFirst() {
        SimpleArray<String> sma = new SimpleArray<>(3);
        sma.add("a");
        sma.add("b");
        sma.add("c");
        sma.remove(0);
        assertEquals(sma.get(0), "b");
    }

    @Test
    public void whenIntegerAndRemoveMiddle() {
        SimpleArray<Integer> sma = new SimpleArray<>(4);
        sma.add(2);
        sma.add(3);
        sma.remove(1);
        sma.add(4);
        assertThat(sma.get(1), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDoubleAndRemoveLast() {
        SimpleArray<Double> sma = new SimpleArray<>(3);
        sma.add(2.0);
        sma.add(3.0);
        sma.add(4.0);
        sma.remove(2);
        assertThat(sma.get(2), is(4.0));
    }

    @Test
    public void whenCharAndSet() {
        SimpleArray<Character> sma = new SimpleArray<>(3);
        sma.add('a');
        sma.add('b');
        sma.add('c');
        sma.set(2, 'd');
        assertThat(sma.get(2), is('d'));
    }

    @Test
    public void whenIteratorHasNextFalse() {
        SimpleArray<Integer> sma = new SimpleArray<>(4);
        assertFalse(sma.iterator().hasNext());
    }

    @Test
    public void whenIteratorHasNextTrue() {
        SimpleArray<Integer> sma = new SimpleArray<>(4);
        sma.add(1);
        assertTrue(sma.iterator().hasNext());
    }

    @Test
    public void whenIteratorNext() {
        SimpleArray<String> sma = new SimpleArray<>(4);
        sma.add("1");
        sma.add("2");
        sma.add(null);
        sma.add("4");
        Iterator<String> iter = sma.iterator();
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "1");
        assertEquals(iter.next(), "2");
        assertNull(iter.next());
        assertEquals(iter.next(), "4");
        assertFalse(iter.hasNext());
    }
}