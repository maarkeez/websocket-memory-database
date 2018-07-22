package com.memory.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class CustomData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long data1;
	private Long data2;

}
