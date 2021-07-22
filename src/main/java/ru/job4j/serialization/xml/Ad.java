package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "ad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ad {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int number;
    private Contact contact;
    private boolean active;
    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    private String[] group;

    public Ad() {
    }

    public Ad(String name, Integer number, Contact contact, boolean active, String[] group) {
        this.name = name;
        this.number = number;
        this.contact = contact;
        this.active = active;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "number='" + number + '\''
                + "name='" + name + '\''
                + "contact='" + contact + '\''
                + "active='" + active + '\''
                + "group='" + Arrays.toString(group) + '\''
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Ad ad = new Ad("Tv", 875, new Contact("+79007771111"), true,
                new String[]{"tv", "secondHand"});
        JAXBContext context = JAXBContext.newInstance(Ad.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(ad, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Ad result = (Ad) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}