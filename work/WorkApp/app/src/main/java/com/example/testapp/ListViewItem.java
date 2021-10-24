package com.example.testapp;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewItem {
    // 아이템 타입을 구분하기 위한 type 변수.
    private int type ;
    private int num;
    private String jobname;
    private String status;
    private String before;
    private String present;
    private String next;
    private int warehouse_type;
    private String bgcolor;
    private int joborderid;
    private int joborder_process_id;

    public void setType(int type) {
        this.type = type ;
    }
    public void setNum(int num) {
        this.num = num ;
    }
    public void setJobname(String jobname) {
        this.jobname = jobname ;
    }
    public void setStatus(String status) {
        this.status = status ;
    }
    public void setBefore(String before) {
        this.before = before ;
    }
    public void setPresent(String present) {
        this.present = present ;
    }
    public void setNext(String next) {
        this.next = next ;
    }
    public void setWarehouse_type(int warehouse_type) {
        this.warehouse_type = warehouse_type ;
    }
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor ;
    }
    public void setJoborderid(int joborderid) {
        this.joborderid = joborderid ;
    }
    public void setJoborder_process_id(int joborder_process_id) { this.joborder_process_id = joborder_process_id ;
    }


    public int getType() {
        return this.type ;
    }
    public int getNum() {
        return this.num ;
    }
    public String getJobname() {
        return this.jobname ;
    }
    public String getStatus() {
        return this.status ;
    }
    public String getBefore() { return this.before ; }
    public String getPresent() {
        return this.present ;
    }
    public String getNext() {
        return this.next ;
    }
    public int getWarehouse_type() {
        return this.warehouse_type ;
    }
    public String getBgcolor() { return this.bgcolor; }
    public int getJoborderid() {
        return this.joborderid ;
    }
    public int getJoborder_process_id() { return this.joborder_process_id ; }
}
