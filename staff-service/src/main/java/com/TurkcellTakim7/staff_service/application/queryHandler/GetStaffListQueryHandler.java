package com.TurkcellTakim7.staff_service.application.queryHandler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.core.QueryHandler;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;
import com.TurkcellTakim7.staff_service.application.mapper.GetStaffMapper;
import com.TurkcellTakim7.staff_service.application.queries.GetStaffListQuery;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;

@Component
public class GetStaffListQueryHandler implements QueryHandler<GetStaffListQuery, List<StaffResponse>> {

    private final StaffDomainService staffDomainService;
    private final GetStaffMapper getStaffMapper;

    public GetStaffListQueryHandler(StaffDomainService staffDomainService, GetStaffMapper getStaffMapper) {
        this.staffDomainService = staffDomainService;
        this.getStaffMapper = getStaffMapper;
    }

    @Override
    public List<StaffResponse> handle(GetStaffListQuery query) {
        List<Staff> staffList = staffDomainService.getStaffList();

        return staffList.stream()
                .map(getStaffMapper::toResponse)
                .toList();
    }
}
