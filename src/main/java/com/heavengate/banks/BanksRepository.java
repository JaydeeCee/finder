package com.heavengate.banks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BanksRepository extends JpaRepository<BanksEntity, Long> {
	BanksEntity findByCode(String code);
	List<BanksEntity> findAll();
}
