package com.ggsoftware.opcserver.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ggsoftware.opcserver.entity.General;

@Repository
public interface GeneralRepository extends JpaRepository<General, Integer> {
	
	@Query("from General where name like %:name%")
	List<General> consultarPorNome(String name, Pageable pageable );
	
	@Query("from General where date between convert(NVARCHAR(10), :dataInicial, 103) and convert(NVARCHAR(10), :dataFinal, 103)")
	List<General> consultarPorPeriodo(String dataInicial, String dataFinal);
	
	@Query("from General where name like %:name% and date between convert(NVARCHAR(10), :dataInicial, 103) and convert(NVARCHAR(10), :dataFinal, 103)")
	List<General> consultarPorPeriodoName(String name, String dataInicial, String dataFinal);

}
