package com.egen.dao.mongodb.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Entity object for "Metrics" MongoDB collection.
 * @author SENDIL SEKKI
 */
@Entity("Metrics")
public class UserMetrics
{
    @Id
    private ObjectId id;
    private long timestamp;
    private short currentWeight;
    
    
    public ObjectId getId()
    {
        return id;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }
    
    public short getCurrentWeight()
    {
        return currentWeight;
    }
    
    public void setCurrentWeight(short currentWeight)
    {
        this.currentWeight = currentWeight;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
}
