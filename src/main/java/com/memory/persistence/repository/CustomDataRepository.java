package com.memory.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.memory.persistence.entity.CustomData;

public interface CustomDataRepository extends CrudRepository<CustomData, Long>{

}
