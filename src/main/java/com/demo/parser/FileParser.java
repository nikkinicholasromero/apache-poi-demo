package com.demo.parser;

import java.util.List;

interface FileParser {
    boolean isSupported(String filePath);
    List<List<List<String>>> parse(String filePath);
}
