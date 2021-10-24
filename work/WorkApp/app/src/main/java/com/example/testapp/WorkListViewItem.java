package com.example.testapp;

public class WorkListViewItem {
    // 아이템 타입을 구분하기 위한 type 변수.
    private int type ;
    private int num;
    private String jobname;
    private String status;
    private String before;
    private String present;
    private String next;
    private String worker;  //작업자
    private int assigment;  //할당
    private int warehousing;     //입고
    private String bgcolor;
    private int joborderid;
    private int joborder_process_id;
    private int origin_account_id;
    private int focus_on;



    public void setType(int type) {
        this.type = type ;
    }
    public void setNum(int num) {
        this.num = num ;
    }
    public void setOrigin_account_id(int origin_account_id){this.origin_account_id= origin_account_id;};
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
    public void setWorker(String worker) {
        this.worker = worker ;
    }
    public void setAssigment(int assigment){this.assigment=assigment;}
    public void setFocus_on(int focus_on){this.focus_on=focus_on;};
    public void setWarehousing(int warehousing){this.warehousing=warehousing;};
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
    public String getWorker() {
        return this.worker ;
    }
    public int getAssigment(){ return this.assigment;}
    public int getFocus_on(){ return this.focus_on;}
    public int getWarehousing(){ return this.warehousing;}
    public String getBgcolor() { return this.bgcolor; }
    public int getOrigin_account_id(){ return this.origin_account_id;}
    public int getJoborderid() {
        return this.joborderid ;
    }
    public int getJoborder_process_id() { return this.joborder_process_id ; }

}
