package com.egen.dao;

import java.net.UnknownHostException;
import java.util.List;

import com.egen.domain.UserAlertData;

/**
 * Data access layer Interface for User Alert DB operations.
 * @author SENDIL SEKKI
 */
public interface UserAlertDao
{
    /**
     * Adds a new User Alert to the DB. 
     * @param userAlertData
     * @return
     * @throws UnknownHostException
     */
    public String createUserAlert(UserAlertData userAlertData)throws UnknownHostException;
    
    /**
     * Gets the User Alert List data for the given parameters.
     * @param fromTimeStamp if this value is less than zero then it is not considered.
     * @param toTimeStamp if this value is less than zero then it is not considered.
     * @return
     * @throws UnknownHostException
     */
    public List<UserAlertData> getUserAlertList(long fromTimeStamp, long toTimeStamp)throws UnknownHostException;

}
