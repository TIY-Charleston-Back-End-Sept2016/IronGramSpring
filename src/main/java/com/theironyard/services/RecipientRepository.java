package com.theironyard.services;

import com.theironyard.entities.Recipient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zach on 10/28/16.
 */
public interface RecipientRepository extends CrudRepository<Recipient, Integer> {
}
