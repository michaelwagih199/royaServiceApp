package com.omelnur.roya.royaServiceApp.auth.repository;

import com.omelnur.roya.royaServiceApp.auth.models.UsersLogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author michael wagih
 */
public interface UserLogsRepository extends CrudRepository<UsersLogs,Long> {

    @Query("SELECT u FROM UsersLogs u ")
    Page<UsersLogs> findAllLogs(Pageable paging);

    @Query("SELECT u FROM UsersLogs u where u.user.id = :id")
    Page<UsersLogs> getLogsByUser(Pageable paging, @Param("id") Long userId);

    @Query("SELECT u FROM UsersLogs u where u.userLogsType.id = :typeId")
    Page<UsersLogs> findByUserLogsType(Pageable paging, @Param("typeId") Long typeId);

}
