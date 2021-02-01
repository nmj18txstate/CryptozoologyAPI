package com.cryptozoologyAPI.cryptozoologyapi.ServiceTest;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import com.cryptozoologyAPI.cryptozoologyapi.Service.CryptozoologyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptozoologyServiceTest {
    @Mock
    private CryptozoologyRepository cryptozoologyRepository;

    @InjectMocks
    private CryptozoologyService cryptozoologyService;

    @Test
    public void cryptozoology_addAnimal(){
       Animal animal =  new Animal("Fish","swimming");
        when(cryptozoologyRepository.save(any()))
                .thenReturn(animal);
         Animal Fish = cryptozoologyService.addAnimal(new Animal("Fish","swimming"));
         verify(cryptozoologyRepository,times(1)).save(any());
         assertEquals(animal,Fish);
         assertEquals(animal.hashCode(),Fish.hashCode());

    }
}
