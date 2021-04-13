package com.omelnur.roya.royaServiceApp.auth.service.userLogs;


import com.omelnur.roya.royaServiceApp.auth.models.UsersLogs;

/**
 * @author michael wagih
 */
public class StaticLog {
    public static UsersLogs createLogin(String userName){
        UsersLogs usersLogs = new UsersLogs();
        usersLogs.setDescription(userName+"Login at System");
        return usersLogs;
    }
}
