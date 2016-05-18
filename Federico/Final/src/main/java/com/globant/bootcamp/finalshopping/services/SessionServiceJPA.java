package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.model.Session;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;


public class SessionServiceJPA implements SessionService {

    private SessionRepository repo;
    private final int MAX_TIME_SESSION = 1200000; // 20' in milliseconds

    @Autowired
    public SessionServiceJPA(SessionRepository repo){
        this.repo = repo;
    }

    @Override
    public boolean add(Session session) {
        Session old = repo.findByUsername(session.getUsername());
        if(old != null)
            /* It is already logged */
             return false;

        repo.save(session);
        return true;
    }

    @Override
    public boolean check(String token, String username) {
        Session session = repo.findOne(token);
        if(session != null && session.getUsername().equals(username)){

            long diff = DateTime.now().getMillis() - session.getCreated().getMillis();
            if(diff >= MAX_TIME_SESSION) {
                /* timed out */
                repo.delete(session);
                return false;
            } else {
                session.updateTime();
                repo.save(session);
                return true;
            }
        } else
            return false;
    }
}
