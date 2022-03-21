package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
	PublisherEntity findOneByCode(String code);
}
