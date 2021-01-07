package com.example.demo.repository;

import com.example.demo.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    List<Brand> findAllByIdIn(Set<Integer> ids);

}
