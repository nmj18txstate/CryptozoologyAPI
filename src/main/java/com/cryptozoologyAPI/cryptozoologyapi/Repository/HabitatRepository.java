package com.cryptozoologyAPI.cryptozoologyapi.Repository;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {
}
