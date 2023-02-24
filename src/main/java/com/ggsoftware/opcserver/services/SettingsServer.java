package com.ggsoftware.opcserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggsoftware.opcserver.entity.ItemTag;
import com.ggsoftware.opcserver.entity.Settings;
import com.ggsoftware.opcserver.repositories.SettingsRepository;

@Service
public class SettingsServer {
	
	@Autowired
	private SettingsRepository repository;
	
	
	public List<Settings> findAll() {
		return repository.findAll();
	}
	
	public List<ItemTag> findAllTags() {
		
		List<ItemTag> itensTag = new ArrayList<ItemTag>();
		List<Settings> nome = repository.findAll();

		for (int i = 0; i < nome.size(); i++) {
			if (i == 1) {
				String text1 = nome.get(i).getValue();
				String[] values1 = text1.split(",");
				//System.out.println("O valor de values1 ".concat("é: " + values1.length));
				for (int y = 0; y < values1.length; y++) {
					itensTag.add(new ItemTag(y + 1, values1[y]));
				}
			}
		}
		return itensTag;
		
	}

}
