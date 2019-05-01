package config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScans({@ComponentScan("cn.springtx")})
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfiguration.class, TransactionConfiguration.class})
@EnableTransactionManagement
public class SpringConfiguration {

}
