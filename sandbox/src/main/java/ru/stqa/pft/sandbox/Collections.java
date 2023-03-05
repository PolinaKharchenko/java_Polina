package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String [] args){
        String[] langse = { "Java", "S#", "Python", "PHP" };

        List<String> languages = Arrays.asList("Java", "S#", "Python", "PHP" );

        for (String l : languages)
        {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
