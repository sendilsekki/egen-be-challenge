package com.egen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.domain.UserWeightData;
import com.egen.manager.UserManager;

/**
 * User Metrics Rest Service Implementation class.
 * @author SENDIL SEKKI
 */
@Service("userMetrics")
public class UserMetricsServiceImpl implements UserMetricsService
{
    @Autowired
    private UserManager userManager;
    
    /*
     * (non-Javadoc)
     * @see com.egen.service.UserMetricsService#create(com.egen.domain.UserWeightData)
     */
    @Override
    public void create(UserWeightData userWeightData)
    {
        this.userManager.addUserMetricsData(userWeightData);
    }
    
    /*
     * (non-Javadoc)
     * @see com.egen.service.UserMetricsService#getUserMetrics()
     */
    @Override
    public List<UserWeightData> getUserMetrics()
    {
        //Calling the "getUserDataList" method with 0, 0 to get all the Metrics data.
        return this.userManager.getUserDataList(0, 0); 
    }
    
    /*
     * (non-Javadoc)
     * @see com.egen.service.UserMetricsService#getUserMetrics(long, long)
     */
    @Override
    public List<UserWeightData> getUserMetrics(long fromTimeStamp, long toTimeStamp)
    {
        return this.userManager.getUserDataList(fromTimeStamp, toTimeStamp);
    }
}
