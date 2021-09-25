package com.omelnur.roya.royaServiceApp.reports.controller;

import com.omelnur.roya.royaServiceApp.reports.service.StatiscicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/reports/statistics")
public class StatisticsController {
    /**
     * todo: should to get reports
     */

    @Autowired
    StatiscicsService statiscicsService;

    @GetMapping
    public ResponseEntity getStatistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(statiscicsService.getStatistcs(start, end));
    }

}
