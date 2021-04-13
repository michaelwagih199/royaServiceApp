package com.omelnur.roya.royaServiceApp.auth.controllers;


import com.omelnur.roya.royaServiceApp.auth.models.UsersLogs;
import com.omelnur.roya.royaServiceApp.auth.service.userLogs.UserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author michael wagih
 */
@RestController
@RequestMapping("api/userLogs")
public class UserLogsController {

    /**
     * create logs d
     * get all logs d
     * get logs by user id
     * get logs by type id
     */

    @Autowired
    UserLogsService userLogsService;

    @PostMapping
    public UsersLogs createLogs(@RequestBody UsersLogs usersLogs, @RequestParam Long logTypesId, String userName) {
        return userLogsService.createLoginLogs(usersLogs, logTypesId,userName);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<UsersLogs> usersLogsPage;
        usersLogsPage = userLogsService.getActiveUserLogs(page, size, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("logs", usersLogsPage.getContent());
        response.put("currentPage", usersLogsPage.getNumber());
        response.put("totalItems", usersLogsPage.getTotalElements());
        response.put("totalPages", usersLogsPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<Map<String, Object>> getLogsByUser(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam Long userId) {
        Page<UsersLogs> usersLogsPage;
        usersLogsPage = userLogsService.getLogsByUser(page, size, sortBy, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("logs", usersLogsPage.getContent());
        response.put("currentPage", usersLogsPage.getNumber());
        response.put("totalItems", usersLogsPage.getTotalElements());
        response.put("totalPages", usersLogsPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("type")
    public ResponseEntity<Map<String, Object>> getLogsByType(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam Long typeId) {
        Page<UsersLogs> usersLogsPage;
        usersLogsPage = userLogsService.getLogsByType(page, size, sortBy,typeId);
        Map<String, Object> response = new HashMap<>();
        response.put("logs", usersLogsPage.getContent());
        response.put("currentPage", usersLogsPage.getNumber());
        response.put("totalItems", usersLogsPage.getTotalElements());
        response.put("totalPages", usersLogsPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("logsType")
    public ResponseEntity getAllType(){
        return ResponseEntity.ok().body(userLogsService.getAllType());
    }


}
