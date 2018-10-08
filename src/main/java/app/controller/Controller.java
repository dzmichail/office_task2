package app.controller;

import app.SearchUser;
import app.connection.ConnectionDB;
import app.entity.Organization;
import app.repository.OrganizationRepository;
import app.repository.UserRepository;
import app.service.IUserServiceDB;
import app.service.IUserServiceJson;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import app.service.UserServiceHib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.entity.User;


//import javax.persistence.EntityManager;
import java.util.Collection;

@RestController
public class Controller {

    private IUserServiceJson userServiceJson;
    private ConnectionDB conn;
    private IUserServiceDB userServiceDB;
//    private UserRepository userRepository;
    private UserServiceHib userServiceHib;
    private OrganizationRepository org;



    @Autowired
    public Controller(IUserServiceJson userServiceJson, ConnectionDB conn, IUserServiceDB userServiceDB, UserServiceHib userServiceHib, OrganizationRepository org){
        this.userServiceJson=userServiceJson;
        this.conn=conn;
        this.userServiceDB=userServiceDB;
        this.userServiceHib=userServiceHib;
        this.org=org;
    }

    @RequestMapping(path = "/hello/{value}", method = RequestMethod.GET)
    public void index(@PathVariable String  value){
//        System.out.println(arg);
        System.out.println(value);
        System.out.println(conn.getConn().hashCode());
        System.out.println(this.userServiceDB.getConn().hashCode());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) /*throws UserServiceJson.UserValidationException*/ {

//        this.userServiceJson.save(user);
//        this.userServiceDB.save(user);
        this.userServiceHib.save(user);
    }



//    @RequestMapping(path = "/get", method = RequestMethod.GET)
//    public User getUser(@RequestParam String name){
//        return this.userServiceJson.getUser(name);
//    }

    @RequestMapping(path = "/get", method = RequestMethod.POST)
    public User getUser(@RequestBody SearchUser searchUser){
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml1").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        // work with CrudRepository
//        User user = this.userServiceDB.getUser(searchUser.getName());
//        userRepository.save(user);

        User user = userServiceHib.getUser(searchUser.getName());

        return  user;
    }

    @RequestMapping(path="/getusers", method = RequestMethod.GET)
    public Collection<User> getUsers(){
        return userServiceHib.getAllUsers();
//        return this.userServiceDB.getUsers();
    }

    @RequestMapping(path = "/getuserorganization", method = RequestMethod.GET)
    public Collection<User> getUserOrganization(@RequestParam("organization") String name){
        return this.userServiceDB.getUserOrganization(name);
    }
}