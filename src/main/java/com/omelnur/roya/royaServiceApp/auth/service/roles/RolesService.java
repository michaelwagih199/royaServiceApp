package com.omelnur.roya.royaServiceApp.auth.service.roles;


import com.omelnur.roya.royaServiceApp.auth.models.Permission;
import com.omelnur.roya.royaServiceApp.auth.models.Role;
import com.omelnur.roya.royaServiceApp.auth.payload.request.RolesRequest;
import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;

import java.util.List;
import java.util.Set;

public interface RolesService extends BluePrintService<Role> {
    public Role createRoles(RolesRequest rolesRequest);
    List<Set<Permission>> getRolePermissions(Long roleId);
}
