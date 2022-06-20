package heesoon.tableManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
// 테스트용 RestController
@RestController
@SpringBootApplication
public class TableManagerApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:aws.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(TableManagerApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);

	}
	// 테스트용 RestController
	@GetMapping
	public String home()
	{
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
}
