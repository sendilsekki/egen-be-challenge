package com.egen.rules.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.dao.UserAlertDao;
import com.egen.domain.UserAlertData;
import com.egen.domain.UserWeightData;

@Component
@Rule(name="Under Weight Rule", description ="Checks whether the User's given weight is under weight")
public class UnderWeightRule 
{
    private static final String UNDERWEIGHT_RULE_ALERT = "User Is Under Weight";
    
    private UserWeightData userWeightData;
    
    @Autowired
    private UserAlertDao userAlertDao;
    
    @Condition
    public boolean when() 
    {
        boolean ruleSuccess = false;
        short currentWeight = userWeightData.getValue();
        short baseWeight = userWeightData.getBaseWeight();
        
        // Checking whether the user current weight is less then 10% of base weight
        if (currentWeight < ((0.9)* baseWeight))
        {
            ruleSuccess = true;
        }
        
        return ruleSuccess;
    }
    
    @Action(order = 1)
    public void then() throws Exception 
    {
        UserAlertData userAlertData = new UserAlertData();
        userAlertData.setTimeStamp(this.getUserWeightData().getTimeStamp());
        userAlertData.setAlertMessage(UNDERWEIGHT_RULE_ALERT);
        
        userAlertDao.createUserAlert(userAlertData);
    }
    
    public UserWeightData getUserWeightData()
    {
        return userWeightData;
    }

    public void setUserWeightData(UserWeightData userWeightData)
    {
        this.userWeightData = userWeightData;
    }
   

}
