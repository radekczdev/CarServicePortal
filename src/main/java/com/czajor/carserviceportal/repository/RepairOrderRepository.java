package com.czajor.carserviceportal.repository;

import com.czajor.carserviceportal.model.RepairOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepairOrderRepository extends CrudRepository<RepairOrder, Integer> {

    @Override
    List<RepairOrder> findAll();
}
