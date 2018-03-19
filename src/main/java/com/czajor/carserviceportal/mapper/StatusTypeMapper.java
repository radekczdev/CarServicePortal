package com.czajor.carserviceportal.mapper;

import com.czajor.carserviceportal.domain.StatusTypeDto;
import com.czajor.carserviceportal.model.StatusType;
import org.springframework.stereotype.Component;

@Component
public class StatusTypeMapper {

    public StatusType mapToRepairOrderStatus(StatusTypeDto statusTypeDto) throws IllegalArgumentException {
        StatusType statusType = StatusType.valueOf(statusTypeDto.getStatusType());
        return statusType;
    }

}
