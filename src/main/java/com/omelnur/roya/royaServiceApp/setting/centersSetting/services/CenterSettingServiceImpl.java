package com.omelnur.roya.royaServiceApp.setting.centersSetting.services;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintCustomResponce;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.models.CentersSetting;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.repositories.CenterSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo
 * create center
 * Login to system return obj or false
 */
@Service
public class CenterSettingServiceImpl implements CenterSettingService {

    @Autowired
    CenterSettingRepository centerSettingRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public CentersSetting createObject(CentersSetting object) {
        return centerSettingRepository.save(object);
    }

    @Override
    public CentersSetting updateObject(Long id, CentersSetting object) {
        return centerSettingRepository.findById(id).map(post -> {
            post.setCenterPassword(object.getCenterPassword());
            post.setHospital(object.getHospital());
            post.setId(object.getId());
            post.setCenteruserName(object.getCenteruserName());
            return centerSettingRepository.save(post);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteObject(Long id) {
        centerSettingRepository.archive(true, id);
    }

    @Override
    public Iterable<CentersSetting> getAllObject() {
        return null;
    }

    @Override
    public CentersSetting findObject(Long id) {
        return null;
    }

    @Override
    public BluePrintCustomResponce loginToCenter(String username, String password) {
        BluePrintCustomResponce bluePrintCustomResponce = new BluePrintCustomResponce();
        bluePrintCustomResponce.setObject(centerSettingRepository.loginToCenter(username, password));
        if (centerSettingRepository.loginToCenter(username, password) == null) {
            bluePrintCustomResponce.setMessage("Wrong credentials");
        } else bluePrintCustomResponce.setMessage("bass user");
        return bluePrintCustomResponce;
    }

    @Override
    public List<CentersSetting> getAllActive() {
        return centerSettingRepository.getAllActive();
    }

    @Override
    public CentersSetting createCenterSetting(CentersSetting object, Long hospitalId) {
        if (centerSettingRepository.checkIfCenterExsit(hospitalId) == null) {
            object.setHospital(hospitalRepository.findById(hospitalId).orElseThrow(ResourceNotFoundException::new));
            centerSettingRepository.save(object);
        }
        return null;
    }

    @Override
    public List<String> getNames() {
        return centerSettingRepository.findNames();
    }

    @Override
    public List<CentersSetting> searchByName(String centerName) {
        return centerSettingRepository.findByHospitalName(centerName);
    }

}
