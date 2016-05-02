package com.egen.domain;

/**
 * Business Object for User Metrics data
 * @author SENDIL SEKKI
 */
public class UserWeightData
{
    private long timeStamp;
    private short value;
    private short baseWeight;

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public short getValue()
    {
        return value;
    }

    public void setValue(short value)
    {
        this.value = value;
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
