package me.saniukvyacheslav.util.file;

import java.io.File;
import java.io.IOException;

public class TestingFileUtils {

    public static File createFile(String aPathToFile) {
        File file = new File(aPathToFile);
        boolean isCreated = false;
        try {
             isCreated = file.createNewFile();
             if (!(isCreated)) throw new RuntimeException("File is not created.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    public static File deleteFile(String aPathToFile) {
        File file = new File(aPathToFile);
        boolean isDeleted = false;
        isDeleted = file.delete();
        if (!(isDeleted)) throw new RuntimeException("File is not created.");

        return file;
    }

}
