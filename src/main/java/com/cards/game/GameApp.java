package com.cards.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class GameApp {

	public static void main(String[] args) {
		SpringApplication.run(GameApp.class, args);
	}

}
