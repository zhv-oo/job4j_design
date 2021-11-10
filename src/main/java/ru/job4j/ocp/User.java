package ru.job4j.ocp;

/**
 * \* User: zhv
 * \* Date: 10.11.2021
 * \* Time: 11:11
 * \* Description: Примеры нарушения принципа OCP.
 * \
 */

public class User {

    public String name;

    /**
     * Первый пример это жесткая связка с другим класом. Вызов одного класса в другом.
     */

    static class Car {

    }

    static class WashCar {
        void washACar() {
            Car car = new Car();
        }
    }

    /**
     * Второй пример это тоже привязка к другому классу. А не к абстрактному.
     */

    static class Person {
        void getName(User user) {
            System.out.println(user.name);
        }
    }

    /**
     * Третий пример привязка к возвращаемым типам.
     */

    static class Toy {

    }

    static class ToyShop {
        Toy getAnimal() {
            return new Toy();
        }
    }
}
