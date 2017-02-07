package org.androidtown.myrss;

/**
 * Created by InKyung on 2017-02-06.
 */

public class RSSitem {
    String title, link, category, author,pubDate,description ;

    public RSSitem(){}
    public RSSitem(String title, String link,String category,String author,String pubDate,String description){

        this.title=title ;
        this.link=link ;
        this.category=category ;
        this.author=author ;
        this.pubDate=pubDate ;
        this.description=description ;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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
