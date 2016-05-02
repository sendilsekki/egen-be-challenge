package com.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egen.domain.UserAlertData;
import com.egen.service.UserAlertService;

/**
 * Rest Controller for User Alert Fetch operations.
 * @author SENDIL SEKKI
 */
@RestController
@RequestMapping("/userAlert")
public class UserAlertController
{
    @Autowired
    private UserAlertService userAlertService;

    @RequestMapping(value="/read", method=RequestMethod.GET)
    public List<UserAlertData> getUserAlerts() 
    {
        return this.userAlertService.getUserAlerts();
    }
    
    @RequestMapping(value="/readByTimeRange", method=RequestMethod.GET)
    public List<UserAlertData> getUserAlerts(@RequestParam(value="fromTimeStamp") long fromTimeStamp, @RequestParam(value="toTimeStamp") long toTimeStamp) 
    {
        return this.userAlertService.getUserAlerts(fromTimeStamp, toTimeStamp);
    }
}
