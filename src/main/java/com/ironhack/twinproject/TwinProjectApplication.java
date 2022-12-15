package com.ironhack.twinproject;

import com.ironhack.twinproject.service.MainMenuService;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class TwinProjectApplication implements CommandLineRunner {

	private final Utils utils;
	private final MainMenuService mainMenu;

	public static void main(String[] args) {
		SpringApplication.run(TwinProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mainMenu.run();
	}
}