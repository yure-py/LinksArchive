package com.archiveLinks.Entity;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
class ArchiveTest {
    Path mockCategoryPath = Paths.get(Category.getDataPath()+"/"+"mock");

    @Test
    void createCategory() throws IOException {
        Category.createCategory(mockCategoryPath.getFileName().toString());
        assertTrue(Files.exists(mockCategoryPath));
        Files.delete(mockCategoryPath);
    }

    @Test
    void deleteCategory() throws IOException {
        Files.createFile(mockCategoryPath);
        assertTrue(Category.deleteCategory(mockCategoryPath.getFileName().toString()));
        assertFalse(Files.exists(mockCategoryPath));
    }


    @Test
    void addLink_oneLink_validNewLink() throws IOException {
        Files.createFile(mockCategoryPath);

        String fileName = mockCategoryPath.getFileName().toString();
        String[] l1 = new String[]{"link1", "www.link1.com.br"};
        Category.addLink(fileName, l1[0], l1[1]);

        String[] result = Files.readAllLines(mockCategoryPath).getFirst().split(",");

        assertEquals(l1[0], result[0]);
        assertEquals(l1[1], result[1]);
    }


}