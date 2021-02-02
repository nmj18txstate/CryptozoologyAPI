package com.cryptozoologyAPI.cryptozoologyapi.IntegrationTest;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Model.AnimalRequest;
import com.cryptozoologyAPI.cryptozoologyapi.Model.Habitat;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.HabitatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    HabitatRepository habitatRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addAnimalintoZoo() throws Exception {
        Animal animal = new Animal("Fish", "swimming","Unhappy"); //flying, swimming, walking;
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
    cryptozoologyRepository.save(new Animal("Dog","walking","Unhappy"));
    cryptozoologyRepository.save(new Animal("Cat","walking","Unhappy"));
    cryptozoologyRepository.save(new Animal("Bird","flying","Unhappy"));
        mockMvc.perform(get("/zoo/animal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dog"))
                .andExpect(jsonPath("$[0].type").value("walking"))
                .andExpect(jsonPath("$[1].name").value("Cat"))
                .andExpect(jsonPath("$[1].type").value("walking"))
                .andExpect(jsonPath("$[2].name").value("Bird"))
                .andExpect(jsonPath("$[2].type").value("flying"))
                .andDo(print());
    }

    @Test
    public void cryptozoology_giveTreat() throws Exception {
        Animal animal = new Animal("Fish", "swimming","Happy"); //flying, swimming, walking;
        String animalJson = mapper.writeValueAsString(animal);
        cryptozoologyRepository.save(animal);
        mockMvc.perform(put("/zoo/animal/" + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fish"))
                .andExpect(jsonPath("$.type").value("swimming"))
                .andExpect(jsonPath("$.mood").value("Happy"))
                .andDo(print());
    }

    @Test
    public void create_emptyHabitat() throws Exception {
        Habitat habitat = new Habitat("nest", "flying");
        String habitatJson = mapper.writeValueAsString(habitat);
        mockMvc.perform(post("/zoo/habitat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(habitatJson))
               // .andExpect(content().string(habitatJson))
                .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("nest"))
               .andExpect(jsonPath("$.type").value("flying"))
                .andDo(print());

    }

    @Test
    public void compatible_Habitat() throws Exception {
        Habitat habitat = new Habitat("nest", "flying");
        Animal bird = new Animal("Bird", "flying", "UnHappy");
        habitatRepository.save(habitat);
        cryptozoologyRepository.save(bird);
        AnimalRequest animalRequest = new AnimalRequest(bird.getId());
        String animalRequestJson = mapper.writeValueAsString(animalRequest);
       MvcResult mvcResult = mockMvc.perform(put("/zoo/habitat/"+habitat.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalRequestJson))
                .andExpect(status().isOk())
               .andReturn();
       // habitat.setAnimal(bird);
       Habitat habitatResponse = mapper.readValue(mvcResult.getResponse().getContentAsString(), Habitat.class);
       assertEquals(bird,habitatResponse.getAnimal());
        MvcResult mvcResultfromDB = mockMvc.perform(get("/zoo/habitat/"+habitat.getId()))
               .andExpect(status().isOk()).andReturn();
        Habitat habitatFromDB = mapper.readValue(mvcResultfromDB.getResponse().getContentAsString(), Habitat.class);
        assertEquals(bird, habitatFromDB.getAnimal());

    }

    @Test
    public void incompatible_habitat() throws Exception {
        Habitat habitat = new Habitat("ocean", "swimming");
        Animal bird = new Animal("Bird", "flying", "Happy");
        habitatRepository.save(habitat);
        cryptozoologyRepository.save(bird);

        AnimalRequest animalRequest = new AnimalRequest(bird.getId());
        String animalRequestJson = mapper.writeValueAsString(animalRequest);
        mockMvc.perform(put("/zoo/habitat/"+habitat.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalRequestJson))
                .andExpect(status().isOk());

       mockMvc.perform(get("/zoo/animal/"+bird.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(bird.getName()))
                .andExpect(jsonPath("$.type").value(bird.getType()))
               .andExpect(jsonPath("$.mood").value("UnHappy"))
                .andDo(print());

    }

    @Test
    public void occupied_Habitat() throws Exception {
        Habitat habitat = new Habitat("ocean", "swimming");
        Animal bird = new Animal("Bird", "flying", "Happy");
        habitat.setAnimal(bird);
        habitatRepository.save(habitat);
        cryptozoologyRepository.save(bird);

        Animal fish = new Animal("Fish", "swimming", "UnHappy");
        cryptozoologyRepository.save(fish);
        AnimalRequest animalRequest = new AnimalRequest(fish.getId());
        String animalRequestJson = mapper.writeValueAsString(animalRequest);
        mockMvc.perform(put("/zoo/habitat/"+habitat.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalRequestJson))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getAnimalByMoodAndType() throws Exception {
        Animal animal1 = new Animal("Dog","walking","Unhappy");
        Animal animal2 =  new Animal("Cat","walking","Unhappy");
        Animal animal3 = new Animal("Bird","flying","Unhappy");
        cryptozoologyRepository.save(animal1);
        cryptozoologyRepository.save(animal2);
        cryptozoologyRepository.save(animal3);

       // List<Animal> actualList = cryptozoologyRepository.findByMoodAndType("Unhappy","walking");
        List<Animal> expected = List.of(animal1, animal2);

        MvcResult mvcResult = mockMvc.perform(get("/zoo/animal-type-mood?mood=Unhappy&type=walking"))
                .andExpect(status().isOk())
               .andReturn();
        
        assertEquals(expected,mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Animal>>() {
        }));




    }
}
