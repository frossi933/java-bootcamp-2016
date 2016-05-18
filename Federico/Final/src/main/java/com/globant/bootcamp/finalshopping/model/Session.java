package com.globant.bootcamp.finalshopping.model;




import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Session {

    @Id
    private String token;
    private String username;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Transient
    private static SessionIdGenerator generator = new SessionIdGenerator();

    public Session(){
    }

    public Session(String username){
        this.username = username;
        created = DateTime.now();
        token = generator.nextSessionId();
    }


    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public DateTime getCreated() {
        return created;
    }

    public void updateTime(){
        created = DateTime.now();
    }
}
