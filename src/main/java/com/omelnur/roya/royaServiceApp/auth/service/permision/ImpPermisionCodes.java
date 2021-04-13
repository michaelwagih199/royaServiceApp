package com.omelnur.roya.royaServiceApp.auth.service.permision;

import com.omelnur.roya.royaServiceApp.auth.models.Permission;
import com.omelnur.roya.royaServiceApp.auth.repository.PermissionRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ImpPermisionCodes implements PermissionService {

    private final PermissionRepository permissionRepository;


    public ImpPermisionCodes(PermissionRepository permisionCodeRepository) {
        this.permissionRepository = permisionCodeRepository;
    }

    @Override
    public Permission createObject(Permission object) {
        return permissionRepository.save(object);
    }

    @Override
    public Permission updateObject(Long id, Permission object) {
        return null;
    }

    @Override
    public void deleteObject(Long id) {
        permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("permission id" + id + "not found"));
        permissionRepository.deleteById(id);
    }

    @Override
    public Iterable<Permission> getAllObject() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findObject(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("permission id" + id + "not found"));

    }

}
