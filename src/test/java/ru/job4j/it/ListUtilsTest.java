package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterAndRemoveLessTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        ListUtils.removeIf(input, obj -> obj < 2);
        assertThat(Arrays.asList(2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterAndRemoveMoreOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        ListUtils.removeIf(input, obj -> obj > 1);
        assertThat(Arrays.asList(0, 1), Is.is(input));
    }

    @Test
    public void whenAddAfterAndReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        ListUtils.replaceIf(input, obj -> obj > 1, 0);
        assertThat(Arrays.asList(0, 1, 0 , 0), Is.is(input));
    }

    @Test
    public void whenAddAfterAndReplaceTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        ListUtils.replaceIf(input, obj -> obj % 2 == 0, 4);
        assertThat(Arrays.asList(4, 1, 4 , 3), Is.is(input));
    }

    @Test
    public void whenAddBeforeAndRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addBefore(input, 2, 3);
        ListUtils.removeAll(input, List.of(2, 3));
        assertThat(Arrays.asList(0, 1), Is.is(input));
    }

    @Test
    public void whenAddBeforeAndRemoveAllInMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addBefore(input, 2, 3);
        ListUtils.removeAll(input, List.of(1, 2));
        assertThat(Arrays.asList(0, 3), Is.is(input));
    }

    @Test
    public void whenAddBeforeAndRemoveAllFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addBefore(input, 2, 3);
        ListUtils.removeAll(input, List.of(0, 1, 2));
        assertThat(Arrays.asList(3), Is.is(input));
    }
}