package main;

import container.MyContainer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MyContainer container = new MyContainer();
      //  Scanner in = new Scanner(System.in);
        container.randomlyFill(1000);
        container.sort();
//        synchronized (container){
//            while(container.)
//        }
        System.out.println(container.size());
        System.out.println(container);
    }
}
