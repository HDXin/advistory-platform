package top.atstudy;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumUserStatus;
import top.atstudy.component.enums.NumberCodeEnumTypeHandler;
import top.atstudy.component.enums.StringCodeEnumTypeHandler;

/**
 * Created by admin on 2017/11/15.
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan(basePackages = {
        "top.atstudy.advistory.article.dao.mapper",
        "top.atstudy.component.user.dao.mapper",
        "top.atstudy.component.swiper.dao.mapper"
})
public class SpringBoot {
    private static final Logger logger = LoggerFactory.getLogger(SpringBoot.class);
    public static void main(String[] args){
        logger.info(" ===>> spring boot main ... ");
        ApplicationContext applicationContext = SpringApplication.run(SpringBoot.class, args);


        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(EnumDeleted.class, NumberCodeEnumTypeHandler.class);
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(EnumUserStatus.class, StringCodeEnumTypeHandler.class);
    }

}