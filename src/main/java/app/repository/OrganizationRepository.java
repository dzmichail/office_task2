package app.repository;

import app.entity.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

    @Query("Select o from Organization o where o.id=:id")
    Organization getOrg(@Param("id") Integer id);


}
