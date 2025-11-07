package colletion;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueue<T> implements Queue<T> {

    /**
     * Внутренний класс, представляющий узел в связном списке.
     */
    private static class Node<T> {
        T item; // Данные узла
        Node<T> next; // Ссылка на следующий узел

        Node(T element) {
            this.item = element;
            this.next = null;
        }
    }

    private int size = 0;   // Текущий размер очереди
    private Node<T> head;   // Голова очереди (первый элемент)
    private Node<T> tail;   // Хвост очереди (последний элемент)

    // --- Методы для добавления элементов ---

    /**
     * Добавляет элемент в конец очереди.
     * Этот метод идентичен offer(), но в случае ограниченной очереди может выбросить исключение.
     * Так как наша очередь не ограничена, он всегда возвращает true.
     * @param t элемент для добавления
     * @return true, если элемент был добавлен
     * @throws NullPointerException если элемент равен null
     */
    @Override
    public boolean add(T t) {
        if (offer(t)) {
            return true;
        } else {
            // В нашей неограниченной реализации это никогда не произойдет
            throw new IllegalStateException("Queue is full");
        }
    }

    /**
     * Добавляет элемент в конец очереди.
     * @param t элемент для добавления
     * @return true, если элемент был успешно добавлен
     * @throws NullPointerException если элемент равен null
     */
    @Override
    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null elements are not allowed in this queue.");
        }

        Node<T> newNode = new Node<>(t);
        if (isEmpty()) {
            // Если очередь пуста, новый узел становится и головой, и хвостом
            head = newNode;
        } else {
            // Иначе, он привязывается к текущему хвосту
            tail.next = newNode;
        }
        tail = newNode; // Новый узел теперь является хвостом
        size++;
        return true;
    }

    // --- Методы для извлечения элементов ---

    /**
     * Извлекает и удаляет голову очереди.
     * @return голова очереди
     * @throws NoSuchElementException если очередь пуста
     */
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return poll();
    }

    /**
     * Извлекает и удаляет голову очереди.
     * @return голова очереди или null, если очередь пуста
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T item = head.item;
        Node<T> nextNode = head.next;
        head.item = null; // Помогаем сборщику мусора
        head.next = null;
        head = nextNode;
        size--;

        // Если после удаления очередь стала пустой, хвост тоже должен быть null
        if (isEmpty()) {
            tail = null;
        }

        return item;
    }

    // --- Методы для просмотра элементов ---

    /**
     * Возвращает, но не удаляет, голову очереди.
     * @return голова очереди
     * @throws NoSuchElementException если очередь пуста
     */
    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return peek();
    }

    /**
     * Возвращает, но не удаляет, голову очереди.
     * @return голова очереди или null, если очередь пуста
     */
    @Override
    public T peek() {
        return isEmpty() ? null : head.item;
    }

    // --- Основные методы коллекции ---

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (Node<T> current = head; current != null; current = current.next) {
            if (o.equals(current.item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает очередь.
     */
    @Override
    public void clear() {
        // Помогаем сборщику мусора, обнуляя все узлы
        for (Node<T> current = head; current != null; ) {
            Node<T> next = current.next;
            current.item = null;
            current.next = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    // --- Остальные методы (часто реализуются через базовые) ---

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            result[i++] = current.item;
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<T> current = head; current != null; current = current.next) {
            result[i++] = current.item;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    // Удаление элемента из середины не является типичной операцией для очереди,
    // но интерфейс Collection требует его реализации.
    @Override
    public boolean remove(Object o) {
        if (o == null) return false;

        Node<T> prev = null;
        Node<T> current = head;

        while (current != null) {
            if (o.equals(current.item)) {
                // Если узел - голова
                if (prev == null) {
                    poll();
                } else {
                    prev.next = current.next;
                    // Если узел - хвост
                    if (current == tail) {
                        tail = prev;
                    }
                    size--;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    // Bulk-операции (реализованы для полноты)

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            // Цикл для удаления всех вхождений, если они есть
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                // Итератор не поддерживает remove, поэтому используем основной метод
                // Это неэффективно, но просто для реализации
                remove(it.next());
                modified = true;
            }
        }
        return modified;
    }
}
