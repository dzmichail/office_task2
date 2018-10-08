package app.repository;

import app.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByName  (String name);

    List<User> findUsersByNameOrderByBirthDate(String name);

    @Query("SELECT u FROM User AS u ORDER BY u.birthDate")
    List<User> getAllUsers();

    @Query("SELECT u FROM User AS u where u.name = :name")
    User findUser(@Param("name") String name);
}
