package app.service;

import app.entity.User;
import app.repository.OrganizationRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceHib implements IUserServiceHib {

   private UserRepository userRepository;
   private OrganizationRepository organizationRepository;


    @Autowired
    public UserServiceHib(UserRepository userRepository, OrganizationRepository organizationRepository){
        this.userRepository=userRepository;
        this.organizationRepository=organizationRepository;
    }

    public void save(User user){
         if (this.organizationRepository.getOrg(user.getOrganization()) == null ){
             return;
         }
        userRepository.save(user);
    }

    public User getUser(String name){
//        return userRepository.findUserByName(name);
//        System.out.println(null == organizationRepository.getOrg(5));

        User user  = userRepository.findUser(name);

//        System.out.println(user);

        if (user!=null) {
            user.setOrganizationname( organizationRepository.getOrg(user.getOrganization()).getNameorganization() );
        }
        return user;
    }

//    public Collection<User> getUserOrganization(){
//        return new Collection<User>();
//    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

}
