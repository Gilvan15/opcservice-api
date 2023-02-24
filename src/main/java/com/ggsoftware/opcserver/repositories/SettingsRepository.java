package com.ggsoftware.opcserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggsoftware.opcserver.entity.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer>  {

}
