package com.codecool.krk.spellchecker;

import java.io.IOException;
import java.io.PrintStream;

public class SpellCheck {
    public SpellCheck() {
    }

    public static void main(String[] entry) {
        if (entry.length == 0) {
            showUsageMessage();
        } else {
            String type = entry[entry.length - 1];
            String fileName = "wordlist.txt";
            Object hasher = new LousyStringHasher();
            PrintStream pStream = System.out;
            boolean isFoo = false;

            for(int i = 0; i < entry.length - 1; ++i) {
                if (entry[i].equals("-degenerate")) {
                    hasher = new DegenerateStringHasher();
                } else if (entry[i].equals("-lousy")) {
                    hasher = new LousyStringHasher();
                } else if (entry[i].equals("-better")) {
                    hasher = new BetterStringHasher();
                } else if (entry[i].equals("-quiet")) {
                    pStream = new PrintStream(new NullOutputStream());
                    isFoo = true;
                } else if (entry[i].equals("-wordlist")) {
                    ++i;
                    if (i >= entry.length - 1) {
                        showUsageMessage();
                        return;
                    }

                    fileName = entry[i];
                }
            }

            if (entry[entry.length - 1].charAt(0) == '-') {
                showUsageMessage();
            } else {
                try {
                    long start = System.currentTimeMillis();
                    (new Checker()).check(type, fileName, (StringHasher)hasher, pStream);
                    long stop = System.currentTimeMillis();
                    if (isFoo) {
                        System.out.println("Checker ran in " + (stop - start) + "ms");
                    }
                } catch (IOException var11) {
                    var11.printStackTrace();
                }

            }
        }
    }

    private static void showUsageMessage() {
        System.out.println("Usage: java SpellCheck [options] inputFilename");
        System.out.println();
        System.out.println("    options");
        System.out.println("    -------");
        System.out.println("    -degenerate");
        System.out.println("        runs the spell checker with the degenerate word hashing algorithm");
        System.out.println();
        System.out.println("    -lousy");
        System.out.println("        runs the spell checker with a lousy word hashing algorithm (default)");
        System.out.println();
        System.out.println("    -better");
        System.out.println("        runs the spell checker with a better word hashing algorithm");
        System.out.println();
        System.out.println("    -quiet");
        System.out.println("        runs the spell checker without any output, reporting the total time");
        System.out.println("        taken to load the dictionary and perform the spell check");
        System.out.println();
        System.out.println("    -wordlist wordlistFilename");
        System.out.println("        runs the spell checker using the wordlist specified, rather than");
        System.out.println("        the default (wordlist.txt)");
        System.out.println();
        System.out.println("    example");
        System.out.println("    -------");
        System.out.println("    java SpellCheck -wordlist biglist.txt -better -quiet big-input.txt");
        System.out.println("        executes the spell checker using the wordlist 'biglist.txt', the");
        System.out.println("        better word hashing algorithm, in quiet mode (i.e. no output),");
        System.out.println("        on the input file 'big-input.txt'");
    }
}
