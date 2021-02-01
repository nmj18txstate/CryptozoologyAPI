package com.cryptozoologyAPI.cryptozoologyapi.Repository;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptozoologyRepository extends JpaRepository<Animal,Long>{

}
