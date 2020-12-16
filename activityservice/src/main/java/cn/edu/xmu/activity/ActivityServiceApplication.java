package cn.edu.xmu.activity;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/28 23:30
 */

@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.ooad","cn.edu.xmu.activity"})
@MapperScan("cn.edu.xmu.activity.mapper")
@DubboComponentScan("cn.edu.xmu.activity")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient//通过springCloud实现服务发现

public class ActivityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityServiceApplication.class, args);
    }
}
