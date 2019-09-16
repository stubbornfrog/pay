package niuwajun;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application{
 
    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        DataSource dataSource = context.getBean(DataSource.class);

        System.out.println(dataSource.getClass());
    }

    
}
