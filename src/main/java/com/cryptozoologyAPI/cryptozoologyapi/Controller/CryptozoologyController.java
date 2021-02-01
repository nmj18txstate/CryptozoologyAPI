package com.cryptozoologyAPI.cryptozoologyapi.Controller;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Service.CryptozoologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zoo")

public class CryptozoologyController {

    @Autowired
    CryptozoologyService cryptozoologyService;

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAnimal(@RequestBody Animal animal){
     return cryptozoologyService.addAnimal(animal);
    }

    @GetMapping("/animal")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getallAnimals(){
        return cryptozoologyService.getallAnimals();
    }
}
