package app.configuration;

import app.connection.ConnectionDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {
    @Bean
    public ConnectionDB connection(){
        return new ConnectionDB();
    }
}
