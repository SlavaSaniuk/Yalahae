package me.saniukvyacheslav.util.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class IOUtilsTests {

    @Test
    void openReader_fileIsNull_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> IOUtils.openReader(null));
    }

    @Test
    void openReader_fileIsNotExist_shouldThrowIOE() {
        File file = new File("notexisted.filex");
        Assertions.assertThrows(IOException.class, () -> IOUtils.openReader(file));
    }

    @Test
    void openWriter_fileIsNull_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, () -> IOUtils.openWriter(null));
    }

    @Test
    void openWriter_fileIsNotExist_shouldThrowIOE() {
        File file = new File("notexisted.filex");
        Assertions.assertThrows(IOException.class, () -> IOUtils.openWriter(file));
    }

    @Test
    void fileContent_fileIsNull_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, ()-> IOUtils.fileContent(null));
    }

    @Test
    void fileContent_fileIsNotExist_shouldThrowIOE() {
        File file = new File("notexisted.filex");
        Assertions.assertThrows(IOException.class, ()-> IOUtils.fileContent(file));
    }


}
