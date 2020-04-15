package com.anat.practice.util.csv;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.InputMismatchException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CsvReaderUtilsTest {

  private MockMultipartFile csvFile;
  private MockMultipartFile notCsvFile;

  @Autowired
  private CsvReaderUtils csvReaderUtils;

  @BeforeEach
  public void setUp() {
    csvFile = new MockMultipartFile("bug", "bug.csv", null
        , "Common Butterfly,Flying,160".getBytes());

    notCsvFile = new MockMultipartFile("bug", "bug.text", null
        , "This is text file".getBytes());
  }

  @Test
  public void testReadCsvFileShouldCorrectly() throws Exception {
    List<List<String>> data = csvReaderUtils.read(csvFile);
    assertThat(data.size(), is(equalTo(1)));

    List<String> firstLine = data.get(0);
    assertThat(firstLine.contains("Common Butterfly"), is(true));
    assertThat(firstLine.contains("Flying"), is(true));
    assertThat(firstLine.contains("160"), is(true));
  }

  @Test
  public void testReadNotCsvShouldThrowInputMismatchException() throws Exception {
    Assertions.assertThrows(InputMismatchException.class, () -> {
      csvReaderUtils.read(notCsvFile);
    });
  }

}
