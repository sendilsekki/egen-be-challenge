package com.egen.dao.mongodb.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Entity object for "Alerts" MongoDB collection.
 * @author SENDIL SEKKI
 */
@Entity("Alerts")
public class UserAlert
{
    @Id
    private ObjectId id;
    private long timestamp;
    private String alertMessage;
    
    public ObjectId getId()
    {
        return id;
    }
    
    public void setId(ObjectId id)
    {
        this.id = id;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
    
    public String getAlertMessage()
    {
        return alertMessage;
    }
    
    public void setAlertMessage(String alertMessage)
    {
        this.alertMessage = alertMessage;
    }
}
