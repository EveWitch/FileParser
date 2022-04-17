package com.springbootparser.parser;

import java.io.File;
import java.util.List;

public interface Parser {
    List<Model> parseFile(File file);
}
