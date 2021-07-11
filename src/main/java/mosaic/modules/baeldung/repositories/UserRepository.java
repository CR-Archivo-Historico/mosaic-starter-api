package mosaic.modules.baeldung.repositories;


import mosaic.modules.baeldung.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}