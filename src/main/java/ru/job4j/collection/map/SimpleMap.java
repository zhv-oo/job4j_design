package ru.job4j.collection.map;

import ru.job4j.collection.map.inter.Map;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int hashKey = indexFor(key.hashCode());
        if (table[hashKey] == null) {
            table[hashKey] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    private void expand() {
        int oldCap = capacity;
        capacity *= 2;
        int index = 0;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        while (index < oldCap) {
            if (table[index] != null) {
               newTable[indexFor(table[index].key.hashCode())] = table[index];
            }
            index++;
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedMod = modCount;
            private int pos = 0;

            @Override
            public boolean hasNext() {
                while (pos < capacity && table[pos] == null) {
                    pos++;
                }
                return pos < capacity;
            }

            @Override
            public K next() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[pos++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}