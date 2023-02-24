package com.ggsoftware.opcserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggsoftware.opcserver.entity.ItemTag;
import com.ggsoftware.opcserver.entity.Settings;
import com.ggsoftware.opcserver.services.SettingsServer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "ItemTags")
@RestController
@RequestMapping("/settings")
public class SettingsController {

	@Autowired
	private SettingsServer service;

	@ApiOperation("Lista o arquivo Json unificado das ItemTags")
	@GetMapping()
	public List<Settings> ListarTodasAsSettings() {
		return service.findAll();
	}

	@ApiOperation("Lista ItemTags separadamente")
	@GetMapping("itemtags")
	public List<ItemTag> ListarTodasAsSettingsSeparadas() {
		return service.findAllTags();
	}
}
