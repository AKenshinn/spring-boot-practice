package com.anat.practice.util.csv;

import static org.junit.Assert.assertThat;

import java.util.InputMismatchException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CsvReaderUtilsTest {

  private MockMultipartFile csvFile;
  private MockMultipartFile notCsvFile;

  @Autowired
  private CsvReaderUtils csvReaderUtils;

  @Before
  public void setUp() {
    csvFile = new MockMultipartFile("bug", "bug.csv", null
        , "Common Butterfly,Flying,160".getBytes());

    notCsvFile = new MockMultipartFile("bug", "bug.text", null
        , "This is text file".getBytes());
  }

  @Test
  public void testReadCsvFileShouldCorrectly() throws Exception {
    List<List<String>> data = csvReaderUtils.read(csvFile);
    assertThat(data.size(), Matchers.equalTo(1));

    List<String> firstLine = data.get(0);
    assertThat(firstLine.contains("Common Butterfly"), Matchers.equalTo(true));
    assertThat(firstLine.contains("Flying"), Matchers.equalTo(true));
    assertThat(firstLine.contains("160"), Matchers.equalTo(true));
  }

  @Test(expected = InputMismatchException.class)
  public void testReadNotCsvShouldThrowInputMismatchException() throws Exception {
    csvReaderUtils.read(notCsvFile);
  }

}
