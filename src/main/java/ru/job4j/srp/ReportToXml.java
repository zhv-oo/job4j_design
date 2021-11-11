package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

/**
 * \* User: zhv
 * \* Date: 11.11.2021
 * \* Time: 20:11
 * \* Description:
 * \
 */
public class ReportToXml implements Report {

    private Store store;

    public ReportToXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String res = "";
        try {
            res = toXml(store.findBy(filter));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static String toXml(List<Employee> employeeList) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(
                    new Employees(employeeList), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "users")
    public static class Employees {

        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getUsers() {
            return employees;
        }

        public void setUsers(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
