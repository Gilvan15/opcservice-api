package com.ggsoftware.opcserver.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ApiModel("Pageable")
@Setter
@Getter
public class PageableConfigSwagger {
	
	@ApiModelProperty(example = "0", value = "Número da Página (começa em 0)")
	private int page;
	
	@ApiModelProperty(example = "10", value = "Quantidade de Registros por página")
	private int size;
	
	@ApiModelProperty(example = "name,asc", value = "Nome da propriedade por ordenção")
	private List<String> sort;
	

}
