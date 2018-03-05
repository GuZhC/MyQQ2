package com.rongyuan.mingyida.model;

import java.util.List;

/**
 * Created by guZhongC on 2018/2/26.
 * describe:
 */

public class CommentModel {
    private List<String> commentimages;
    private String username;
    private String headimage;
    private String commentContent;
    private String time;
    private float starLevel;

    public List<String> getCommentimages() {
        return commentimages;
    }

    public void setCommentimages(List<String> commentimages) {
        this.commentimages = commentimages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(float starLevel) {
        this.starLevel = starLevel;
    }
}
