package com.example.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkLIstViewAdapter extends RecyclerView.Adapter<WorkLIstViewAdapter.ViewHolder> {

    Context context;
    int row_index = -1;

    // 아이템 데이터 리스트.
    private ArrayList<WorkListViewItem> worklistViewItemList = null ;


    public class ViewHolder extends RecyclerView.ViewHolder {

        //변수 선언 여기서 하시면 대구요 아 물론 새파일 만드셔야 ㅎㅎ;
        LinearLayout layout;
        TextView num;
        TextView jobname;
        TextView status;
        TextView before;
        TextView present;
        TextView next;
        TextView woker_name;
        ImageView assigment;
        ImageView warehouesing;
        String color;
        LayerDrawable drawable;
        GradientDrawable shape;
        Spinner workerSpinner;
        int joborderid;
        int joborder_processid;
        int accountid;


        //얘가 홀던데 여기서 데이터들을 가꼬잇음
        ViewHolder(View itemView) {
            super(itemView) ;
            //여기서 xml이랑 아이디 연결하는거고
            layout = (LinearLayout)itemView.findViewById(R.id.ps_layout);
            num = (TextView)itemView.findViewById(R.id.num_txt);
            jobname = (TextView)itemView.findViewById(R.id.jobname_txt);
            status = (TextView)itemView.findViewById(R.id.status_txt);
            before = (TextView)itemView.findViewById(R.id.before_txt);
            present = (TextView)itemView.findViewById(R.id.present_txt);
            next = (TextView)itemView.findViewById(R.id.next_txt);
            woker_name = (TextView)itemView.findViewById(R.id.workman_txt);
            assigment = (ImageView)itemView.findViewById(R.id.assignment_img);
            color = "#ffffff";
            warehouesing = (ImageView)itemView.findViewById(R.id.warehouse_img);
            drawable = (LayerDrawable) context.getResources().getDrawable(R.drawable.xml_border);
            shape = (GradientDrawable) (drawable.findDrawableByLayerId(R.id.selectable_shape));
            workerSpinner = (Spinner)itemView.findViewById(R.id.workman_spinner);


        }
    }

    // 무조건 잇어야함
    WorkLIstViewAdapter(ArrayList<WorkListViewItem> list) {
        worklistViewItemList = list ;
    }

    // @오버라이드 붙은건 다잇어야함 아니면 에러남. 얘는 인플레이터 시켜서 뷰홀더를 만들고 리턴시켜주는애
    @Override
    public WorkLIstViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;


        View view = inflater.inflate(R.layout.process_item, parent, false);
        WorkLIstViewAdapter.ViewHolder vh = new WorkLIstViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    // 얘도 잇어야함 온바인드뷰홀더 여기서 값을 직접 너어주는겨
    @Override
    public void onBindViewHolder(WorkLIstViewAdapter.ViewHolder holder, int position) {
        //얘가 데이터 세팅해주는애임
        WorkListViewItem item = worklistViewItemList.get(position);

        holder.num.setText(String.valueOf(item.getNum()));
        holder.jobname.setText(item.getJobname());
        holder.status.setText(item.getStatus());
        holder.before.setText(item.getBefore());
        holder.present.setText(item.getPresent());
        holder.next.setText(item.getNext());
        holder.woker_name.setText(item.getWorker());
        holder.color = item.getBgcolor();
        holder.shape.setStroke (1 , Color.parseColor("#a3b0bf"));
        holder.shape.setColor(Color.parseColor(holder.color));
        holder.layout.setBackground(holder.drawable);
        holder.joborderid = item.getJoborderid();
        holder.joborder_processid = item.getJoborder_process_id();
        holder.accountid = item.getOrigin_account_id();
        //holder.workerSpinner = item.getWorkerlist();

        if(LoginActivity.division == 3)
        {
            holder.workerSpinner.setVisibility(View.VISIBLE);
            holder.woker_name.setVisibility(View.GONE);
        }
        else
        {
            holder.workerSpinner.setVisibility(View.GONE);
            holder.woker_name.setVisibility(View.VISIBLE);
        }


        switch(item.getAssigment())
        {
            case 4:
                holder.assigment.setImageResource(R.drawable.btbdownload_btn_selector);
                break;
            case 5:
                holder.assigment.setImageResource(R.drawable.btn_download_sort_inact);
                break;


        }

        //그 입고 타입에 따라서 작업중 글씨 혹은 눌린버튼 안눌린버튼 해주는 스위치케이스
       switch(item.getWarehousing())
        {
            case 4:
                holder.warehouesing.setImageResource(R.drawable.cancel_btn_selector);
                break;
            case 5:
                holder.warehouesing.setImageResource(R.drawable.btn_cancel_inact);
                break;


        }


//할당버튼클릭
        holder.assigment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://220.89.167.212:8085/testing05/UpdateAssignOn1";
                MakeJson Mjson = new MakeJson();

                String result=null;

                JSONParser jsonParser = new JSONParser();

                String json = Mjson.UpdateAssignOn(holder.joborderid, holder.joborder_processid, MainActivity.processid, LoginActivity.accountid);
                NetworkTask networkTask = new NetworkTask(url, null,json);
                try
                {
                    result = networkTask.execute().get();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                int isSuccess = jsonParser.UpdateJSONParse(result, 2);

                //성공
                if(isSuccess == 1)
                {
                    //페이지 샐오고침
                    Intent refresh = new Intent(context, WorkProcessActivity.class);
                    context.startActivity(refresh);
                    ((Activity)context).finish();
                }
                //실패
                else if(isSuccess == 0)
                {
                    //TODO: 이미 다른 작업자가 할당했다는 짠짠 팝업
                    Intent intent = new Intent(context, AllocateFailPopup.class);
                    context.startActivity(intent);


                    //페이지 샐오고침
                    Intent refresh = new Intent(context, WorkProcessActivity.class);
                    context.startActivity(refresh);
                    ((Activity)context).finish();
                }
            }
        });

//입고취소버튼클릭
        holder.warehouesing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://220.89.167.212:8085/testing05/UpdateReceiveCancel";
                MakeJson Mjson = new MakeJson();
                String result=null;

                JSONParser jsonParser = new JSONParser();

                String json = Mjson.UpdateReceiveOn(holder.joborderid, holder.joborder_processid, MainActivity.processid);
                NetworkTask networkTask = new NetworkTask(url, null,json);
                try
                {
                    result = networkTask.execute().get();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                int isSuccess = jsonParser.UpdateJSONParse(result, 3);

                //성공
                if(isSuccess == 1)
                {
                    //페이지 샐오고침
                    Intent refresh = new Intent(context, WorkProcessActivity.class);
                    context.startActivity(refresh);
                    ((Activity)context).finish();
                }
                //실패
                else if(isSuccess == 0)
                {
                    //TODO: 이미 다른 작업자가 할당했다는 짠짠 팝업
                    //TODO: 이미 다른 작업자가 할당했다는 짠짠 팝업
                    Intent intent = new Intent(context, WarehouseCancleFailPopup.class);
                    context.startActivity(intent);
                    //페이지 샐오고침
                    Intent refresh = new Intent(context, WorkProcessActivity.class);
                    context.startActivity(refresh);
                    ((Activity)context).finish();
                }
            }
        });


    }

    // 얘네 다 잇어야함
    @Override
    public int getItemViewType(int position) {
        return worklistViewItemList.get(position).getType() ;
    }

    @Override
    public int getItemCount() {
        return worklistViewItemList.size();
    }



}
