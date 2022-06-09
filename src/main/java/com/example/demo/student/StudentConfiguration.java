package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student sandrine = new Student("SANDRINE",
                    "casandra@gmail.com",
                    LocalDate.of(2000, 2, 5)
            );
            Student adeline = new Student("uwumucyo",
                    "UWUMU@gmail.com",
                    LocalDate.of(1980, 2, 5)
            );
            repository.saveAll(Arrays.asList(sandrine,adeline)
            );
        };


    }

}
