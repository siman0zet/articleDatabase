package ru.pnu.edu.articledatabase;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ArticleDatabaseApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ArticleDatabaseApplication.class, args);
		Application.launch(JavaFxApplication.class, args);
	}

}
