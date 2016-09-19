package hello;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import hello.controller.FileUploadController;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@ServletComponentScan
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(Application.class).run(args);
    }

    @Bean
    CommandLineRunner init() {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(FileUploadController.ROOT));

            Files.createDirectory(Paths.get(FileUploadController.ROOT));
        };
    }
}