package com.kaizen.service.dbService;

import com.kaizen.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDbService extends CrudRepository<User, Integer> {


}
