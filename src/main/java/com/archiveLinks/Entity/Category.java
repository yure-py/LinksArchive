package com.archiveLinks.Entity;

import com.archiveLinks.Utility.Validate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

public class Category {
    private final static Path base = Paths.get("user.dir");
    private final static Path dataPathDir = Paths.get(base+"/src/main/java/com/archiveLinks/Data");

    // garante que o Data existe e está criado
    static {
        if (!Files.exists(dataPathDir)){
            try {
                Files.createDirectory(dataPathDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Boolean createCategory(String name) {
        if (Validate.isNotNullOrDigitFirst(name))
            return false;
        try {
            Path newFile = Paths.get(dataPathDir + "/" + name);
            Files.createFile(newFile);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    public static Boolean deleteCategory(String name) throws IOException {
        Path searchedCategory = Paths.get(dataPathDir + "/" + name);

        if (Validate.isNotNullOrDigitFirst(name) || Files.isDirectory(searchedCategory))
            return false;

        return Files.deleteIfExists(searchedCategory);
    }

    static public boolean addLink(String category, String linkName, String URL) throws IOException {
        Path categoryFile = Paths.get(dataPathDir + "/" + category);

        // Mover para um try catch com exceção personalizada
        if (!Files.exists(categoryFile)) {
            System.out.println("Arquivo não existe!");
            return false;
        }
        if (!Files.isWritable(categoryFile)) {
            System.out.println("Sem permissão de escrita");
            return false;
        }

        // Escrever
        String content = String.format("%s,%s\n", linkName, URL);

        try {
            Files.write(categoryFile, content.getBytes(), APPEND);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
    
    static public boolean deleteLink(String category, String linkName) throws IOException {
        return true;
    }

    // gets
    public static String getDataPath() {
        return dataPathDir.toString();
    }
}
