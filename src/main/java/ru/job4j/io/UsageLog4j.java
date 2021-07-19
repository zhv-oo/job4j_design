package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    @SuppressWarnings("checkstyle:OperatorWrap")
    public static void main(String[] args) {
        BasicConfigurator.configure();
        byte one = 1;
        short two = 2;
        int tree = 3;
        long four = 4;
        float five = 5.0f;
        double six = 6.0d;
        char seven = 'a';
        boolean eight = true;
        LOG.debug("byte : {}, short : {}, int : {}, long : {}, "
                        + "float : {}, double : {}, char : {}, boolean : {}",
                            one, two, tree, four, five, six, seven, eight);
    }
}