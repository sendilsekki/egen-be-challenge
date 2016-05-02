package com.egen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.domain.UserAlertData;
import com.egen.manager.UserManager;

/**
 * User Alert Rest Service Implementation class.
 * @author SENDIL SEKKI
 */
@Service("userAlert")
public class UserAlertServiceImpl implements UserAlertService
{
    @Autowired
    private UserManager userManager;

    /*
     * (non-Javadoc)
     * @see com.egen.service.UserAlertService#getUserAlerts()
     */
    @Override
    public List<UserAlertData> getUserAlerts()
    {
        return this.userManager.getUserAlertList(0, 0);
    }

    /*
     * (non-Javadoc)
     * @see com.egen.service.UserAlertService#getUserAlerts(long, long)
     */
    @Override
    public List<UserAlertData> getUserAlerts(long fromTimeStamp, long toTimeStamp)
    {
        return this.userManager.getUserAlertList(fromTimeStamp, toTimeStamp);
    }

}
