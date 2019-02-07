package com.codecool.krk.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordList {
    private final HashTable hashTable;

    public WordList(String fileName, StringHasher hasher) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int index = Integer.parseInt(br.readLine());
        this.hashTable = new HashTable((int)((double)index * 1.2D), hasher);

        for(int var5 = 0; var5 < index; ++var5) {
            this.hashTable.add(br.readLine().trim().toUpperCase());
        }
        br.close();
    }

    public boolean lookup(String var1) {
        return this.hashTable.lookup(var1.toUpperCase());
    }
}
