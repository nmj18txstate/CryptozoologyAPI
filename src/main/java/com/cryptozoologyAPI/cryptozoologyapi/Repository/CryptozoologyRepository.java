package com.cryptozoologyAPI.cryptozoologyapi.Repository;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptozoologyRepository extends JpaRepository<Animal,Long>{
        List<Animal> findByMoodAndType(String mood, String Type);
}
