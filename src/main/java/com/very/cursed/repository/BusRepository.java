package com.very.cursed.repository;

import com.very.cursed.model.Buses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Buses, Integer> {
    List<Buses> findByinpark(boolean inpark);
}
