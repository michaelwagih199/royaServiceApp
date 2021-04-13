package com.omelnur.roya.royaServiceApp.auth.repository;

import com.omelnur.roya.royaServiceApp.auth.models.UserLogsType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author michael wagih
 */
public interface LogsTypeRepository extends CrudRepository<UserLogsType,Long> {
}
