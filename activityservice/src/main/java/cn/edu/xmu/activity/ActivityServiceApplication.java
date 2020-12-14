package cn.edu.xmu.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/28 23:30
 */
@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.ooad","cn.edu.xmu.activity","cn.edu.xmu.goods"})
@MapperScan("cn.edu.xmu.activity.mapper")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ActivityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityServiceApplication.class, args);
    }
}
