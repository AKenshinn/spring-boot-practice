package com.anat.practice.util.csv;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import com.opencsv.CSVReader;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvReaderUtils {

  private static Logger LOGGER = LoggerFactory.getLogger(CsvReaderUtils.class);
  private static final String EXTENSION_CSV = "csv";

	public List<List<String>> read(MultipartFile file) throws Exception {
    try {
      String extension = FilenameUtils.getExtension(file.getOriginalFilename());
      if (!EXTENSION_CSV.equals(extension.toLowerCase())) {
        throw new InputMismatchException("Input file is not csv file.");
      }

      /* Skip BOM character in CSV file */
      BOMInputStream bomIn = new BOMInputStream(file.getInputStream()
        , ByteOrderMark.UTF_8
        , ByteOrderMark.UTF_16BE
        , ByteOrderMark.UTF_16LE
        , ByteOrderMark.UTF_32BE
        , ByteOrderMark.UTF_32LE);
      
      /* Read CSV file */
      List<List<String>> csvData = new ArrayList<List<String>>();
      try (CSVReader csvReader = new CSVReader(new InputStreamReader(bomIn));) {
        String[] values = null;
        while ((values = csvReader.readNext()) != null) {
          if (values != null && values.length > 0) {
            csvData.add(Arrays.asList(values));
          }
        }
      }

      return csvData;
    } catch (InputMismatchException e) {
      LOGGER.error("Input file is not csv file: " + file.getName());
      throw e;
    } catch (Exception e) {
      LOGGER.error("Can't read input file: " + file.getName());
      throw e;
    }
  }

}
