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
import org.springframework.stereotype.Component;

import com.egen.dao.UserAlertDao;
import com.egen.dao.mongodb.entity.UserAlert;
import com.egen.dao.mongodb.helper.MongoDBHelper;
import com.egen.domain.UserAlertData;

/**
 * MongoDB Implementation class for User Alert DB operations. 
 * @author SENDIL SEKKI
 */
@Component
public class UserAlertDaoImpl implements UserAlertDao
{
    @Autowired
    private MongoDBHelper mongoDBHelper;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /*
     * (non-Javadoc)
     * @see com.egen.dao.UserAlertDao#createUserAlert(com.egen.domain.UserAlertData)
     */
    @Override
    public String createUserAlert(UserAlertData userAlertData) throws UnknownHostException
    {
        String savedObjectId = null;
        if(userAlertData != null)
        {    
            Datastore datastore = mongoDBHelper.getDatastore();
        
            UserAlert userAlert = new UserAlert();
            userAlert.setTimestamp(userAlertData.getTimeStamp());
            userAlert.setAlertMessage(userAlertData.getAlertMessage());
        
            Key<UserAlert> savedUserAlert = datastore.save(userAlert);
            savedObjectId = savedUserAlert.getId().toString();
            
            log.debug("Saved Alert ObjectId : " + savedObjectId);
        }
        
        return savedObjectId;

    }
    
    /*
     * (non-Javadoc)
     * @see com.egen.dao.UserAlertDao#getUserAlertList(long, long)
     */
    @Override
    public List<UserAlertData> getUserAlertList(long fromTimeStamp, long toTimeStamp) throws UnknownHostException
    {
        List<UserAlertData> userAlertDataList = null;
        List<UserAlert> userAlertList = null;
        
        Datastore datastore = mongoDBHelper.getDatastore();
        Query<UserAlert> query = datastore.createQuery(UserAlert.class);
        
        if(fromTimeStamp > 0)
        {
            query.filter("timestamp >=", fromTimeStamp);
        }
        if(toTimeStamp > 0)
        {
            query.filter("timestamp <=", toTimeStamp);
        }
        
        userAlertList = query.asList();
        userAlertDataList = this.loadUserData(userAlertList);
        
        return userAlertDataList;
    }
    
    /**
     * Converts UserAlert Entity object List to UserAlertData List.
     * @param userAlertList
     * @return
     */
    private List<UserAlertData> loadUserData(List<UserAlert> userAlertList)
    {
        List<UserAlertData> UserAlertDataList = null;
        
        if(userAlertList != null && userAlertList.isEmpty() == false)
        {
            UserAlertDataList = new ArrayList<UserAlertData>();
            for(UserAlert userAlert : userAlertList)
            {
                UserAlertData userAlertData = new UserAlertData();
                userAlertData.setTimeStamp(userAlert.getTimestamp());
                userAlertData.setAlertMessage(userAlert.getAlertMessage());
                
                UserAlertDataList.add(userAlertData);
            }
        }
        
        return UserAlertDataList;
    }

}
