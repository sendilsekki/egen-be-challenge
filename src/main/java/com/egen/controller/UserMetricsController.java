package com.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.domain.UserWeightData;
import com.egen.service.UserMetricsService;

/**
 * Rest Controller for User Metrics Create, Fetch operations.
 * @author SENDIL SEKKI
 */
@RestController
@RequestMapping("/userMetrics")
public class UserMetricsController 
{
    @Autowired
    private UserMetricsService userMetricsService;

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public void create(@RequestBody UserWeightData userWeightData) 
    {
        this.userMetricsService.create(userWeightData);
	}
    
    @RequestMapping(value="/read", method=RequestMethod.GET)
    public List<UserWeightData> getUserMetrics() 
    {
        return this.userMetricsService.getUserMetrics();
    }
    
    @RequestMapping(value="/readByTimeRange", method=RequestMethod.GET)
    public List<UserWeightData> getUserMetrics(@RequestParam(value="fromTimeStamp") long fromTimeStamp, 
            @RequestParam(value="toTimeStamp") long toTimeStamp) 
    {
        return this.userMetricsService.getUserMetrics(fromTimeStamp, toTimeStamp);
    }
}
