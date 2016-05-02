package com.egen.service;

import java.util.List;

import com.egen.domain.UserWeightData;

/**
 * User Metrics Rest Service interface. This service is used for creating and fetching User Metrics data.
 * @author SENDIL SEKKI
 */
public interface UserMetricsService
{
    /**
     * Creates and User Metrics data with the given input parameter.
     * @param userData
     */
    public void create(UserWeightData userData) ;
    
    /**
     * Returns all the User Metrics data.
     * @return
     */
    public List<UserWeightData> getUserMetrics();
    
    /**
     * Returns the User Metrics data for the given parameters.
     * @param fromTimeStamp
     * @param toTimeStamp
     * @return
     */
    public List<UserWeightData> getUserMetrics(long fromTimeStamp, long toTimeStamp);
}
