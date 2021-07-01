package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outCount = 0;
    private int inCount = 0;

    public T poll() {
        while (outCount <= inCount) {
            out.push(in.pop());
            inCount--;
            outCount++;
        }
        outCount--;
        return out.pop();
    }

    public void push(T value) {
        while (outCount > inCount) {
            in.push(out.pop());
            inCount++;
            outCount--;
        }
        in.push(value);
        inCount++;
    }
}