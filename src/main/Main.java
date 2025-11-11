package main;

import text_case.Animal;
import text_case.Bear;
import text_case.DoInterface;
import text_case.Fish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        Animal animal = new Bear("Медведь", "Белый", "22", "100");

        DoInterface doIntImpl = new Fish();

        System.out.println(doIntImpl.vois());
        System.out.println(animal.run());
        System.out.println(animal.eat2());

        int x = 5;
        int y = 10;
        if (x < 10 || ++y > 10) {
            // блок выполнится
        }
        System.out.println(y);

        int score = 75;
        String result = (score >= 60) ? "Сдано" : "Не сдано";
        System.out.println(result);


    }


}