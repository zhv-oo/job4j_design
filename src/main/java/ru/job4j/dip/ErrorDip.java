package ru.job4j.dip;
import java.util.List;

/**
 * Примеры нарушения принципа DIP.
 */
public class ErrorDip {
    /**
     * Класс который жестко привязан к работе через консоль.
     */
    class Message {
        void attention() {
            System.out.println("Attention!");
        }
    }

    /**
     *Класс который жестко привязан к другому,
     *в данном примере хранилищу, в данном случае в виде List.
     */
    class Store {
        private List list;

        public Store(List list) {
            this.list = list;
        }
    }

    class Loader {
        Store store = new Store(List.of());
    }

    /**
     * Класс который жестко связан с другим классом, и возвращает его.
     */
    class ReLoad {
        Store returnStore() {
            return new Store(List.of());
        }
    }
}
