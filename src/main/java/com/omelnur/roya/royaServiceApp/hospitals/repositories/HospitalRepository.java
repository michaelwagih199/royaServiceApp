package com.omelnur.roya.royaServiceApp.hospitals.repositories;

import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author OMELNOUR
 */
public interface HospitalRepository extends CrudRepository<Hospital,Long> {

    Iterable<Hospital> findByIsArchived(Boolean isArchived);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Hospital h set h.isArchived =:isArchived WHERE h.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    @Query("SELECT h FROM Hospital h WHERE h.isArchived = false")
    Page<Hospital> getActivePagination(Pageable paging);

    @Query("SELECT h.hospitalName FROM Hospital h WHERE h.isArchived = false")
    List<String>getNames();

    List<Hospital> findByHospitalName(String hospitalName);
    List<Hospital> findByHospitalPhone1(String hospitalName);

    @Query("SELECT h.hospitalPhone1 FROM Hospital h WHERE h.isArchived = false")
    List<String> getPhones();


    @Query(value ="select count(id) from hospital h where (h.created_date BETWEEN :start AND :end) and is_archived = false", nativeQuery = true)
    Integer countBetweenDates(Date start, Date end);

}
