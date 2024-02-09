package com.postgresql.ytdemo;

import com.postgresql.ytdemo.model.Device;
import com.postgresql.ytdemo.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    @Autowired
    DeviceRepository repo;

    @PostMapping("/addDevice")
    public void addDevice(@RequestBody String deviceData) {
        // Assume that deviceData contains necessary information for Device creation
        Device device = new Device();
        device.setImei(deviceData); // Set the appropriate field according to your data
        repo.save(device);
    }

    @PostMapping("/deleteDevice/{id}")
    public void deleteDevice(@PathVariable long id) {
        System.out.println("Delete request received for ID: " + id);

        if (repo.existsById(id)) {
            repo.deleteById(id);
            System.out.println("Veritabanı Kayıt Silme İşlemi Başarılı.. Silinen Kayıt: " + id);
        } else {
            System.out.println("Belirtilen ID'ye sahip bir kayıt bulunamadı: " + id);
        }
    }
}
