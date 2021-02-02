package com.cryptozoologyAPI.cryptozoologyapi.ServiceTest;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Model.Habitat;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.HabitatRepository;
import com.cryptozoologyAPI.cryptozoologyapi.Service.CryptozoologyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptozoologyServiceTest {
    @Mock
    private CryptozoologyRepository cryptozoologyRepository;

    @Mock
    private HabitatRepository habitatRepository;

    @InjectMocks
    private CryptozoologyService cryptozoologyService;

    @Test
    public void cryptozoology_addAnimal(){
       Animal animal =  new Animal("Fish","swimming","Unhappy");
        when(cryptozoologyRepository.save(any()))
                .thenReturn(animal);
         Animal Fish = cryptozoologyService.addAnimal(new Animal("Fish","swimming","Unhappy"));
         verify(cryptozoologyRepository,times(1)).save(any());
         assertEquals(animal,Fish);
         assertEquals(animal.hashCode(),Fish.hashCode());

    }

    @Test
    public void cryptozoology_getAllAnimal(){
        Animal animal = new Animal("Dog","walking","Unhappy");
        Animal animal1= new Animal("Cat","walking","Unhappy");
        Animal animal2= new Animal("Bird","flying","Unhappy");
        List<Animal> animals = new ArrayList<>();
        animals.add(animal);
        animals.add(animal1);
        animals.add(animal2);
        when(cryptozoologyRepository.findAll())
        .thenReturn(animals);
        assertEquals(animals,cryptozoologyService.getallAnimals());
    }

    @Test
    public void cryptozoology_updateAnimalMood(){
        Animal animal = new Animal("Dog","walking","Happy");
        when(cryptozoologyRepository.save(any()))
                .thenReturn(animal);
        when(cryptozoologyRepository.findById(any()))
                .thenReturn(Optional.of(new Animal("Dog", "walking", "Unhappy")));
        assertEquals(animal,cryptozoologyService.updateAnimal(1L,animal));
        verify(cryptozoologyRepository,times(1)).save(any());
    }

    @Test
    public void habitat_returnsEmptyHabitat(){
        Habitat habitat = new Habitat("nest", "flying");
        when(habitatRepository.save(any())).thenReturn(habitat);
        assertEquals(habitat, cryptozoologyService.createHabitat(habitat));
        verify(habitatRepository, times(1)).save(any());
    }

    @Test
    public void update_habitat_compatible() throws Exception {
        Habitat habitat = new Habitat("nest", "flying");
        Animal animal = new Animal("Bird","flying","UnHappy");
        habitat.setAnimal(animal);
        when(habitatRepository.save(any())).thenReturn(habitat);
        when(cryptozoologyRepository.findById(any())).thenReturn(Optional.of(animal));
        when(habitatRepository.findById(any())).thenReturn(Optional.of(habitat));
        assertEquals(habitat, cryptozoologyService.updateHabitat(1L, 1L));
        verify(habitatRepository,times(1)).save(any());

    }

    @Test
    public void getHabitatById(){
        Habitat habitat = new Habitat("nest", "flying");
        when(habitatRepository.findById(any())).thenReturn(Optional.of(habitat));
        assertEquals(habitat, cryptozoologyService.getHabitatById(1L));
        verify(habitatRepository, times(1)).findById(any());
    }

    @Test
    public void getAnimalById(){
        Animal animal = new Animal("Bird","flying","UnHappy");
        when(cryptozoologyRepository.findById(any())).thenReturn(Optional.of(animal));
        assertEquals(animal, cryptozoologyService.getAnimalById(1L));
        verify(cryptozoologyRepository, times(1)).findById(any());
    }

    @Test
    public void getAnimalByTypeAndMood(){
        Animal animal1 = new Animal("Dog","walking","Unhappy");
        Animal animal2 =  new Animal("Cat","walking","Unhappy");
        Animal animal3 = new Animal("Bird","flying","Unhappy");
        List<Animal> animalList = List.of(animal1, animal2, animal3);
        List<Animal> expected = List.of(animal1, animal2);


        when(cryptozoologyRepository.findAll()).thenReturn(animalList);
        assertEquals(expected, cryptozoologyService.getAnimalByTypeAndMood("Unhappy", "walking"));
        verify(cryptozoologyRepository, times(1)).findAll();
    }


}
