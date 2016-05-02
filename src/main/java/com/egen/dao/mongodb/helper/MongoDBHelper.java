package com.egen.dao.mongodb.helper;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

/**
 * MongoDB Helper class. It gives single instance of Datastore object for DB operations.
 * @author SENDIL SEKKI
 */
@Component
@ConfigurationProperties(prefix="db.mongodb")
public class MongoDBHelper
{
    //loaded from the properties file
    private String dbname;
    
    private Datastore datastore;
    
    public MongoDBHelper() throws UnknownHostException
    {
        //this.loadDatastore();
    }
    
    public Datastore getDatastore() throws UnknownHostException
    {
        if(this.datastore == null)
        {
            this.loadDatastore();
        }
        
        return this.datastore;
    }
    
    public void loadDatastore() throws UnknownHostException  
    {
        final Morphia morphia = new Morphia();
        
        // tell Morphia where to find your classes
        morphia.mapPackage("com.egen.dao.mongodb.entity");
        
        // create the Datastore connecting to the default port on the local host
        datastore = morphia.createDatastore(new MongoClient(), dbname);
        datastore.ensureIndexes();
    }
    
    public String getDbname()
    {
        return dbname;
    }

    public void setDbname(String dbname)
    {
        this.dbname = dbname;
    }
    
}
