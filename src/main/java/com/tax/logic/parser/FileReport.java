package com.tax.logic.parser;

import com.tax.db.entity.Report;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface FileReport {
    Report parseFile(String fileName) throws IOException, ParseException;

}
