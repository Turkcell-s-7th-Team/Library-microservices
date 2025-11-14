package com.TurkcellTakim7.staff_service.application.queryHandler;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.staff_service.application.core.QueryHandler;
import com.TurkcellTakim7.staff_service.application.dto.StaffResponse;
import com.TurkcellTakim7.staff_service.application.mapper.GetStaffMapper;
import com.TurkcellTakim7.staff_service.application.queries.GetStaffQuery;
import com.TurkcellTakim7.staff_service.domain.entities.Staff;
import com.TurkcellTakim7.staff_service.domain.services.StaffDomainService;
import com.TurkcellTakim7.staff_service.domain.valueobjects.StaffId;

@Component
public class GetStaffQueryHandler implements QueryHandler<GetStaffQuery, StaffResponse> {

    private final GetStaffMapper getStaffMapper;
    private final StaffDomainService staffDomainService;

    public GetStaffQueryHandler(GetStaffMapper getStaffMapper, StaffDomainService staffDomainService) {
        this.getStaffMapper = getStaffMapper;
        this.staffDomainService = staffDomainService;
    }

    
    public StaffResponse handle(GetStaffQuery query) {
        Staff staff = staffDomainService.getStaff(new StaffId(query.id()));
        return getStaffMapper.toResponse(staff);
    }
}
