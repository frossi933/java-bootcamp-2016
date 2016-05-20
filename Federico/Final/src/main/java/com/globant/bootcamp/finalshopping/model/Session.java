package com.globant.bootcamp.finalshopping.model;




import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@ApiModel(value = "Session entity", description = "Represents a login session")
public class Session {

    @Id
    @ApiModelProperty(value = "Token generated randomly", required = true)
    private String token;
    @ApiModelProperty(value = "Username of logged user", required = true)
    private String username;
    @ApiModelProperty(value = "Time when started the session", required = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Transient
    @ApiModelProperty(value = "Token generator", hidden = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;

        Session session = (Session) o;

        if (!getToken().equals(session.getToken())) return false;
        if (!getUsername().equals(session.getUsername())) return false;
        return getCreated().equals(session.getCreated());

    }

    @Override
    public int hashCode() {
        int result = getToken().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getCreated().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", created=" + created +
                '}';
    }
}
