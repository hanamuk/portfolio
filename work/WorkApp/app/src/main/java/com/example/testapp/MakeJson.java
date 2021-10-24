package com.example.testapp;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeJson {

    public String LoginJson(String Id,String Pw) {

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.accumulate("ORIGIN_ACCOUNT_EMPLOYEENUMBER", Id);

            jsonObject.accumulate("ORIGIN_ACCOUNT_PASSWORD", Pw);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return jsonObject.toString();
    }

    public String FindPasswordJson(String empnum,String socialnum) {

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.accumulate("ORIGIN_ACCOUNT_RESIDENTNUMBER", socialnum);

            jsonObject.accumulate("ORIGIN_ACCOUNT_EMPLOYEENUMBER", empnum);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return jsonObject.toString();
    }

    //입고 취소, 입고 클릭, 할당 취소 똑같
    public String UpdateReceiveOn(int joborder_id, int joborder_process_id, int process_category_id) {

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.accumulate("JOBORDER_ID", joborder_id);

            jsonObject.accumulate("JOBORDER_PROCESS_ID", joborder_process_id);

            jsonObject.accumulate("PROCESS_MAIN_CATEGORY_ID", process_category_id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return jsonObject.toString();
    }


    public String UpdateAssignOn(int joborder_id, int joborder_process_id, int process_category_id, int origin_account_id) {

        JSONObject jsonObject = new JSONObject();
        try{

            jsonObject.accumulate("JOBORDER_ID", joborder_id);

            jsonObject.accumulate("JOBORDER_PROCESS_ID", joborder_process_id);

            jsonObject.accumulate("PROCESS_MAIN_CATEGORY_ID", process_category_id);

            jsonObject.accumulate("ORIGIN_ACCOUNT_ID", origin_account_id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        return jsonObject.toString();
    }
}
