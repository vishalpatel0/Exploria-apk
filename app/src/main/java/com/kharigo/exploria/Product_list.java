package com.kharigo.exploria;

public class Product_list {
    private String id;
    private String type;
    private String user;
    private String info;
    private String img;
    private String like;
    private String comment;
    private String share;
    private String map;
    private String time;
    private String other;

    public Product_list(String id, String type, String user, String info, String img, String like, String comment, String share, String map, String time, String other) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.info = info;
        this.img = img;
        this.like = like;
        this.comment = comment;
        this.share = share;
        this.map = map;
        this.time = time;
        this.other = other;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getInfo() {
        return info;
    }

    public String getImg() {
        return img;
    }

    public String getLike() {
        return like;
    }

    public String getComment() {
        return comment;
    }

    public String getShare() {
        return share;
    }

    public String getMap() {
        return map;
    }

    public String getTime() {
        return time;
    }

    public String getOther() {
        return other;
    }
}
