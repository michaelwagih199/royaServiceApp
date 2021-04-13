package com.omelnur.roya.royaServiceApp.auth.repository;


import com.omelnur.roya.royaServiceApp.auth.models.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission,Long> {

}
