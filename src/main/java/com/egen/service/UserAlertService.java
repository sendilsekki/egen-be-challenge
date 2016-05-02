package com.egen.service;

import java.util.List;

import com.egen.domain.UserAlertData;

/**
 * User Alert Rest Service interface. This service is used for fetching User Alert information.
 * @author SENDIL SEKKI
 */
public interface UserAlertService
{
    /**
     * @return Returns All the User Alert data.
     */
    public List<UserAlertData> getUserAlerts();
    
    /**
     * Returns the User Alert data for the given parameters.
     * @param fromTimeStamp
     * @param toTimeStamp
     * @return
     */
    public List<UserAlertData> getUserAlerts(long fromTimeStamp, long toTimeStamp);
}
