package com.anat.practice.animal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testFindAll() throws Exception {
    mockMvc.perform(get("/animals"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", Matchers.equalTo(160)))
        .andExpect(jsonPath("$[?(@.name == 'Common Butterfly')].value").value(160))
        .andExpect(jsonPath("$[?(@.name == 'Common Butterfly')].location").value("Flying"));
  }

  @Test
  public void testFindByNameContains() throws Exception {
    mockMvc.perform(get("/animals/search?name=common"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", Matchers.equalTo(2)))
        .andExpect(jsonPath("$[?(@.name == 'Common Butterfly')]", Matchers.notNullValue()))
        .andExpect(jsonPath("$[?(@.name == 'Common Bluebottle')]", Matchers.notNullValue()));
  }

}
