package com.czajor.carserviceportal.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//@Transactional
//@Repository
public interface AddressDao extends CrudRepository<Address, Integer> {

}
