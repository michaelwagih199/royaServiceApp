package com.omelnur.roya.royaServiceApp.setting.centersSetting.controller;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.models.CentersSetting;
import com.omelnur.roya.royaServiceApp.setting.centersSetting.services.CenterSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/settings/centers")
public class CenterSettingController implements ControllerBluePrint<CentersSetting> {

    @Autowired
    CenterSettingService centerSettingService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return ResponseEntity.ok().body(centerSettingService.getAllActive());
    }

    @GetMapping("login")
    public ResponseEntity loginToCenter(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok().body(centerSettingService.loginToCenter(username, password));
    }

    @GetMapping("names")
    public ResponseEntity getDoctorNames() {
        return new ResponseEntity(centerSettingService.getNames(), HttpStatus.OK);
    }

    @GetMapping("name")
    public ResponseEntity searchByName(@RequestParam String centerName) {
        return new ResponseEntity(centerSettingService.searchByName(centerName), HttpStatus.OK);
    }


    @Override
    public CentersSetting getObjectById(Long id) {
        return null;
    }

    public CentersSetting addObject(@RequestBody CentersSetting object) {
        return null;
    }

    @PostMapping
    public CentersSetting createCenterSetting(@RequestBody CentersSetting object, @RequestParam Long hospitalId) {
        return centerSettingService.createCenterSetting(object , hospitalId);
    }

    @PutMapping("{id}")
    @Override
    public CentersSetting updateObject(@PathVariable Long id,@RequestBody CentersSetting object) {
        return centerSettingService.updateObject(id, object);
    }

    @PutMapping("/archive")
    @Override
    public void deleteObject(@RequestParam Long id) {
        centerSettingService.deleteObject(id);
    }

}
