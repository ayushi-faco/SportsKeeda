package com.kayushi07.sportskeeda;

public class Cards {
    String name, imageUrl, publishedDate;


    public Cards(String name, String imageUrl, String publishedDate) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.publishedDate = publishedDate;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


}
