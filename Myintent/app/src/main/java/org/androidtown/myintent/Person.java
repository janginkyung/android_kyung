package org.androidtown.myintent;

import java.io.Serializable;

/**
 * Created by InKyung on 2017-01-19.
 */

public class Person implements Serializable {
    String name ;
    int age ;
    public Person(){

    }
    public Person(String name, int age){
        this.name=name ;
        this.age=age ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
