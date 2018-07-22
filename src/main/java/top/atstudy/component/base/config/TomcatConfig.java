package top.atstudy.component.base.config;

//import org.apache.catalina.Context;
//import org.apache.tomcat.util.http.LegacyCookieProcessor;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-22
 * Time: 19:34
 */
@Configuration
public class TomcatConfig {

//    @Bean
//    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                    ((TomcatEmbeddedServletContainerFactory) container)
//                            .addContextCustomizers(new TomcatContextCustomizer() {
//
//                                @Override
//                                public void customize(Context context) {
//                                    context.setCookieProcessor(new LegacyCookieProcessor());
//                                }
//
//                            });
//                }
//            }
//
//        };
//    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers(
//                connector -> {
//                    Http11NioProtocol protocol =
//                            (Http11NioProtocol) connector.getProtocolHandler();
//                    System.out.println("---------------------*************************____________________");
//                    protocol.setDisableUploadTimeout(false);
//                }
//        );

        factory.addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                context.setCookieProcessor(new LegacyCookieProcessor());
            }
        });

        return factory;
    }



}
