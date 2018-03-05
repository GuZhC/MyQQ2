package com.rongyuan.mingyida.model;

/**
 * Created by guZhongC on 2018/3/1.
 * describe:
 */

public class BannerModel {

    /**
     * bid : 1519785788513
     * name :
     * url_before : http://116.62.235.64/ap/
     * url_after : upload/banner/KRLarrGa2duEZvOXW9woUTq1ZWQFcK85AAFJs1ElqgsLpDLDhCGk8MNH0nTxSjxr.png
     * info : no data
     */

    private long bid;
    private String name;
    private String url_before;
    private String url_after;
    private String info;

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_before() {
        return url_before;
    }

    public void setUrl_before(String url_before) {
        this.url_before = url_before;
    }

    public String getUrl_after() {
        return url_after;
    }

    public void setUrl_after(String url_after) {
        this.url_after = url_after;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
