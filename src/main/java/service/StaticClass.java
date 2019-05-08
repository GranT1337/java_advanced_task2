package service;

import java.util.concurrent.atomic.AtomicInteger;

public class StaticClass {

    public static AtomicInteger commonCounter = new AtomicInteger(0);
    public static int b = 0;
}
