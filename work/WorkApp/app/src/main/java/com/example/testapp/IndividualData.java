package com.example.testapp;

public class IndividualData {

    private String JOB_NAME;
    private String PROGRESS_STATUS;
    private String STATE_BEFORE;
    private String STATE_PRESENT;
    private String STATE_NEXT;
    private String WORKER_NAME;
    //작업상태
    private int WORK_STATUS;

    public void SetJOB_NAME(String job_name){this.JOB_NAME= job_name;};
    public void SetPROGRESS_STATUS(String progress_status){this.PROGRESS_STATUS=progress_status;};
    public void SetSTATE_BEFORE(String state_before){this.STATE_BEFORE=state_before;};
    public void SetSTATE_PRESENT(String  state_present){this.STATE_PRESENT= state_present;};
    public void SetSTATE_NEXT(String state_next){this.STATE_NEXT=state_next;};
    public void SetWORKER_NAME(String wname){this.WORKER_NAME= wname;};
    public void SetWORK_STATUS(int work_status){this.WORK_STATUS= work_status;};


    public String GetJOB_NAME(){ return this.JOB_NAME;}
    public String GetPROGRESS_STATUS(){ return this.PROGRESS_STATUS;}
    public String GetSTATE_BEFORE(){ return this.STATE_BEFORE;}
    public String GetSTATE_PRESENT(){ return this.STATE_PRESENT;}
    public String GetSTATE_NEXT(){ return this.STATE_NEXT;}
    public String GetWORKER_NAME(){ return this.WORKER_NAME;}
    public int GetWORK_STATUS(){ return this.WORK_STATUS;}


}
