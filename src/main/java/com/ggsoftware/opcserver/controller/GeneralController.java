package com.ggsoftware.opcserver.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggsoftware.opcserver.entity.General;
import com.ggsoftware.opcserver.entity.GeneralDTO;
import com.ggsoftware.opcserver.services.GeneralService;

@RestController
@RequestMapping("/opcdevices")
public class GeneralController {
	
	//Injeção de dependências:
	@Autowired
	private GeneralService service;
	
	@GetMapping()
	public List<GeneralDTO> ListarTodasAsTags(){
		return toCollectonDTO(service.findAll());
	}
	
	//Seção de endpoints:
	@GetMapping("/listar-por-nome")
	public List<GeneralDTO> ListarTagsPorNome(@RequestParam("name") String name, Pageable pageable) {
		return toCollectonDTO(service.findByName(name, pageable));
	}
	
	@GetMapping("/listar-todos")
	public List<GeneralDTO> ListarTodasTagsPaginada(Pageable pageable) {
		return toCollectonDTOPage(service.findAll(pageable));
	}
	
	@GetMapping("/listar-por-periodo")
	public List<General> ListarTagsPorPeriodoENome(@RequestParam String name, @RequestParam String dateInicio, @RequestParam String dateFinal )   {
		return service.listarPorPeriodoName(name, dateInicio, dateFinal);
	}
	
	//Seção de métodos privados:
	private List<GeneralDTO> toCollectonDTO(List<General> list ){
		return list.stream()
				.map(general -> toDTO(general))
				.collect(Collectors.toList());
	}
	
	private List<GeneralDTO> toCollectonDTOPage(Page<General> page ){
		return page.stream()
				.map(general -> toDTO(general))
				.collect(Collectors.toList());
	}
		
	private GeneralDTO toDTO(General general) {
		GeneralDTO generalDTO = new GeneralDTO();
		generalDTO.setId(general.getId() );
		generalDTO.setName(general.getName());
		generalDTO.setValue(general.getValue());
		generalDTO.setDate(general.getDate());
		generalDTO.setTime(general.getTime());
		return generalDTO;
	}
}