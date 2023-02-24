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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "OpcItemTags")
@RestController
@RequestMapping("/opcdevices")
public class GeneralController {
	
	//Injeção de dependências:
	@Autowired
	private GeneralService service;
	
	//Seção de endpoints:
	@ApiOperation("Lista todas opcItemTags")
	@GetMapping()
	public List<GeneralDTO> ListarTodasAsTags(){
		return toCollectonDTO(service.findAll());
	}
	
	@ApiOperation("Lista opcItemTags por nome - page" )
	@GetMapping("/listar-por-nome-page")
	public List<GeneralDTO> ListarTagsPorNome(@RequestParam("name") String name, Pageable pageable) {
		return toCollectonDTO(service.findByName(name, pageable));
	}
	
	@ApiOperation("Lista todas opcItemTags - page" )
	@GetMapping("/listar-todos-page")
	public List<GeneralDTO> ListarTodasTagsPaginada(@ApiParam(name = "Size", value = "Quantidade por páginas" ) Pageable pageable) {
		return toCollectonDTOPage(service.findAll(pageable));
	}
	
	@ApiOperation("Lista opcItemTags por nome e período" )
	@GetMapping("/listar-por-nome-periodo")
	public List<General> ListarTagsPorPeriodoENome(@RequestParam String name, @RequestParam String dateInicio, @RequestParam String dateFinal )   {
		return service.listarPorPeriodoName(name, dateInicio, dateFinal);
	}
	
	@ApiOperation("Lista opcItemTags por nome e período - page" )
	@GetMapping("/listar-por-nome-periodo-page")
	public List<General> ListarTagsPorPeriodoENomePage(@RequestParam String name, @RequestParam String dateInicio, @RequestParam String dateFinal, Pageable pageable )   {
		return service.listarPorPeriodoNamePage(name, dateInicio, dateFinal, pageable);
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