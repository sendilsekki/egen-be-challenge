package com.egen.manager;

import java.util.List;

import com.egen.domain.UserAlertData;
import com.egen.domain.UserWeightData;

/**
 * Main Manager class for the User.
 * @author SENDIL SEKKI
 */
public interface UserManager
{
    /**
     * Adds the User Metrics Data to the DB.
     * Executes the Rules and saves the Alert to DB through Rules
     * @param userData
     * @return
     */
    public String addUserMetricsData(UserWeightData userData);
    
    /**
     * Returns the User Data List for the given parameters.
     * @param fromTimeStamp
     * @param toTimeStamp
     * @return
     */
    public List<UserWeightData> getUserDataList(long fromTimeStamp, long toTimeStamp);
    
    /**
     * Returns the User Alert Data List for the given parameters.
     * @param fromTimeStamp
     * @param toTimeStamp
     * @return
     */
    public List<UserAlertData> getUserAlertList(long fromTimeStamp, long toTimeStamp);
}
