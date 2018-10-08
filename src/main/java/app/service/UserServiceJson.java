package app.service;

import app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.validation.ConstraintViolation;

@Service
public class UserServiceJson implements IUserServiceJson {

    private ObjectMapper objectMapper;

    @Autowired
    public UserServiceJson(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @Override
    public User getUser(String name) {
        try{
            return this.objectMapper.readValue(new File("target/" + name + ".json"), User.class);
        }catch(IOException ex){
            System.out.println(ex.getMessage().toUpperCase() + "!!!!!!!");
        }
        return null;
    }

    @Override
    public void save(User user) {



        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> validationErrors = validator.validate(user);
        if ( ! validationErrors.isEmpty()){
            System.out.printf("VALIDATION ERRORS ARE RAISED!!!!!!\nAMOUNT OF VALIDATION ERRORS IS %d\n", validationErrors.size());
            for(ConstraintViolation<User> var : validationErrors){
                System.out.println(var.getMessage().toUpperCase() + "!!!!!!!!!!!!!!!!!");
            }
            return;
        }

        try {
            this.objectMapper.writeValue(new File("target/" + user.getName() +".json"), user );
        }catch(IOException ex){
            System.out.println(ex.getMessage().toUpperCase() + "!!!!!!!");
        }



    }


//    public static class UserValidationException extends Exception{
//        private Collection<ConstraintViolation<User>> constraints;
//
//        public UserValidationException(Collection<ConstraintViolation<User>> constraints ){
//            super();
//            this.constraints = constraints;
//        }
//
//        public UserValidationException(String message, Collection<ConstraintViolation<User>> constraints){
//            super(message);
//            this.constraints = constraints;
//        }
//
//        public UserValidationException(String message, Throwable cause, Collection<ConstraintViolation<User>> constraints){
//            super(message, cause);
//            this.constraints = constraints;
//        }
//    }


}
