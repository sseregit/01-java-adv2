package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("Files.exists(file) = " + Files.exists(file));

        try {
            Files.createFile(file);
            System.out.println("File created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + " File already exists");
        }

        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + " Directory already exists");
        }

//        Files.delete(file);
//        System.out.println("File deleted");

        System.out.println("Files.isRegularFile(file) = " + Files.isRegularFile(file));
        System.out.println("Files.isDirectory(directory) = " + Files.isDirectory(directory));
        System.out.println("file.getFileName() = " + file.getFileName());
        System.out.println("Files.size(file) = " + Files.size(file) + "bytes");

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved/renamed");

        System.out.println("Files.getLastModifiedTime(newFile) = " + Files.getLastModifiedTime(newFile));

        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("===== Attributes =====");
        System.out.println("attrs.creationTime() = " + attrs.creationTime());
        System.out.println("attrs.isDirectory() = " + attrs.isDirectory());
        System.out.println("attrs.isRegularFile() = " + attrs.isRegularFile());
        System.out.println("attrs.isSymbolicLink() = " + attrs.isSymbolicLink());
        System.out.println("attrs.size() = " + attrs.size());

    }
}
