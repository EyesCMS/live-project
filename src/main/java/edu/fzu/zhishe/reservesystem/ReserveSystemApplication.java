package edu.fzu.zhishe.reservesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "edu.fzu.zhishe.reservesystem.generator")
@SpringBootApplication
public class ReserveSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReserveSystemApplication.class, args);
    }

}
