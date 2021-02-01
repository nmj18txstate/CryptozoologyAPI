package com.cryptozoologyAPI.cryptozoologyapi.IntegrationTest;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CryptozoologyAPIControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CryptozoologyRepository cryptozoologyRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addAnimalintoZoo() throws Exception {
        Animal animal = new Animal("Fish", "swimming"); //flying, swimming, walking;
        String animalJson = mapper.writeValueAsString(animal);
        mockMvc.perform(post("/zoo/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Fish"))
                .andExpect(jsonPath("$.type").value("swimming"))
                .andDo(print());
    }

    @Test
    public void cryptozoology_getAllAnimal() throws Exception {
    cryptozoologyRepository.save(new Animal("Dog","walking"));
    cryptozoologyRepository.save(new Animal("Cat","walking"));
    cryptozoologyRepository.save(new Animal("Bird","flying"));
        mockMvc.perform(get("/zoo/animal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dog"))
                .andExpect(jsonPath("$[0].type").value("walking"))
                .andDo(print());

    }
}
