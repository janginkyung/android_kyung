package orgm.androidtown.mission17;

/**
 * Created by InKyung on 2017-02-17.
 */

public class videoitem {
    int id ;
    long date ;

    public videoitem(){

    }
    public videoitem(long date, int id) {
        this.date = date;
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
