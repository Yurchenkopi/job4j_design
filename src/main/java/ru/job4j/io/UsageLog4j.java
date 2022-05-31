package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Vasya Vasin";
        int age = 5;
        byte weight = 88;
        short height = 173;
        boolean likeEatingCakes = true;
        char p = 'p';
        float pi = 3.1415f;
        long dist = 55760000;
        double price = 1234.232434;
        LOG.debug("User info name : {}, age : {}, height : {}, weight : {}, like eating cakes: {}, with price : {} rub", name, age, height, weight, likeEatingCakes, price);
        LOG.debug("{} constant is {}", p, pi);
        LOG.debug("Distance to mars is {} km", dist);


    }
}
