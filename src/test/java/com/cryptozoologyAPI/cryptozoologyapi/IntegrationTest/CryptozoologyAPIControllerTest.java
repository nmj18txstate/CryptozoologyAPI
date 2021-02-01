package com.cryptozoologyAPI.cryptozoologyapi.IntegrationTest;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptozoologyAPIControllerTest {
    @Autowired
    MockMvc mockMvc;

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
}
