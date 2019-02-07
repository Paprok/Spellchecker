package com.codecool.krk.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Checker {
    public Checker() {
    }

    public void check(String type, String fileName, StringHasher hasher, PrintStream printStream) throws IOException {
        WordList wordList = new WordList(fileName, hasher);
        BufferedReader br = new BufferedReader(new FileReader(type));
        String line = br.readLine();
        WordLineReader wordReader = new WordLineReader(line);

        label34:
        for(WordChecker wordChecker = new WordChecker(wordList); line != null; wordReader = new WordLineReader(line)) {
            while(true) {
                ArrayList arrayList;
                do {
                    String word;
                    do {
                        if (!wordReader.hasNextWord()) {        // if end of line
                            line = br.readLine();
                            continue label34;
                        }

                        word = wordReader.nextWord().toUpperCase();
                    } while(wordChecker.wordExists(word));          // while is word

                    arrayList = wordChecker.getSuggestions(word);
                    printStream.println();
                    printStream.println(line);
                    printStream.println("     word not found: " + word);        // print searched word
                } while(arrayList.size() <= 0);

                Collections.sort(arrayList);
                printStream.println("  perhaps you meant: ");                   // print suggestions
                Iterator iterator = arrayList.iterator();

                while(iterator.hasNext()) {
                    String suggestion = (String)iterator.next();
                    printStream.println("          " + suggestion + " ");
                }
            }
        }
        br.close();
    }
}
