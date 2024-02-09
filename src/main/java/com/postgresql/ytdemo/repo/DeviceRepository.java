package com.postgresql.ytdemo.repo;

import com.postgresql.ytdemo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
