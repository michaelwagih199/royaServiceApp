package com.omelnur.roya.royaServiceApp.reports.service;

import com.omelnur.roya.royaServiceApp.reports.models.StatistcsModel;

import java.util.Date;

public interface StatiscicsService {
    public abstract StatistcsModel getStatistcs(Date start, Date end);
}
