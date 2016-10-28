package com.theironyard.services;

import com.theironyard.entities.Recipient;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zach on 10/28/16.
 */
public interface RecipientRepository extends CrudRepository<Recipient, Integer> {
    List<Recipient> findByUser(User user);
}
