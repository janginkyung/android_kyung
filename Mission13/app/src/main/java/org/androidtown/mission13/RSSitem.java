package org.androidtown.mission13;

/**
 * Created by InKyung on 2017-02-10.
 */

public class RSSitem {

    String day="";
    String cloud="";
    String tempN="";
    String tempM ="";
    public RSSitem(){

    }
    public RSSitem    (String day,String cloud,String tempN,String tempM){
        this.day=day ;
        this.cloud=cloud ;
        this.tempN=tempN ;
        this.tempM=tempM ;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getTempM() {
        return tempM;
    }

    public void setTempM(String tempM) {
        this.tempM = tempM;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTempN() {
        return tempN;
    }

    public void setTempN(String tempN) {
        this.tempN = tempN;
    }
}
