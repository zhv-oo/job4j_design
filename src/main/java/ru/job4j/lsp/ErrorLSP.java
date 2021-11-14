package ru.job4j.lsp;

import jdk.jshell.spi.ExecutionControl;
import ru.job4j.collection.list.List;

/**
 * \* User: zhv
 * \* Date: 14.11.2021
 * \* Time: 21:11
 * \* Description: Примеры нарушения принципа LSP.
 * \
 */
public class ErrorLSP {

    /**
     * Нарушение когда у потомка переопределен но не реализован метотд предка.
     */

    class Doctor {
        String heal(String name) {
            return new String();
        }
    }

    class NewDoctor extends Doctor {
        @Override
        String heal(String name) {
            try {
                    throw new ExecutionControl.NotImplementedException("Поведение не определено!");
            } catch (ExecutionControl.NotImplementedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Когда мы раширяем наследника за пределы родителя не обрабатывая это.
     * В итоге получим некоректные данные из-за нарушения принципа.
     */

    static class SomeFigure {
        Double lineOne;
        Double lineTwo;

        Double area() {
            return this.lineOne * this.lineTwo;
        }

    }
    
    class AnotherFigure extends SomeFigure {
        Double lineOne;
        Double lineTwo;
        Double lineThree;
    }

    class UseFigure {
        void calcArea(List<SomeFigure> rectangles) {
            rectangles.add(new SomeFigure());
            rectangles.add(new AnotherFigure());
            rectangles.forEach(SomeFigure::area);
        }
    }

    /**
     * Когда упускается проверка.
     */

    class BaseTriangle {
        Double lineOne;
        Double lineTwo;
        Double lineThree;

        boolean canBuild(Double lineOne, Double lineTwo, Double lineThree) {
            boolean res = false;
            if (lineThree > lineOne + lineTwo
                    || lineOne > lineTwo + lineThree
                    || lineTwo > lineOne + lineThree) {
                res = true;
            }
            return res;
        }
    }

    class Triangle extends BaseTriangle {
        @Override
        boolean canBuild(Double lineOne, Double lineTwo, Double lineThree) {
            return true;
        }
    }

    class UseTriangle {
        void makeTriangle(Double lineOne, Double lineTwo, Double lineThree) {
            BaseTriangle triangle = new Triangle();
            if (triangle.canBuild(3.0, 3.0, 7.0)) {
                System.out.println("Треугольник создан!");
            }
        }
    }
}
