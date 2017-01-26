package org.androidtown.mylist;

/**
 * Created by InKyung on 2017-01-25.
 */

public class Singeritem {
    String name, age ;
    public Singeritem(){

    }
    public Singeritem(String name,String age) {
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
