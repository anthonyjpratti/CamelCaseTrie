package com.ant.main;

import com.ant.trie.Trie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {
        if( args.length != 1){
            System.err.println("Usage: Demo [path/to/dictionary]");
            System.exit(1);
        }
        String fileName = args[0];
        Trie trie = new Trie();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName)).parallel()) {
            stream.map(String::toLowerCase)
                    .filter(str -> str.length() > 1) // Some Dictionaries include all alphabetic characters as words
                    .forEach(trie::addWord);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = trie.makeAllCamelCase("computerscience");
        for (String s : list){
            System.out.println(s);
        }

        list = trie.makeAllCamelCase("hotdogman");
        for (String s : list){
            System.out.println(s);
        }

    }
}
