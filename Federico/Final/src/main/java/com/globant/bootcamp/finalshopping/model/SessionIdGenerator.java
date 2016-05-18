package com.globant.bootcamp.finalshopping.model;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SessionIdGenerator {

    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
