package com.ironhack.twinproject;

import com.ironhack.twinproject.service.Menu;
import com.ironhack.twinproject.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TwinProjectApplication implements CommandLineRunner {

	private final Utils utils;
	private final Menu mainMenu;

	public static void main(String[] args) {
		SpringApplication.run(TwinProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mainMenu.run();
	}
}