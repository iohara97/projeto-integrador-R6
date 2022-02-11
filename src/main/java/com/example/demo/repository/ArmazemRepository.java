package com.example.demo.repository;

import com.example.demo.entity.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazemRepository extends JpaRepository <Armazem, Long> {
}
