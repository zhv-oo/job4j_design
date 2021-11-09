package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    /**
     * Тест формирования отчета для бухгалтерии и вывода ЗП в рублях.
     */
    @Test
    public void whenGenerateToBuh() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportToBuh(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * 72).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    /**
     * Тест формирования отчета для отдела разработки в html.
     */
    @Test
    public void whenGenerateToDev() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportToDev(store);
        StringBuilder expect = new StringBuilder()
                .append("<name>").append(worker.getName()).append("</name>")
                .append(System.lineSeparator())
                .append("<hired>").append(worker.getHired()).append("</hired>")
                .append(System.lineSeparator())
                .append("<fired>").append(worker.getFired()).append("</fired>")
                .append(System.lineSeparator())
                .append("<salary>").append(worker.getSalary()).append("</salary>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    /**
     * Тест формирования отчета для отдела HR.
     */
    @Test
    public void whenGenerateToHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee workerTwo = new Employee("Fedr", now, now, 90);
        store.add(workerTwo);
        store.add(worker);
        Report engine = new ReportToHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerTwo.getName()).append(";")
                .append(workerTwo.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}