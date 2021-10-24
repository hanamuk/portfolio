package com.example.testapp;

import android.content.SharedPreferences;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Filter {

    public static int main_warehousing_count = 0;
    public static int btb_all_count = 0;
    public static int btb_haldang_count = 0;
    public static int indi_daegie_count = 0;
    public static int indi_jinhanging_count = 0;
    public static int indi_wanryo_count = 0;


    public static ArrayList<AllProcessData> MainFilter(ArrayList<ArrayList<ProcessJSONData>> jsonlist) {
        ArrayList<AllProcessData> allProcessDataArrayList = new ArrayList<>();
        AllProcessData processData = new AllProcessData();
        ArrayList<ProcessJSONData> jsonData;
        ProcessJSONData json;
        int status = 0;


        for (int i = 0; i < jsonlist.size(); i++) {
            //한 작지 묶음
            jsonData = jsonlist.get(i);
            int tmp = 0;
            for (int j = 0; j < jsonData.size(); j++) {
                //세부
                json = jsonData.get(j);


                //1. 이름
                processData.SetJOB_NAME(json.GetJOBORDER_DETAIL_JOBNUMBER());
                processData.SetJOB_ID(json.GetJOBORDER_ID());

                //2. 진행상태
                if (json.GetJOBORDER_PROCESS_FOCUSON() == 1 || json.GetJOBORDER_PROCESS_FOCUSON() == 2) {
                    //현재
                    processData.SetSTATE_PRESENT(json.GetJOBORDER_PROCESS_NAME());

                    //이전
                    if (j - 1 < 0)
                        processData.SetSTATE_BEFORE("None");
                    else
                        processData.SetSTATE_BEFORE(jsonData.get(j - 1).GetJOBORDER_PROCESS_NAME());

                    //다음
                    if (j + 1 > jsonData.size())
                        processData.SetSTATE_NEXT("None");
                    else
                        processData.SetSTATE_NEXT(jsonData.get(j + 1).GetJOBORDER_PROCESS_NAME());
                }

                //완료
                else if (json.GetJOBORDER_PROCESS_FOCUSON() == 3) {

                    status += 1;
                }

                //3. 입고
                //사용자가 설정한거랑 같은거일때

                if (tmp == 0) {
                    if (json.GetJOBORDER_PROCESS_FOCUSON() == 1 || json.GetJOBORDER_PROCESS_FOCUSON() == 2) {
                        if (json.GetPROCESS_MAIN_CATEGORY_ID() == MainActivity.processid) {
                            tmp = 1;
                            processData.SetWAREHOUSING(json.GetJOBORDER_LOBBY_ACTIVITY_ID1());
                            processData.SetJOB_PROCESS_ID(json.GetJOBORDER_PROCESS_ID());

                            if (json.GetJOBORDER_LOBBY_ACTIVITY_ID1() == 1) {
                                main_warehousing_count += 1;

                            }

                        }
                    } else {
                        processData.SetWAREHOUSING(2);
                    }
                }


                //4. 긴급 우선 여부 (색상)
                switch (json.GetJOBORDER_EMERGENCY_ID()) {
                    case 0:
                        processData.SetCOLOR("#ffffff");
                        break;
                    case 1:
                        processData.SetCOLOR("#fff8e5");
                        break;
                    case 2:
                        processData.SetCOLOR("#fde5e5");
                        break;

                }
                processData.SetPROGRESS_STATUS(Integer.toString(status) + "/" + Integer.toString(jsonData.size()));
            }
            //전부다완료일경우 추가 안함.
            if (status != jsonData.size()) {
                allProcessDataArrayList.add(processData);
                processData = new AllProcessData();
            }
        }


        return allProcessDataArrayList;
    }


    public static ArrayList<BtoBData> WorkProcessFilter(ArrayList<ArrayList<ProcessJSONData>> jsonlist) {
        ArrayList<BtoBData> allBtoBDataArrayList = new ArrayList<>();
        BtoBData processData = new BtoBData();
        ArrayList<ProcessJSONData> jsonData;
        ProcessJSONData json;
        int status = 0;

        for (int i = 0; i < jsonlist.size(); i++) {
            //한 작지 묶음
            jsonData = jsonlist.get(i);
            int tmp = 0;
            for (int j = 0; j < jsonData.size(); j++) {
                //세부
                json = jsonData.get(j);

                if (json.GetJOBORDER_PROCESS_FOCUSON() != 3) {
                    if (json.GetPROCESS_MAIN_CATEGORY_ID() == MainActivity.processid) {
                        if (json.GetJOBORDER_LOBBY_ACTIVITY_ID1() == 2 || json.GetJOBORDER_LOBBY_ACTIVITY_ID1() == 3) {
                            //1. 이름
                            processData.SetJOB_NAME(json.GetJOBORDER_DETAIL_JOBNUMBER());
                            processData.SetJOB_ID(json.GetJOBORDER_ID());
                            processData.SetJOB_PROCESS_ID(json.GetJOBORDER_PROCESS_ID());

                            //현재
                            processData.SetSTATE_PRESENT(json.GetJOBORDER_PROCESS_NAME());

                            //이전
                            if (j - 1 < 0)
                                processData.SetSTATE_BEFORE("None");
                            else
                                processData.SetSTATE_BEFORE(jsonData.get(j - 1).GetJOBORDER_PROCESS_NAME());

                            //다음
                            if (j + 1 > jsonData.size())
                                processData.SetSTATE_NEXT("None");
                            else
                                processData.SetSTATE_NEXT(jsonData.get(j + 1).GetJOBORDER_PROCESS_NAME());

                            processData.SetWORKER_NAME(json.GetORIGIN_ACCOUNT_MEMBERNAME());
                            processData.SetORIGIN_ACCOUNT_ID(json.GetORIGIN_ACCOUNT_ID());

                            //할당 가능할때
                            if (json.GetJOBORDER_LOBBY_ACTIVITY_ID2() == 4) {
                                btb_haldang_count += 1;
                                processData.SetASSIGNMENT(json.GetJOBORDER_LOBBY_ACTIVITY_ID2());
                                processData.SetWAREHOSEING(json.GetJOBORDER_LOBBY_ACTIVITY_ID2());
                            } else {
                                processData.SetASSIGNMENT(5);
                                processData.SetWAREHOSEING(5);
                            }

                            //4. 긴급 우선 여부 (색상)
                            switch (json.GetJOBORDER_EMERGENCY_ID()) {
                                case 0:
                                    processData.SetCOLOR("#ffffff");
                                    break;
                                case 1:
                                    processData.SetCOLOR("#fff8e5");
                                    break;
                                case 2:
                                    processData.SetCOLOR("#fde5e5");
                                    break;

                            }
                            if (json.GetJOBORDER_PROCESS_FOCUSON() == 0)
                            {
                                processData.SetPROGRESS_STATUS("대기");
                            }
                            else if (json.GetJOBORDER_PROCESS_FOCUSON() == 1) {
                                processData.SetPROGRESS_STATUS("해야될거");
                            }
                            else if (json.GetJOBORDER_PROCESS_FOCUSON() == 2) {
                                processData.SetPROGRESS_STATUS("작업중");
                            }

                            allBtoBDataArrayList.add(processData);

                        }
                    }
                }

                if (j >= jsonData.size() - 1) {



                    processData = new BtoBData();
                }

            }

        }

        btb_all_count = allBtoBDataArrayList.size();
        return allBtoBDataArrayList;
    }

    public static ArrayList<BtoBData> IndividualFilter(ArrayList<ArrayList<ProcessJSONData>> jsonlist) {
        ArrayList<BtoBData> allBtoBDataArrayList = new ArrayList<>();
        BtoBData processData = new BtoBData();
        ArrayList<ProcessJSONData> jsonData;
        ProcessJSONData json;
        int status = 0;

        for (int i = 0; i < jsonlist.size(); i++) {
            //한 작지 묶음
            jsonData = jsonlist.get(i);
            int tmp = 0;
            for (int j = 0; j < jsonData.size(); j++) {
                //세부
                json = jsonData.get(j);

                if (json.GetORIGIN_ACCOUNT_MEMBERNAME().equals(LoginActivity.MemberName))
                {

                    if (json.GetJOBORDER_PROCESS_FOCUSON() == 0 || json.GetJOBORDER_PROCESS_FOCUSON() == 1) {
                        indi_daegie_count += 1;
                    }
                    else if (json.GetJOBORDER_PROCESS_FOCUSON() == 2) {
                        indi_jinhanging_count += 1;
                    }
                    else if (json.GetJOBORDER_PROCESS_FOCUSON() == 3) {
                        indi_wanryo_count += 1;
                        status += 1;
                    }

                    //1. 이름
                    processData.SetJOB_NAME(json.GetJOBORDER_DETAIL_JOBNUMBER());
                    processData.SetJOB_ID(json.GetJOBORDER_ID());
                    processData.SetJOB_PROCESS_ID(json.GetJOBORDER_PROCESS_ID());

                    //현재
                    processData.SetSTATE_PRESENT(json.GetJOBORDER_PROCESS_NAME());

                    //이전
                    if (j - 1 < 0)
                        processData.SetSTATE_BEFORE("None");
                    else
                        processData.SetSTATE_BEFORE(jsonData.get(j - 1).GetJOBORDER_PROCESS_NAME());

                    //다음
                    if (j + 1 > jsonData.size())
                        processData.SetSTATE_NEXT("None");
                    else
                        processData.SetSTATE_NEXT(jsonData.get(j + 1).GetJOBORDER_PROCESS_NAME());

                    processData.SetWORKER_NAME(json.GetORIGIN_ACCOUNT_MEMBERNAME());
                    processData.SetORIGIN_ACCOUNT_ID(json.GetORIGIN_ACCOUNT_ID());

                    //할당 가능할때
                    if (json.GetJOBORDER_LOBBY_ACTIVITY_ID2() == 4) {
                        //btb_haldang_count += 1;
                        processData.SetASSIGNMENT(json.GetJOBORDER_LOBBY_ACTIVITY_ID2());
                        processData.SetWAREHOSEING(json.GetJOBORDER_LOBBY_ACTIVITY_ID2());
                    } else {
                        processData.SetASSIGNMENT(5);
                        processData.SetWAREHOSEING(5);
                    }

                    //4. 긴급 우선 여부 (색상)
                    switch (json.GetJOBORDER_EMERGENCY_ID()) {
                        case 0:
                            processData.SetCOLOR("#ffffff");
                            break;
                        case 1:
                            processData.SetCOLOR("#fff8e5");
                            break;
                        case 2:
                            processData.SetCOLOR("#fde5e5");
                            break;

                    }
                    processData.SetFOCUS_ON(json.GetJOBORDER_PROCESS_FOCUSON());
                    allBtoBDataArrayList.add(processData);
                    processData.SetPROGRESS_STATUS(Integer.toString(status) + "/" + Integer.toString(jsonData.size()));
                    processData = new BtoBData();
                }

            }

        }

        //btb_all_count = allBtoBDataArrayList.size();
        return allBtoBDataArrayList;
    }
}
