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
@Rule(name="Over Weight Rule", description ="Checks whether the User's given weight is over weight")
public class OverWeightRule
{
private static final String OVERWEIGHT_RULE_ALERT = "User Is Over Weight";
    
    private UserWeightData userWeightData;
    
    @Autowired
    private UserAlertDao userAlertDao;
    
    @Condition
    public boolean when() 
    {
        boolean ruleSuccess = false;
        short currentWeight = userWeightData.getValue();
        short baseWeight = userWeightData.getBaseWeight();
        
        // Checking whether the user current weight is greater then 10% of base weight
        if (currentWeight > ((1.1)* baseWeight))
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
        userAlertData.setAlertMessage(OVERWEIGHT_RULE_ALERT);
        
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
