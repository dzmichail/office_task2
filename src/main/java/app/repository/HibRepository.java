package app.repository;

import app.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface HibRepository extends CrudRepository<User, Integer> {
}
