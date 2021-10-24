package com.example.testapp;

public class LoginData {

    private int login_fail;
    private int pwd_find_fail;
    private int Id_Credit;
    private int overlap;

    private int WORKER_PROPERTY_ID;
    private int ORIGIN_EQUIPMENT_ID;
    private int ORIGIN_PROCESS_ID;

    private int ORIGIN_ACCOUNT_ID;
    private int ORIGIN_ACCOUNT_DIVISION_ID;
    private String ORIGIN_ACCOUNT_MEMBERNAME;
    private String ORIGIN_ACCOUNT_PASSWORD;
    private String ORIGIN_ACCOUNT_TYPE;
    private String ORIGIN_ACCOUNT_EMPLOYEENUMBER;
    private String ORIGIN_ACCOUNT_DEPARTMENT;
    private String ORIGIN_ACCOUNT_TIER;
    private String ORIGIN_ACCOUNT_RESIDENTNUMBER;
    private String ORIGIN_ACCOUNT_CREATEDATE;
    private int ORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID;

    public void Setlogin_fail(int Login_fail){this.login_fail=Login_fail;};
    public void SetPwd_find_fail(int Pwd_find_fail){this.pwd_find_fail=Pwd_find_fail;};
    public void SetId_Credit(int id_Credit){this.Id_Credit=id_Credit;};
    public void SetOverlap(int Overlap){this.overlap=Overlap;};

    public void SetWORKER_PROPERTY_ID(int Woker_pid){this.WORKER_PROPERTY_ID=Woker_pid;};
    public void SetORIGIN_EQUIPMENT_ID(int Equ_id){this.ORIGIN_EQUIPMENT_ID=Equ_id;};
    public void SetORIGIN_PROCESS_ID(int Precess_id){this.ORIGIN_PROCESS_ID=Precess_id;};


    public void SetORIGIN_ACCOUNT_ID(int id){this.ORIGIN_ACCOUNT_ID = id;};
    public void SetORIGIN_ACCOUNT_DIVISION_ID(int div_id){this.ORIGIN_ACCOUNT_DIVISION_ID = div_id;};
    public void SetORIGIN_ACCOUNT_MEMBERNAME(String m_name){this.ORIGIN_ACCOUNT_MEMBERNAME =m_name;};
    public void SetORIGIN_ACCOUNT_PASSWORD(String pw){this.ORIGIN_ACCOUNT_PASSWORD = pw;};
    public void SetORIGIN_ACCOUNT_TYPE(String type){this.ORIGIN_ACCOUNT_TYPE= type;};
    public void SetORIGIN_ACCOUNT_EMPLOYEENUMBER(String emp){this.ORIGIN_ACCOUNT_EMPLOYEENUMBER = emp;};
    public void SetORIGIN_ACCOUNT_DEPARTMENT(String dept){this.ORIGIN_ACCOUNT_DEPARTMENT = dept;};
    public void SetORIGIN_ACCOUNT_TIER(String tier){this.ORIGIN_ACCOUNT_TIER = tier;};
    public void SetORIGIN_ACCOUNT_RESIDENTNUMBER(String resnum ){this.ORIGIN_ACCOUNT_RESIDENTNUMBER = resnum;};
    public void SetORIGIN_ACCOUNT_CREATEDATE(String createDate){this.ORIGIN_ACCOUNT_CREATEDATE= createDate;};
    public void SetORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID(int empStatusId){this.ORIGIN_ACCOUNT_EMPLOYMENT_STATUS_ID = empStatusId;};

    public int Getlogin_fail(){ return this.login_fail;}
    public int GetORIGIN_ACCOUNT_DIVISION_ID(){ return this.ORIGIN_ACCOUNT_DIVISION_ID;}
    public int GetORIGIN_ACCOUNT_ID(){ return this.ORIGIN_ACCOUNT_ID;}
    public String GetMember_name(){ return this.ORIGIN_ACCOUNT_MEMBERNAME;}


}
