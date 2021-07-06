package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {
    @Test
    public void whenAdd() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("One", 4);
        simpleMap.put("Two", 2);
        assertThat(simpleMap.get("One"), is(4));
        assertThat(simpleMap.get("Two"), is(2));
    }

    @Test
    public void whenAddMoreThenCap() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("One", 1);
        simpleMap.put("Two", 2);
        simpleMap.put("Three", 3);
        simpleMap.put("Four", 4);
        simpleMap.put("Five", 5);
        simpleMap.put("Six", 6);
        simpleMap.put("Seven", 7);
        simpleMap.put("Eighth", 8);
        assertThat(simpleMap.get("One"), is(1));
        assertThat(simpleMap.get("Eighth"), is(8));
    }

    @Test
    public void whenDelete() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("One", 4);
        simpleMap.put("Two", 2);
        assertThat(simpleMap.get("One"), is(4));
        assertTrue(simpleMap.remove("Two"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterElem() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("One", 4);
        simpleMap.put("Two", 2);
        simpleMap.remove("Two");
        Iterator<String> smIter = simpleMap.iterator();
        assertThat(simpleMap.get(smIter.next()), is(4));
        assertThat(simpleMap.get(smIter.next()), is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterDeleteNoSuchElem() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("One", 4);
        simpleMap.put("Two", 2);
        simpleMap.remove("Two");
        Iterator<String> smIter = simpleMap.iterator();
        simpleMap.get(smIter.next());
        simpleMap.get(smIter.next());
    }
}