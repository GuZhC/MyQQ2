package com.rongyuan.mingyida.model;

/**
 * Created by guZhongC on 2018/2/27.
 * describe:
 */

public class MyAddressModel {
    private String name;
    private String phone;
    private String address;
    private  String detale_address;
    private boolean isdefault;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetale_address() {
        return detale_address;
    }

    public void setDetale_address(String detale_address) {
        this.detale_address = detale_address;
    }

    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }
}
