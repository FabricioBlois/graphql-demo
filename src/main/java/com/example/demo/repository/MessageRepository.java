package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByBeerIdIn(Set<Integer> beerIds);

}
