package com.omelnur.roya.royaServiceApp.auth.controllers;

import com.omelnur.roya.royaServiceApp.auth.models.Permission;
import com.omelnur.roya.royaServiceApp.auth.service.permision.PermissionService;
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
@RequestMapping("api/auth/permission")
public class PermissionController implements ControllerBluePrint<Permission> {

    @Autowired
    PermissionService permissionService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return new ResponseEntity(permissionService.getAllObject(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public Permission getObjectById(@Positive @PathVariable Long id) {
        return permissionService.findObject(id);
    }

    @PostMapping
    @Override
    public Permission addObject(@RequestBody @Valid Permission object) {
        return permissionService.createObject(object);
    }

    @Override
    public Permission updateObject(Long id, Permission object) {
        return null;
    }

    @DeleteMapping("{id}")
    @Override
    public void deleteObject(@Positive @PathVariable Long id) {
        permissionService.deleteObject(id);
    }

}
