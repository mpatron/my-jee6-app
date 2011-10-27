package org.glassfish.samples;

import javax.ejb.Stateless;

@Stateless
public class SimpleEJB {
    public String sayHello(String name) {
        return "Hello " + name + "!!!";
    }
}
