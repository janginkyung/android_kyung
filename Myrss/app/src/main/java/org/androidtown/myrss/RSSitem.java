package org.androidtown.myrss;

import android.graphics.drawable.Drawable;

/**
 * Created by InKyung on 2017-02-06.
 */

public class RSSitem {
    String title, link, description, image, dcDate ;

    public RSSitem(){}
    public RSSitem(String title, String link,String image,String dcDate,String description){

        this.title=title ;
        this.link=link ;
        this.image=image ;
        this.dcDate=dcDate ;
        this.description=description ;
    }

    public String getDcDate() {
        return dcDate;
    }
    private Drawable mIcon;
    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }
    public Drawable getIcon() {
        return mIcon;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
