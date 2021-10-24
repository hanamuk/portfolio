package com.example.testapp;

public class AllProcessData {

    private String JOB_NAME;
    private int JOB_ID;
    private String PROGRESS_STATUS;
    private String STATE_BEFORE;
    private String STATE_PRESENT;
    private String STATE_NEXT;
    private String COLOR;
    private int WAREHOUSING;
    private int JOB_PROCESS_ID;




    public void SetJOB_NAME(String job_name){this.JOB_NAME= job_name;};
    public void SetJOB_ID(int job_id){this.JOB_ID= job_id;};
    public void SetJOB_PROCESS_ID(int job_process_id){this.JOB_PROCESS_ID= job_process_id;};
    public void SetPROGRESS_STATUS(String progress_status){this.PROGRESS_STATUS=progress_status;};
    public void SetSTATE_BEFORE(String state_before){this.STATE_BEFORE=state_before;};
    public void SetSTATE_PRESENT(String  state_present){this.STATE_PRESENT= state_present;};
    public void SetSTATE_NEXT(String state_next){this.STATE_NEXT=state_next;};
    public void SetWAREHOUSING(int warehousing){this.WAREHOUSING= warehousing;};
    public void SetCOLOR(String color){this.COLOR=color;};




    public String GetJOB_NAME(){ return this.JOB_NAME;}
    public int GetJOB_ID(){return this.JOB_ID;}
    public int GetJOB_PROCESS_ID(){return this.JOB_PROCESS_ID;}
    public String GetPROGRESS_STATUS(){ return this.PROGRESS_STATUS;}
    public String GetSTATE_BEFORE(){ return this.STATE_BEFORE;}
    public String GetSTATE_PRESENT(){ return this.STATE_PRESENT;}
    public String GetSTATE_NEXT(){ return this.STATE_NEXT;}
    public int GetWAREHOUSING(){ return this.WAREHOUSING;}
    public String GetCOLOR(){ return this.COLOR;}




}