package me.saniukvyacheslav.util.string;

import me.saniukvyacheslav.util.string.generation.RussianStringsGenerator;
import me.saniukvyacheslav.util.string.generation.StringsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class StringsGeneratorTests {

    @Test
    void generate_20strings_shouldGenerate20Strings() {
        StringsGenerator generator = new RussianStringsGenerator();
        generator.useLowerCaseLetters(false);
        generator.useUpperCaseLetters(false);
        generator.useNumbers(false);
        generator.useSpecialSymbols(false);
        int size = 20;
        List<String> generatedStrings = new LinkedList<>();
        generator.useLowerCaseLetters(true);

        for (int i = 0; i < size; i++) {
            if (i > 5) generator.useUpperCaseLetters(true);
            if (i > 10) generator.useNumbers(true);
            if (i > 15) generator.useSpecialSymbols(true);
            generatedStrings.add(generator.generate(10));
        }

        Assertions.assertEquals(size, generatedStrings.size());
        generatedStrings.forEach(System.out::println);
    }

}
