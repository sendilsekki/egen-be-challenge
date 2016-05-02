package com.egen.manager;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.net.UnknownHostException;
import java.util.List;

import org.easyrules.api.RulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.egen.dao.UserAlertDao;
import com.egen.dao.UserMetricsDao;
import com.egen.domain.UserAlertData;
import com.egen.domain.UserWeightData;
import com.egen.rules.easyrules.OverWeightRule;
import com.egen.rules.easyrules.UnderWeightRule;

/**
 * Implementation class for the User Manager. This class has the User related business logic.
 * @author SENDIL SEKKI
 */
@Component
@ConfigurationProperties("user")
public class UserManagerImpl implements UserManager
{
    //User Base Weight, loaded from properties
    private short baseWeight;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserMetricsDao userMetricsDao;
    @Autowired
    private UserAlertDao userAlertDao;
    @Autowired
    private OverWeightRule overWeightRule;
    @Autowired
    private UnderWeightRule underWeightRule;
    
    /*
     * (non-Javadoc)
     * @see com.egen.manager.UserManager#addUserMetricsData(com.egen.domain.UserWeightData)
     */
    @Override
    public String addUserMetricsData(UserWeightData userWeightData)
    {
        String userMetricsId = null;
        
        try
        {
            if(userWeightData != null)
            {    
                //Validate the input data
                short userCurrentWeight = userWeightData.getValue();
                Assert.isTrue(userCurrentWeight <= 600, "Wrong User Data"); //Checking if the User Weight is more than 600 (Pounds) then error.
                Assert.isTrue(userCurrentWeight > 0 , "Wrong User Data"); //Checking if the User Weight <= 0 (Pounds) then error.
                
                userMetricsId = this.userMetricsDao.createUserMetrics(userWeightData);
            
                if(userMetricsId != null)
                {
                    //setting business object to rules
                    userWeightData.setBaseWeight(this.baseWeight);
                    underWeightRule.setUserWeightData(userWeightData);
                    overWeightRule.setUserWeightData(userWeightData);
                
                    //Executing the rules
                    RulesEngine rulesEngine = aNewRulesEngine().build();
                    rulesEngine.registerRule(underWeightRule);
                    rulesEngine.registerRule(overWeightRule);
                    rulesEngine.fireRules();
                }
            }
        }
        catch(UnknownHostException unknownHostException)
        {
            log.error("Error Occuried While creating User Metrics Data", unknownHostException);
        }
        
        return userMetricsId;
    }

    /*
     * (non-Javadoc)
     * @see com.egen.manager.UserManager#getUserDataList(long, long)
     */
    @Override
    public List<UserWeightData> getUserDataList(long fromTimeStamp, long toTimeStamp)
    {
        List<UserWeightData> userWeightDataList = null;
        
        try
        {
            userWeightDataList = this.userMetricsDao.getUserMetricsList(fromTimeStamp, toTimeStamp);
        }
        catch(UnknownHostException unknownHostException)
        {
            log.error("Error Occuried While fetching User Metrics Data", unknownHostException);
        }
                
        return userWeightDataList;
    }
    
    /*
     * (non-Javadoc)
     * @see com.egen.manager.UserManager#getUserAlertList(long, long)
     */
    @Override
    public List<UserAlertData> getUserAlertList(long fromTimeStamp, long toTimeStamp)
    {
        List<UserAlertData> userAlertDataList = null;
        
        try
        {
            userAlertDataList = userAlertDao.getUserAlertList(fromTimeStamp, toTimeStamp);
        }
        catch(UnknownHostException unknownHostException)
        {
            log.error("Error Occuried While fetching User Alert Data", unknownHostException);
        }
        
        return userAlertDataList;
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
