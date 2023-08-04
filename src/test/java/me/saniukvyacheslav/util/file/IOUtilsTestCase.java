package me.saniukvyacheslav.util.file;

import me.saniukvyacheslav.util.array.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IOUtilsTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtilsTestCase.class);

    private File testFile;

    @BeforeEach
    void beforeEach() {
        this.testFile = TestingFileUtils.createFile("test_file.tests");
    }

    @AfterEach
    void afterEach() {
        this.testFile = TestingFileUtils.deleteFile("test_file.tests");
    }

    @Test
    void openReader_readFromFileStr_shouldReadStr() {
        // Write str before:
        String testStr = "Hello world!";
        try(BufferedWriter writer = IOUtils.openWriter(this.testFile)) {
            writer.write(testStr);
            writer.flush();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

        // Read str:
        try(BufferedReader reader = IOUtils.openReader(this.testFile)) {
            String resultStr = reader.readLine();

            Assertions.assertNotNull(resultStr);
            Assertions.assertEquals(testStr, resultStr);
            LOGGER.debug(String.format("Read string: [%s];", resultStr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fileContent_fileWithText_shouldReturnText() {
        // File content:
        String line1 = "Hello world!" +System.lineSeparator();
        String line2 = "My name is Vyacheslav." +System.lineSeparator();
        String line3 = "Lorem ipsum dollar sit ammeter.";
        // Write file content:
        try(BufferedWriter writer = IOUtils.openWriter(this.testFile)) {
            writer.write(line1); writer.write(line2); writer.write(line3);
            writer.flush();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
        // Expected file content:
        String expectedFileContent = line1.concat(line2).concat(line3);
        LOGGER.debug(String.format("Expected file content: [%s%s%s]", System.lineSeparator(), expectedFileContent, System.lineSeparator()));

        // Read file content:
        try {
            String fileContent = IOUtils.fileContent(this.testFile);

            Assertions.assertNotNull(fileContent);
            Assertions.assertEquals(expectedFileContent, fileContent);
            LOGGER.debug(String.format("Actual file content: [%s%s%s]", System.lineSeparator(), fileContent, System.lineSeparator()));
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void fileContent_fileWithoutText_shouldReturnEmptyString() {

        // Expected file content:
        String expectedFileContent = "";

        // Read file content:
        try {
            String fileContent = IOUtils.fileContent(this.testFile);
            Assertions.assertNotNull(fileContent);
            Assertions.assertEquals(expectedFileContent, fileContent);
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void fileContentAsList_fileWithText_shouldReturnListOfStrings() {
        // File content:
        String line1 = "Hello world!" +System.lineSeparator();
        String line2 = "My name is Vyacheslav." +System.lineSeparator();
        String line3 = "Lorem ipsum dollar sit ammeter.";
        // Write file content:
        try(BufferedWriter writer = IOUtils.openWriter(this.testFile)) {
            writer.write(line1); writer.write(line2); writer.write(line3);
            writer.flush();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }


        // Expected file content:
        String expectedFileContent = line1.concat(line2).concat(line3);
        LOGGER.debug(String.format("Expected file content: [%s%s%s]", System.lineSeparator(), expectedFileContent, System.lineSeparator()));

        // Read file content:
        try {
            List<String> fileContent = IOUtils.fileContentAsList(this.testFile);
            Assertions.assertNotNull(fileContent);
            Assertions.assertFalse(fileContent.isEmpty());

            StringBuilder actualContent = new StringBuilder();
            fileContent.forEach((str) -> {
                if (!ArrayUtils.isLastElement(fileContent.toArray(), str))
                    actualContent.append(str).append(System.lineSeparator());
                else actualContent.append(str);
            });
            Assertions.assertEquals(expectedFileContent, actualContent.toString());
            LOGGER.debug(String.format("Actual file content: [%s%s%s]", System.lineSeparator(), actualContent, System.lineSeparator()));
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void fileContentAsList_fileWithoutText_shouldReturnEmptyListOfStrings() {
        // Read file content:
        try {
            List<String> fileContent = IOUtils.fileContentAsList(this.testFile);
            Assertions.assertNotNull(fileContent);
            Assertions.assertTrue(fileContent.isEmpty());
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void fileContentAsArray_fileWithText_shouldReturnArrayOfString() {
        // File content:
        String line1 = "Hello world!" +System.lineSeparator();
        String line2 = "My name is Vyacheslav." +System.lineSeparator();
        String line3 = "Lorem ipsum dollar sit ammeter.";
        // Write file content:
        try(BufferedWriter writer = IOUtils.openWriter(this.testFile)) {
            writer.write(line1); writer.write(line2); writer.write(line3);
            writer.flush();
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

        // Expected file content:
        String expectedFileContent = line1.concat(line2).concat(line3);
        LOGGER.debug(String.format("Expected file content: [%s%s%s]", System.lineSeparator(), expectedFileContent, System.lineSeparator()));

        // Read file content:
        try {
            String[] fileContent = IOUtils.fileContentAsArray(this.testFile);
            Assertions.assertNotNull(fileContent);
            Assertions.assertNotEquals(0, fileContent.length);

            StringBuilder actualContent = new StringBuilder();
            for (int i=0; i<fileContent.length; i++) {
                if (i==(fileContent.length-1)) actualContent.append(fileContent[i]);
                else actualContent.append(fileContent[i]).append(System.lineSeparator());
            }

            Assertions.assertEquals(expectedFileContent, actualContent.toString());
            LOGGER.debug(String.format("Actual file content: [%s%s%s]", System.lineSeparator(), actualContent, System.lineSeparator()));
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }

    }

    @Test
    void fileContentAsArray_fileWithoutText_shouldReturnEmptyArray() {
        // Expected file content:
        String expectedFileContent = "";

        // Read file content:
        try {
            String[] fileContent = IOUtils.fileContentAsArray(this.testFile);
            Assertions.assertNotNull(fileContent);
            Assertions.assertEquals(0, fileContent.length);

            StringBuilder actualContent = new StringBuilder();
            for (int i=0; i<fileContent.length; i++) {
                if (i==(fileContent.length-1)) actualContent.append(fileContent[i]);
                else actualContent.append(fileContent[i]).append(System.lineSeparator());
            }

            Assertions.assertEquals(expectedFileContent, actualContent.toString());
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
