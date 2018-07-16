package top.atstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by admin on 2017/11/15.
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = {
        "top.atstudy.advistory.user.dao.mapper",
        "top.atstudy.advistory.article.dao.mapper",
        "top.atstudy.component.user.dao.mapper"
})
public class SpringBoot {
    private static final Logger logger = LoggerFactory.getLogger(SpringBoot.class);
    public static void main(String[] args){
        logger.info(" ===>> spring boot main ... ");
        SpringApplication.run(SpringBoot.class, args);
    }
}