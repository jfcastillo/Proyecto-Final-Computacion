package com.taller.castillo.felipe;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.service.TsscAdminServiceImp;
import com.taller.castillo.felipe.service.TsscGameServiceImp;
import com.taller.castillo.felipe.service.TsscTopicServiceImp;

@SpringBootApplication
public class Taller2Application {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {

//		SpringApplication.run(Taller1Application.class, args);
		ConfigurableApplicationContext c = SpringApplication.run(Taller2Application.class, args);
		TsscAdminServiceImp adminBean = c.getBean(TsscAdminServiceImp.class);
		// ADMINISTRADOR
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("felipe");
		admin.setPassword("{noop}1234");
		admin.setSuperAdmin("admin");
		adminBean.createAdmin(admin);
		// SUPER ADMINISTRADOR
		TsscAdmin superAdmin = new TsscAdmin();
		superAdmin.setUsername("superfelipe");			
		superAdmin.setPassword("{noop}1234");
		superAdmin.setSuperAdmin("superadmin");	
		adminBean.createAdmin(superAdmin);
		
		TsscTopicServiceImp topicBean = c.getBean(TsscTopicServiceImp.class);
		TsscTopic topic = new TsscTopic();
		topic.setName("Scrum <20 MGP - Corto");
		topic.setDescription("Scrum menos de 20 personas MGP");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(3);
		topic.setGroupPrefix("Grupo");
		TsscTopic topic1 = new TsscTopic();
		topic1.setName("Scrum 20-30 SIS");
		topic1.setDescription("Scrum menos de 20 a 30 personas SIS");
		topic1.setDefaultGroups(5);
		topic1.setDefaultSprints(4);
		topic1.setGroupPrefix("Grupo");
		TsscTopic topic2 = new TsscTopic();
		topic2.setName("Scrum <20 SIS");
		topic2.setDescription("Scrum menos de 20 personas SIS");
		topic2.setDefaultGroups(4);
		topic2.setDefaultSprints(4);
		topic2.setGroupPrefix("Grupo");
		TsscTopic topic3 = new TsscTopic();
		topic3.setName("Scrum 20-30 MGP - Full");
		topic3.setDescription("Scrum menos de 20 personas MGP-Full");
		topic3.setDefaultGroups(4);
		topic3.setDefaultSprints(4);
		topic3.setGroupPrefix("Grupo");
		try {
			topicBean.createTopic(topic);
			topicBean.createTopic(topic1);
			topicBean.createTopic(topic2);
			topicBean.createTopic(topic3);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TsscGameServiceImp gameBean = c.getBean(TsscGameServiceImp.class);
		TsscGame game = new TsscGame();
		game.setName("Completa-PI2.20-1");
		game.setScheduledDate(LocalDate.of(2019, Month.JANUARY, 14));
		game.setScheduledTime(LocalTime.of(00,00));
		game.setNGroups(4);
		game.setNSprints(4);
		TsscGame game1 = new TsscGame();
		game1.setName("202-mgp");
		game1.setScheduledDate(LocalDate.of(2021, Month.JANUARY, 1));
		game1.setScheduledTime(LocalTime.of(19,07));
		game1.setNGroups(4);
		game1.setNSprints(5);
		try {
			gameBean.createGame(game);
			gameBean.createGame(game1);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
