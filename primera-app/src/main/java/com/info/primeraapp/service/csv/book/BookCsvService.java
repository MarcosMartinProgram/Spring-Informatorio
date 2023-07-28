package com.info.primeraapp.service.csv.book;

import com.info.primeraapp.model.BookCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface BookCsvService {
    List<BookCsvRecord> convertCSV(File file) throws FileNotFoundException;
}
