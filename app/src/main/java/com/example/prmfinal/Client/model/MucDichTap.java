package com.example.prmfinal.Client.model;

public class MucDichTap {

    private long id;
    private String title;
    private int thoiGianTap;
    private long thoiGianNghi;

    public MucDichTap() {
    }

    public MucDichTap( String title, int thoiGianTap, long thoiGianNghi) {
        this.title = title;
        this.thoiGianTap = thoiGianTap;
        this.thoiGianNghi = thoiGianNghi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThoiGianTap() {
        return thoiGianTap;
    }

    public void setThoiGianTap(int thoiGianTap) {
        this.thoiGianTap = thoiGianTap;
    }

    public long getThoiGianNghi() {
        return thoiGianNghi;
    }

    public void setThoiGianNghi(long thoiGianNghi) {
        this.thoiGianNghi = thoiGianNghi;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
