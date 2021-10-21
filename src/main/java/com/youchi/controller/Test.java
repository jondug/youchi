package com.youchi.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public void test1(String a, String b) {
        System.out.println("test1:" + a + b);
    }

    public void test2(String a, String b) {
        System.out.println("test2:" + a + b);
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.remove(0);
    }
}
