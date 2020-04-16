package com.anat.practice.animal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeAll
  public static void setUpClass() {
    AnimalMockHelper.initial();
  }
  
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testFindAllShouldCorrectly() throws Exception {
    mockMvc.perform(get("/animals"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.animalList").isArray())
				.andExpect(jsonPath("$._embedded.animalList.length()", Matchers.equalTo(160)))
        .andExpect(jsonPath("$._embedded.animalList[?(@.name == 'Common Butterfly')].type").value(AnimalType.BUG.name()))
        .andExpect(jsonPath("$._embedded.animalList[?(@.name == 'Common Butterfly')].value").value(160));
  }

  @Test
  public void testFindByNameContainsShouldCorrectly() throws Exception {
    mockMvc.perform(get("/animals?name=common"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.animalList").isArray())
				.andExpect(jsonPath("$._embedded.animalList.length()", Matchers.equalTo(2)))
        .andExpect(jsonPath("$._embedded.animalList[?(@.name == 'Common Butterfly')]", Matchers.notNullValue()))
        .andExpect(jsonPath("$._embedded.animalList[?(@.name == 'Common Bluebottle')]", Matchers.notNullValue()));
  }

  @Test
  public void testFindByTypeShouldCorrectly() throws Exception {
    mockMvc.perform(get("/animals?type=BUG"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.animalList").isArray())
				.andExpect(jsonPath("$._embedded.animalList.length()", Matchers.equalTo(80)))
        .andExpect(jsonPath("$._embedded.animalList[*].type", Matchers.everyItem(is(AnimalType.BUG.name()))));
  }

}
