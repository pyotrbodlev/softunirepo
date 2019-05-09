package org.softuni.springdataadvancedquering.bookshopsystemapp.util;

import java.io.IOException;

public interface FileUtil {

    String[] getFileContent(String filePath) throws IOException;
}
