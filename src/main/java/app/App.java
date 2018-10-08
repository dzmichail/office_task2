package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EntityScan(basePackages = "app.entity")
//@EnableJpaRepositories
//@EnableAutoConfiguration
//@SpringBootConfiguration
//@ComponentScan
@SpringBootApplication
public class App {
    public static void main(String[] args){

//        String driver = "org.postgresql.Driver";
//        String url = "jdbc:postgresql://127.0.0.1:5432/office_task";
//
//        Connection conn = null;
//
//
//
//        try{
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, "postgres", "postgres");
//        }catch (ClassNotFoundException ex){
//            System.out.println("ClassNotFoundException IS OCCURED!");
//            ex.printStackTrace();
//        }catch(SQLException sex){
//            System.out.println("SQLException IS OCCURED!");
//            sex.printStackTrace();
//        }

//        System.out.println("SYSTEM IS WORKING!!!!!!!!!!!!!!!!!!!!!!!");

        SpringApplication.run(App.class, args);
    }
}
