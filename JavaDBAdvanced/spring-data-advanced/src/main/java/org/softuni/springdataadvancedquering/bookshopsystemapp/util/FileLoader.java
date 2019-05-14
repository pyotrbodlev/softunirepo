package org.softuni.springdataadvancedquering.bookshopsystemapp.util;

public class FileLoader {
    public static String getFilePath(Class clazz){
        String fileLocation = "C:\\Users\\admin\\Documents\\GitHub\\softunirepo\\JavaDBAdvanced\\spring-data-advanced\\src\\main\\resources\\files\\";

        switch (clazz.getSimpleName()){
            case "Author":
                return fileLocation + "authors.txt";
            case "Book":
                return fileLocation + "books.txt";
            case "Category":
                return fileLocation + "categories.txt";
        }

        return null;
    }
}
