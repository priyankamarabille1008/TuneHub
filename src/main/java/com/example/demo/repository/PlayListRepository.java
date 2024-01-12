package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PlayList;
public interface PlayListRepository extends JpaRepository<PlayList, Integer>{

}
