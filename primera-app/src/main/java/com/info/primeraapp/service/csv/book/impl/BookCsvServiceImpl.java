package com.info.primeraapp.service.csv.book.impl;

import com.info.primeraapp.model.BookCsvRecord;
import com.info.primeraapp.service.csv.book.BookCsvService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class BookCsvServiceImpl implements BookCsvService {
    @Override
    public List<BookCsvRecord> convertCSV(File file) throws FileNotFoundException {
        List<BookCsvRecord> bookCsvRecordList =
                new CsvToBeanBuilder<BookCsvRecord>(new FileReader(file))
                        .withType(BookCsvRecord.class)
                        .build()
                        .parse();

        log.info("Convirtiendo CSV a lista de Libros");
        return bookCsvRecordList;

    }
}
