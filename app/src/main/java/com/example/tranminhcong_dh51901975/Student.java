package com.example.tranminhcong_dh51901975;

public class Student {
    private String ten;
    private String dtb;

    public Student(String ten, String dtb) {
        this.ten = ten;
        this.dtb = dtb;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDtb() {
        return dtb;
    }

    public void setDtb(String dtb) {
        this.dtb = dtb;
    }
}
