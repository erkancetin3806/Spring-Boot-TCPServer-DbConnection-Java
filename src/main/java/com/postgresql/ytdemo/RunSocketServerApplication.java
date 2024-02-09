package com.postgresql.ytdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.postgresql.ytdemo.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunSocketServerApplication implements CommandLineRunner {

	@Autowired
	private DeviceController deviceController;

	public static void main(String[] args) {
		SpringApplication.run(RunSocketServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int serverPort = 9999;

		try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
			System.out.println("Server başlatıldı, port: " + serverPort);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Yeni bağlantı alındı: " + clientSocket.getInetAddress().getHostAddress());

				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println("Cihazdan gelen veri: " + line);
					deviceController.addDevice(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
