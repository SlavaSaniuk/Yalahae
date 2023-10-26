package me.saniukvyacheslav.util.string.generation;

/**
 * EnglishStringsGenerator class instances used to generate random strings with english letters.
 * Use {@link EnglishStringsGenerator#generate(int)} method to generate strings.
 */
public class EnglishStringsGenerator extends StringsGenerator {

    /**
     * Construct new instance of {@link EnglishStringsGenerator} class.
     * By default, generator will use only lowercase letters in strings.
     */
    public EnglishStringsGenerator() {
        super("abcdefghijklmnopqrstuvwxyz");
    }

}
