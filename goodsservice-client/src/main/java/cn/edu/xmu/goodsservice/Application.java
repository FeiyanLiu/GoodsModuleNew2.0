package cn.edu.xmu.goodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/14 22:46
 * modifiedBy Yancheng Lai 22:46
 **/
@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.goodsservice","cn.edu.xmu.activity","cn.edu.xmu.goods"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
