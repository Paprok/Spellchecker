package com.codecool.krk.spellchecker.hashTable;

import com.codecool.krk.spellchecker.hashTable.hashers.StringHasher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordList {
    private final HashTable hashTable;

    public WordList(String fileName, StringHasher hasher) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int index = Integer.parseInt(br.readLine());
        this.hashTable = new HashTable((int)((double)index * 1.2D), hasher);

        for(int i = 0; i < index; ++i) {
            this.hashTable.add(br.readLine().trim().toUpperCase());
        }
        br.close();
    }

    public boolean lookup(String word) {
        return this.hashTable.lookup(word.toUpperCase());
    }
}
