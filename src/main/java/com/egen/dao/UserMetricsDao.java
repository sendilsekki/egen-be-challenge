package com.egen.dao;

import java.net.UnknownHostException;
import java.util.List;

import com.egen.domain.UserWeightData;

/**
 * Data access layer Interface for User Metrics DB operations.
 * @author SENDIL SEKKI
 */
public interface UserMetricsDao
{
    /**
     * Adds a new User Metrics data to the DB.
     * @param userData
     * @return
     * @throws UnknownHostException
     */
    public String createUserMetrics(UserWeightData userData)throws UnknownHostException;
    
    /**
     * Gets the User Metrics List data for the given parameters.
     * @param fromTimeStamp if this value is less than zero then it is not considered.
     * @param toTimeStamp if this value is less than zero then it is not considered.
     * @return
     * @throws UnknownHostException
     */
    public List<UserWeightData> getUserMetricsList(long fromTimeStamp, long toTimeStamp)throws UnknownHostException;
}
