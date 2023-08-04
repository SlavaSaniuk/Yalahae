package me.saniukvyacheslav.util.file;

import me.saniukvyacheslav.util.string.YalahaeStrings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This utilities class defines helpful static methods for working with {@link File} files contents.
 */
public final class IOUtils {

    /**
     * Private constructor.
     */
    private IOUtils() {
        throw new UnsupportedOperationException(
                YalahaeStrings.utilitiesClassMessage(IOUtils.class));
    }

    /**
     * Open reader for specified file.
     * @param aFile - File for reading.
     * @return - Reader for specified file.
     * @throws IOException - If specified file is null, or cannot be read, or not exist.
     */
    public static BufferedReader openReader(File aFile) throws IOException {
        // Check file:
        Objects.requireNonNull(aFile);
        // Check for file will be read:
        if (!(aFile.canRead())) throw new IOException(
                String.format("File: [%s] cannot be read.", aFile.getPath())
        );
        // return BufferedReader:
        return new BufferedReader(new FileReader(aFile));
    }

    /**
     * Open writer for specified file.
     * @param aFile - File for writing.
     * @return - Writer for specified file.
     * @throws IOException - If specified file is null, or cannot be written, or not exist.
     */
    public static BufferedWriter openWriter(File aFile) throws IOException {
        // Check file:
        Objects.requireNonNull(aFile);
        // Check for file will be written:
        if (!(aFile.canWrite())) throw new IOException(
                String.format("File: [%s] cannot be written.", aFile.getPath())
        );
        return new BufferedWriter(new FileWriter(aFile));
    }

    /**
     * Read file content as String.
     * @param aFile - file to read.
     * @return - file content as String.
     * @throws IOException - If IO exceptions occur.
     */
    public static String fileContent(File aFile) throws IOException {
        // Check file for null:
        Objects.requireNonNull(aFile);
        // File content:
        StringBuilder fileContent = new StringBuilder();

        // Read file content:
        BufferedReader reader = IOUtils.openReader(aFile);
        String currStr; String lastStr = null;
        boolean readFlag = true;
        while (readFlag) {

            // Read line from file:
            currStr = reader.readLine();
            // If current string is null, EOF was reached:
            if (currStr != null) {
                if (lastStr != null) fileContent.append(lastStr).append(System.lineSeparator());
                lastStr = currStr.concat("");
            }else {
                fileContent.append(lastStr);
                readFlag = false;
            }
        }

        // Close reader:
        reader.close();

        // Return file as String:
        return fileContent.toString().equals("null") ? "" : fileContent.toString();
    }

    /**
     * Read file content as list of Strings.
     * @param aFile - file to read.
     * @return - list of strings.
     * @throws IOException - If IO exceptions occur.
     */
    public static List<String> fileContentAsList(File aFile) throws IOException {
        // Check file:
        Objects.requireNonNull(aFile);
        // File content:
        List<String> fileContent = new ArrayList<>();
        // Read file content
        BufferedReader reader = IOUtils.openReader(aFile);
        String str;
        while ((str = reader.readLine()) != null)
            fileContent.add(str);

        // Close reader:
        reader.close();
        // Return file content:
        return fileContent;
    }

    public static String[] fileContentAsArray(File aFile) throws IOException {
        return IOUtils.fileContentAsList(aFile).toArray(new String[0]);
    }
}
