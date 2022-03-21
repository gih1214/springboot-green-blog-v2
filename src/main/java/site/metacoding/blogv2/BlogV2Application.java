package site.metacoding.blogv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JpaAuditing 활성화
@SpringBootApplication
public class BlogV2Application {

	public static void main(String[] args) {
		SpringApplication.run(BlogV2Application.class, args);
	}

}
