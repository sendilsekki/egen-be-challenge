package com.egen.domain;

/**
 * Business Object for User Alert data.
 * @author SENDIL SEKKI
 */
public class UserAlertData
{
    private long timeStamp;
    private String alertMessage;
    
    public long getTimeStamp()
    {
        return timeStamp;
    }
    
    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
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
