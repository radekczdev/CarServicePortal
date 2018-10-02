package com.czajor.carserviceportal.repository;

import com.czajor.carserviceportal.model.RepairOrder;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface RepairOrderRepository extends CrudRepository<RepairOrder, Integer> {

    @Override
    List<RepairOrder> findAll();

    @Override
    Optional<RepairOrder> findById(Integer id);
}
