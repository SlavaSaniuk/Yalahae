package me.saniukvyacheslav.util.file;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Different utilities methods for working with files and directories.
 */
@UtilityClass
public final class FileUtils {

    /**
     * Check given file system path.
     * @param aPath - file system path.
     * @throws InvalidPathException - If given path is null, empty or invalid file system path.
     */
    public static void checkPath(String... aPath) throws InvalidPathException {
        try {

            if (aPath.length == 0) throw new IllegalArgumentException();
            StringBuilder sb = new StringBuilder();
            Arrays.asList(aPath).forEach(subPath -> {
                if (subPath != null && !subPath.isEmpty()) sb.append(subPath);
            });
            if (sb.toString().isEmpty()) throw new NullPointerException();

            Paths.get(sb.toString());
        }catch (NullPointerException e) {
            throw new InvalidPathException("File path [aPath] must be not null.", "");
        }catch (IllegalArgumentException e) {
            throw new InvalidPathException("File path [aPath] must be not empty.", "");
        }
    }

    /**
     * Check specified file.
     * First: check for null.
     * Second: Check if specified file exist.
     * Third: Check if specified file is file.
     * @param aFile - file to check.
     * @throws NullPointerException - if specified file is null.
     * @throws IllegalArgumentException - if specified file not exist or not is a file.
     */
    public static void checkFile(File aFile) {
        // Check for null:
        Objects.requireNonNull(aFile, "File [aFile] must be not null.");
        // Check if this file is exist:
        if (!(aFile.exists())) throw new IllegalArgumentException("File [aFile] must exist.");
        // Check if these files are directories:
        if (!(aFile.isFile())) throw new IllegalArgumentException("File [aFile] must be a file.");
    }

    /**
     * Check specified directory.
     * First: check for null.
     * Second: Check if specified directory exist.
     * Third: Check if specified directory is directory.
     * @param aDirectory - directory to check.
     * @throws NullPointerException - if specified directory is null.
     * @throws IllegalArgumentException - if specified directory not exist or not is a directory.
     */
    public static void checkDirectory(File aDirectory) {
        // Check for null:
        Objects.requireNonNull(aDirectory, "File [aDirectory] must be not null.");
        // Check if this file is exist:
        if (!(aDirectory.exists())) throw new IllegalArgumentException("File [aDirectory] must exist.");
        // Check if these files are directories:
        if (!(aDirectory.isDirectory())) throw new IllegalArgumentException("File [aDirectory] must be a directory.");
    }

    /**
     * Create empty copy of source directory with all attributes in destination directory.
     * @param aSourceDirectory - a source directory.
     * @param aDestinationDirectory - a destination directory.
     * @return - empty copy of source directory.
     * @throws IOException - If IO exception occurs.
     * @throws NullPointerException - if source or destination directory is null.
     * @throws IllegalArgumentException - if source or destination directory is not directory or not exist.
     */
    public static File makeEmptyCopyOfDirectory(File aSourceDirectory, File aDestinationDirectory) throws IOException {
        // Check parameters:
        try { // aSourceDirectory
            FileUtils.checkDirectory(aSourceDirectory);
        }catch (NullPointerException e) {
            throw new NullPointerException("File [aSourceDirectory] must be not null.");
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("File [aSourceDirectory] must be no null.");
        }
        try { // aDestinationDirectory
            FileUtils.checkDirectory(aDestinationDirectory);
        }catch (NullPointerException e) {
            throw new NullPointerException("File [aDestinationDirectory] must be not null.");
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("File [aDestinationDirectory] must be no null.");
        }

        // Create empty copy:
        Path copyPath = Paths.get(aDestinationDirectory.getAbsolutePath(),
                aSourceDirectory.getName());
        Files.copy(aSourceDirectory.toPath(), copyPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        return copyPath.toFile();
    }

    /**
     * Copy source file to destination directory.
     * @param aSourceFile - source file.
     * @param aDestinationDirectory - destination directory.
     * @return - copy of source file.
     * @throws IOException - If IO exception occurs.
     * @throws NullPointerException - if source file or destination directory is null.
     * @throws IllegalArgumentException - if source file is not exist of not file or destination directory is not directory or not exist.
     */
    public static File copyFileToDirectory(File aSourceFile, File aDestinationDirectory) throws IOException {
        // Check parameters:
        try { // aSourceFile:
            FileUtils.checkFile(aSourceFile);
        }catch (NullPointerException e) {
            throw new NullPointerException("File [aSourceFile] must be not null.");
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("File [aSourceFile] must be a file and exist.");
        }
        try { // aDestinationDirectory:
            FileUtils.checkDirectory(aDestinationDirectory);
        }catch (NullPointerException e) {
            throw new NullPointerException("File [aDestinationDirectory] must be not null.");
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("File [aDestinationDirectory] must be no null.");
        }

        // Copy file:
        Path copyPath = Paths.get(aDestinationDirectory.getAbsolutePath(),
                aSourceFile.getName());
        Files.copy(aSourceFile.toPath(), copyPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        return copyPath.toFile();
    }

    /**
     * Delete directory and all content in it.
     * @param aDirectory - directory to delete.
     * @throws IOException - If IO exception occurs.
     * @throws NullPointerException - if directory is null.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteDirectory(File aDirectory) throws IOException {
        // Check parameters:
        Objects.requireNonNull(aDirectory, "File [aDirectory] must be not null.");

        // Get files from directory:
        File[] contents = aDirectory.listFiles();
        if (contents != null) {
            for (File f : contents) {
                FileUtils.deleteDirectory(f);
            }
        }
        aDirectory.delete();
    }

    /**
     * Copy source directory to destination directory.
     * @param aSourceDirectory - source directory.
     * @param aDestinationDirectory - destination directory.
     * @param isRecursive - recursive coping.
     * @return - copied directory.
     * @throws IOException - if IO exception occurs.
     * @throws NullPointerException - if source or destination directory is null.
     * @throws IllegalArgumentException - if source or destination directory is not directory or not exist.
     */
    public static File copyDirectory(File aSourceDirectory, File aDestinationDirectory, boolean isRecursive) throws IOException {
        // Check parameters and make empty copy in directory:
        File emptyCopy;
        emptyCopy = FileUtils.makeEmptyCopyOfDirectory(aSourceDirectory, aDestinationDirectory);

        // Iterate through all files in source directory:
        File[] sourceFiles = aSourceDirectory.listFiles();
        if (sourceFiles != null) {
            for (File file : sourceFiles) {
                if (Files.isSymbolicLink(file.toPath())) continue; // Avoid following symbolic links;
                if (Files.isRegularFile(file.toPath(), LinkOption.NOFOLLOW_LINKS)) FileUtils.copyFileToDirectory(file, emptyCopy);
                if (Files.isDirectory(file.toPath(), LinkOption.NOFOLLOW_LINKS)) {
                    if (isRecursive) FileUtils.copyDirectory(file, emptyCopy, true);
                }
            }
        }

        return emptyCopy;
    }

    /**
     * Create directory by given path.
     * If directory already exist by given path, method return it.
     * @param aPath - new directory file system path.
     * @return - created directory.
     * @throws IOException - If IO exception occurs.
     * @throws NullPointerException - if given path in null.
     * @throws IllegalArgumentException - if given path is invalid file system path or file with same path already exist.
     */
    public static File createDirectory(String aPath) throws IOException {
        try { // Check parameters:
            Objects.requireNonNull(aPath, "String [aPath] must be not null.");
            Path dirPath = Paths.get(aPath);
            if (Files.exists(dirPath, LinkOption.NOFOLLOW_LINKS)) { // Check if existed:
                if (Files.isDirectory(dirPath)) return dirPath.toFile();
                else
                    throw new IllegalArgumentException(String.format("File with same path [%s] already exist.", aPath));
            }else
                return Files.createDirectories(dirPath).toFile();
        }catch (InvalidPathException e) {
            throw new IllegalArgumentException("Directory path [%s] must be valid file system path.");
        }
    }

}
