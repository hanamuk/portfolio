package com.example.testapp;

public class IndividualListViewItem {

    // 아이템 타입을 구분하기 위한 type 변수.
    private int type ;
    private int num;
    private String jobname;
    private String status;
    private String before;
    private String present;
    private String next;
    private String worker;
    private int work_status;//작업상태

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
    public void setWorker(String worker) {
        this.worker = worker ;
    }
    public void setWork_status(int work_status){this.work_status=work_status;};


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
    public int getWork_status(){ return this.work_status;}
}
