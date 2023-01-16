package com.tax.db;

import java.io.IOException;

public class ParseFileException extends IOException {
    public ParseFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseFileException(String message) {
        super(message);
    }
}
