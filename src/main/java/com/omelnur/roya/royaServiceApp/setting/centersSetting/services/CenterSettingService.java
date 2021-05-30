package com.omelnur.roya.royaServiceApp.setting.centersSetting.services;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintCustomResponce;
import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.models.CentersSetting;

import java.util.List;

public interface CenterSettingService extends BluePrintService<CentersSetting> {
    public abstract BluePrintCustomResponce loginToCenter(String Username, String password);

    List<CentersSetting> getAllActive();

    CentersSetting createCenterSetting(CentersSetting object, Long hospitalId);

    List<String> getNames();

    List<CentersSetting> searchByName(String centerName);
}
