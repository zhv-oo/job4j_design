package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outCount = 0;
    private int inCount = 0;

    public T poll() {
        for (int i = 0; i <= inCount; i++) {
            if (outCount <= inCount) {
                out.push(in.pop());
                outCount++;
                inCount--;
            } else {
                in.push(out.pop());
                inCount++;
                outCount--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCount++;
    }
}