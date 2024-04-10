package com.estructura;

import java.util.Queue;

class VectorHeap<E extends Comparable<E>> implements Queue<E> {
    private E[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public VectorHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public VectorHeap(int initialCapacity) {
        heap = (E[]) new Comparable[initialCapacity];
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size >= heap.length) {
            resize();
        }
        heap[size] = e;
        percolateUp(size);
        size++;
        return true;
    }

    private void resize() {
        int newSize = heap.length * 2;
        E[] newHeap = (E[]) new Comparable[newSize];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    private void percolateUp(int index) {
        while (index > 0 && heap[parent(index)].compareTo(heap[index]) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        E root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            percolateDown(0);
        }
        return root;
    }

    private void percolateDown(int index) {
        int child;
        E temp = heap[index];
        while (2 * index + 1 < size) {
            child = 2 * index + 1;
            if (child < size - 1 && heap[child].compareTo(heap[child + 1]) > 0) {
                child++;
            }
            if (temp.compareTo(heap[child]) <= 0) {
                break;
            }
            heap[index] = heap[child];
            index = child;
        }
        heap[index] = temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(java.util.Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(java.util.function.Consumer<? super E> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offer'");
    }

    @Override
    public E element() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'element'");
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    public E atenderSiguientePaciente() {
        if (isEmpty()) {
            return null;
        }
        E pacienteAtendido = remove();
        return pacienteAtendido;
    }
}