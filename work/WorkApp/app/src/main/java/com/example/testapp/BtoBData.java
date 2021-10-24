package com.example.testapp;

public class BtoBData {

    private String JOB_NAME;
    private String PROGRESS_STATUS;
    private String STATE_BEFORE;
    private String STATE_PRESENT;
    private String STATE_NEXT;
    private String WORKER_NAME;
    private String COLOR;
    private int FOCUS_ON;
    private int ORIGIN_ACCOUNT_ID;
    private int JOB_PROCESS_ID;
    private int JOB_ID;

    //입고
    private int WAREHOSEING;
    //할당
    private int  ASSIGNMENT;

    public void SetFOCUS_ON(int focus_on){this.FOCUS_ON= focus_on;};
    public void SetJOB_NAME(String job_name){this.JOB_NAME= job_name;};
    public void SetJOB_ID(int job_id){this.JOB_ID= job_id;};
    public void SetORIGIN_ACCOUNT_ID(int origin_account_id){this.ORIGIN_ACCOUNT_ID= origin_account_id;};
    public void SetJOB_PROCESS_ID(int job_process_id){this.JOB_PROCESS_ID= job_process_id;};
    public void SetPROGRESS_STATUS(String progress_status){this.PROGRESS_STATUS=progress_status;};
    public void SetSTATE_BEFORE(String state_before){this.STATE_BEFORE=state_before;};
    public void SetSTATE_PRESENT(String  state_present){this.STATE_PRESENT= state_present;};
    public void SetSTATE_NEXT(String state_next){this.STATE_NEXT=state_next;};
    public void SetWORKER_NAME(String wname){this.WORKER_NAME= wname;};
    public void SetWAREHOSEING(int cancel){this.WAREHOSEING=cancel;};
    public void SetASSIGNMENT(int assignment){this.ASSIGNMENT=assignment;};
    public void SetCOLOR(String color){this.COLOR=color;};


    public String GetJOB_NAME(){ return this.JOB_NAME;}
    public String GetPROGRESS_STATUS(){ return this.PROGRESS_STATUS;}
    public String GetSTATE_BEFORE(){ return this.STATE_BEFORE;}
    public String GetSTATE_PRESENT(){ return this.STATE_PRESENT;}
    public String GetSTATE_NEXT(){ return this.STATE_NEXT;}
    public String GetWORKER_NAME(){ return this.WORKER_NAME;}
    public int GetFOCUS_ON(){ return this.FOCUS_ON;}
    public int GetWAREHOSEING(){ return this.WAREHOSEING;}
    public int GetASSIGNMENT(){ return this.ASSIGNMENT;}
    public int GetJOB_ID(){return this.JOB_ID;}
    public int GetJOB_PROCESS_ID(){return this.JOB_PROCESS_ID;}
    public int GetORIGIN_ACCOUNT_ID(){ return this.ORIGIN_ACCOUNT_ID;}
    public String GetCOLOR(){ return this.COLOR;}






}