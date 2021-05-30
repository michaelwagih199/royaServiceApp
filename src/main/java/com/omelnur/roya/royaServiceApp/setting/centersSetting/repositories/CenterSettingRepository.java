package com.omelnur.roya.royaServiceApp.setting.centersSetting.repositories;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintCustomResponce;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.models.CentersSetting;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.services.CenterSettingService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CenterSettingRepository extends CrudRepository<CentersSetting,Long> {

    @Query("select c from CentersSetting c where c.centeruserName =:username and c.centerPassword=:password ")
    CentersSetting loginToCenter(String username, String password);

    @Query("select c from CentersSetting c where c.isArchived =false")
    List<CentersSetting> getAllActive();

    @Query("select c from CentersSetting c where c.hospital.id=:hospitalId and c.isArchived =false")
    CentersSetting checkIfCenterExsit(Long hospitalId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CentersSetting c set c.isArchived =:isArchived WHERE c.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    @Query("select c.hospital.hospitalName from CentersSetting c where c.isArchived =false")
    List<String> findNames();

    @Query("select c from CentersSetting c where  c.hospital.hospitalName =:centerName and c.isArchived =false")
    List<CentersSetting> findByHospitalName(String centerName);

}
