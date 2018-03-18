package application;

import application.data.service.ProductService;
import application.service.FileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class Application  implements  CommandLineRunner{
    @Resource
    FileStorageService fileStorageService;

private  static final Logger LOGGER = LogManager.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        LOGGER.debug("started----------------------------");
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
