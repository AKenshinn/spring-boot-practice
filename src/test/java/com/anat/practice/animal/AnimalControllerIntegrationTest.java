package com.anat.practice.animal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {

  @Mock
  AnimalService animalService;

  @InjectMocks
  AnimalController animalController;

  private MockMvc mockMvc;

  @BeforeAll
  public static void setUpClass() {
    AnimalMockHelper.initial();
  }
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();
  }

  @Test
  public void testFindAll() throws Exception {
    when(animalService.findAll()).thenReturn(AnimalMockHelper.findAll());

    mockMvc.perform(get("/animals"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", Matchers.equalTo(4)))
        .andExpect(jsonPath("$[?(@.name == 'Bug1')].type").value(AnimalType.BUG.name()))
        .andExpect(jsonPath("$[?(@.name == 'Fish1')].type").value(AnimalType.FISH.name()));
  }

  @Test
  public void testFindByNameContains() throws Exception {
    when(animalService.findByNameContains(any(String.class))).then(i -> AnimalMockHelper.findByNameContains(i.getArgument(0)));

    mockMvc.perform(get("/animals/search?name=bug"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()", Matchers.equalTo(2)))
        .andExpect(jsonPath("$[?(@.name == 'Bug1')]", Matchers.notNullValue()))
        .andExpect(jsonPath("$[?(@.name == 'Bug2')]", Matchers.notNullValue()));
  }

}
