package com.omelnur.roya.royaServiceApp.auth.controllers;


import com.omelnur.roya.royaServiceApp.auth.models.Role;
import com.omelnur.roya.royaServiceApp.auth.payload.request.RolesRequest;
import com.omelnur.roya.royaServiceApp.auth.service.roles.RolesService;
import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * @author michael wagih
 */

@RestController
@RequestMapping("api/auth/roles")
public class RolesController implements ControllerBluePrint<Role> {

    @Autowired
    RolesService rolesService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return new ResponseEntity(rolesService.getAllObject(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Override
    public Role getObjectById(@PathVariable @Positive Long id) {
        return rolesService.findObject(id);
    }

    @GetMapping("rolePermissions/{roleId}")
    public ResponseEntity getRolePermissions(@Positive @PathVariable Long roleId){
        return new ResponseEntity(rolesService.getRolePermissions(roleId).get(0), HttpStatus.OK);
    }

    @Override
    public Role addObject(@RequestBody Role object) {
        return rolesService.createObject(object);
    }

    @Override
    public Role updateObject(Long id, Role object) {
        return null;
    }


    @DeleteMapping("{id}")
    @Override
    public void deleteObject(@Positive @PathVariable Long id) {
        rolesService.deleteObject(id);
    }


    @PostMapping
    public Role createRoles(@Valid @RequestBody RolesRequest role){
        return rolesService.createRoles(role);
    }

}
