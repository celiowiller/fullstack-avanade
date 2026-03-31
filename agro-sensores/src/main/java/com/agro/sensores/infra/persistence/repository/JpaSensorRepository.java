package com.agro.sensores.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.SensorEntity;

public interface JpaSensorRepository extends JpaRepository<SensorEntity, String> {
 /*
  * ao praticar a "extensão" com JpaRepository
  * save()
  * findById()
  * findAll()
  * deleteById()
  * count()
  * 
  * */
}
