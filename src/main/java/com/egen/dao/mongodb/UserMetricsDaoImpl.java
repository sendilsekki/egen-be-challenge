package com.egen.dao.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.egen.dao.UserMetricsDao;
import com.egen.dao.mongodb.entity.UserMetrics;
import com.egen.dao.mongodb.helper.MongoDBHelper;
import com.egen.domain.UserWeightData;

/**
 * MongoDB Implementation class for User Metric DB operations.
 * @author SENDIL SEKKI
 */
@Component
@ConfigurationProperties(prefix="user")
public class UserMetricsDaoImpl implements UserMetricsDao
{
    //User Base Weight, loaded from properties
    private short baseWeight;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private MongoDBHelper mongoDBHelper;
    
    /*
     * (non-Javadoc)
     * @see com.egen.dao.UserMetricsDao#createUserMetrics(com.egen.domain.UserWeightData)
     */
    @Override
    public String createUserMetrics(UserWeightData userData) throws UnknownHostException
    {
        String savedObjectId = null;
        if(userData != null)
        {    
            Datastore datastore = mongoDBHelper.getDatastore();
        
            UserMetrics userMetrics = new UserMetrics();
            userMetrics.setTimestamp(userData.getTimeStamp());
            userMetrics.setCurrentWeight(userData.getValue());
        
            Key<UserMetrics> savedUserMetrics = datastore.save(userMetrics);
            savedObjectId = savedUserMetrics.getId().toString();
            
            log.debug("savedObjectId : " + savedObjectId);
        }
        
        return savedObjectId;
    }

    /*
     * (non-Javadoc)
     * @see com.egen.dao.UserMetricsDao#getUserMetricsList(long, long)
     */
    @Override
    public List<UserWeightData> getUserMetricsList(long fromTimeStamp, long toTimeStamp)throws UnknownHostException
    {
        List<UserWeightData> userDataList = null;
        List<UserMetrics> userMetricsList = null;
        
        Datastore datastore = mongoDBHelper.getDatastore();
        Query<UserMetrics> query = datastore.createQuery(UserMetrics.class);
        
        if(fromTimeStamp > 0)
        {
            query.filter("timestamp >=", fromTimeStamp);
        }
        if(toTimeStamp > 0)
        {
            query.filter("timestamp <=", toTimeStamp);
        }
        
        userMetricsList = query.asList();
        userDataList = this.loadUserData(userMetricsList);
        
        return userDataList;
    }
    
    /**
     * Converts UserMetrics Entity object List to UserWeightData List. 
     * @param userMetricsList
     * @return
     */
    private List<UserWeightData> loadUserData(List<UserMetrics> userMetricsList)
    {
        List<UserWeightData> userWeightDataList = null;
        
        if(userMetricsList != null && userMetricsList.isEmpty() == false)
        {
            userWeightDataList = new ArrayList<UserWeightData>();
            for(UserMetrics userMetrics : userMetricsList)
            {
                UserWeightData tempUserWeightData = new UserWeightData();
                tempUserWeightData.setTimeStamp(userMetrics.getTimestamp());
                tempUserWeightData.setValue(userMetrics.getCurrentWeight());
                tempUserWeightData.setBaseWeight(baseWeight);
                
                userWeightDataList.add(tempUserWeightData);
            }
        }
        
        return userWeightDataList;
    }
    
    public short getBaseWeight()
    {
        return baseWeight;
    }

    public void setBaseWeight(short baseWeight)
    {
        this.baseWeight = baseWeight;
    }

}
