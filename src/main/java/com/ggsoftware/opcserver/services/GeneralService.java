package com.ggsoftware.opcserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ggsoftware.opcserver.entity.General;
import com.ggsoftware.opcserver.repositories.GeneralRepository;


@Service
public class GeneralService {
	
	@Autowired
	private GeneralRepository repository;

	
	public Page<General> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public List<General> findAll() {
		return repository.findAll();
	}
	
	public List<General> findByName(String name, Pageable pageable) {
		return repository.consultarPorNome(name, pageable);
	}	
	
	public List<General> listarPorPeriodoName(String name, String dateInicio,  String dateFinal ) {
			
			String sDateInicio = dateInicio.replace("-","/");
			String sDateFinal  = dateFinal.replace("-","/");
			System.out.println("sDateInicio: " + sDateInicio);
			System.out.println("sDateInicio: " + sDateFinal);
			
			return repository.consultarPorPeriodoName(name, sDateInicio, sDateFinal);
	}
	
	
	public List<General> listarPorPeriodoNamePage(String name, String dateInicio,  String dateFinal, Pageable pageable ){
		
		String sDateInicio = dateInicio.replace("-","/");
		String sDateFinal  = dateFinal.replace("-","/");
		System.out.println("sDateInicio: " + sDateInicio);
		System.out.println("sDateInicio: " + sDateFinal);
		
		return repository.consultarPorPeriodoNamePage(name, sDateInicio, sDateFinal, pageable);
}
	
	
	public List<General> listarPorPeriodo(String dateInicio,  String dateFinal ) {
		
		String sDateInicio = dateInicio.replace("-","/");
		String sDateFinal  = dateFinal.replace("-","/");
		System.out.println("sDateInicio: " + sDateInicio);
		System.out.println("sDateInicio: " + sDateFinal);
		
		return repository.consultarPorPeriodo(sDateInicio, sDateFinal);
}


}
