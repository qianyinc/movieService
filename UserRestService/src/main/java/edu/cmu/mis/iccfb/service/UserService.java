package edu.cmu.mis.iccfb.service;
import edu.cmu.mis.iccfb.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service

public interface UserService extends CrudRepository<User, Long> {

    User findByName(String name);
    User findById(Long id);

}