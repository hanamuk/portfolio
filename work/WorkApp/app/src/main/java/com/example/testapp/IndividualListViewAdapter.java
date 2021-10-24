package com.example.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IndividualListViewAdapter extends RecyclerView.Adapter<IndividualListViewAdapter.ViewHolder> {

    Context context;
    int row_index = -1;

    // 아이템 데이터 리스트.
    private ArrayList<WorkListViewItem> IndividualDataList = null;

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
        TextView workstatus;
        ImageView workstatus_img;
        String color;
        LayerDrawable drawable;
        GradientDrawable shape;
        int joborderid;
        int joborder_processid;
        int accountid;
        int focuson;

        //얘가 홀던데 여기서 데이터들을 가꼬잇음
        ViewHolder(View itemView) {
            super(itemView);
            //여기서 xml이랑 아이디 연결하는거고
            //여기서 xml이랑 아이디 연결하는거고
            layout = (LinearLayout)itemView.findViewById(R.id.iv_layout);
            num = (TextView)itemView.findViewById(R.id.num_txt);
            jobname = (TextView)itemView.findViewById(R.id.jobname_txt);
            status = (TextView)itemView.findViewById(R.id.status_txt);
            before = (TextView)itemView.findViewById(R.id.before_txt);
            present = (TextView)itemView.findViewById(R.id.present_txt);
            next = (TextView)itemView.findViewById(R.id.next_txt);
            workstatus = (TextView)itemView.findViewById(R.id.workstatus_txt);
            woker_name = (TextView)itemView.findViewById(R.id.workman_txt);
            workstatus_img = (ImageView)itemView.findViewById(R.id.workstatus_img);
            color = "#ffffff";
            drawable = (LayerDrawable) context.getResources().getDrawable(R.drawable.xml_border);
            shape = (GradientDrawable) (drawable.findDrawableByLayerId(R.id.selectable_shape));

        }
    }

    // 무조건 잇어야함
    IndividualListViewAdapter(ArrayList<WorkListViewItem> list) { IndividualDataList = list; }

    // @오버라이드 붙은건 다잇어야함 아니면 에러남. 얘는 인플레이터 시켜서 뷰홀더를 만들고 리턴시켜주는애
    @Override
    public IndividualListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflater.inflate(R.layout.individual_item, parent, false);
        IndividualListViewAdapter.ViewHolder vh = new IndividualListViewAdapter.ViewHolder(view);

        return vh;
    }

    // 얘도 잇어야함 온바인드뷰홀더 여기서 값을 직접 너어주는겨
    @Override
    public void onBindViewHolder(IndividualListViewAdapter.ViewHolder holder, int position) {
        //얘가 데이터 세팅해주는애임
        WorkListViewItem item = IndividualDataList.get(position);

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
        holder.focuson = item.getFocus_on();


        switch(item.getFocus_on())
        {
            case 0:
            case 1:
                holder.workstatus.setVisibility(View.GONE);
                holder.workstatus_img.setImageResource(R.drawable.individual_work_status_btn_selector);
                holder.workstatus_img.setVisibility(View.VISIBLE);
                break;
            case 2: //작업중
                holder.workstatus.setText("작업중");
                holder.workstatus.setVisibility(View.VISIBLE);
                holder.workstatus_img.setVisibility(View.GONE);
                break;
            case 3: //작업완료
                holder.workstatus.setText("작업완료");
                holder.workstatus.setVisibility(View.VISIBLE);
                holder.workstatus_img.setVisibility(View.GONE);
                break;

        }

        //할당취소버튼클릭
        holder.workstatus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://220.89.167.212:8085/testing05/UpdateAssignCancel";
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

                int isSuccess = jsonParser.UpdateJSONParse(result, 4);

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
                   /* Intent intent = new Intent(context, AllocateFailPopup.class);
                    context.startActivity(intent);*/
                    //페이지 샐오고침
                    Intent refresh = new Intent(context, WorkProcessActivity.class);
                    context.startActivity(refresh);
                    ((Activity)context).finish();
                }
            }
        });





        //레이아웃 클릭시 하이라이트
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://122.43.130.70:8061/temp/worker/worker/worker_joborder_view.html?key=" + holder.joborderid));
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        //여기 수정하면 눌렸을때 그 눌린 레이아웃 디자인이 바뀜. 지금은 stroke 선샊깔만 바꾸고잇음
        if(row_index==position){
            holder.shape.setStroke ( 2, Color.parseColor("#FB0000"));
            //holder.shape.setColor(Color.parseColor(holder.color));
            holder.layout.setBackground(holder.drawable);
        }

        //여긴 눌린애들 제외 나머지 레이아웃 디자인
        else
        {
            holder.shape.setStroke (1 , Color.parseColor("#a3b0bf"));
            //holder.shape.setColor(Color.parseColor(holder.color));
            holder.layout.setBackground(holder.drawable);
        }



    }

    // 얘네 다 잇어야함
    @Override
    public int getItemViewType(int position) {
        return IndividualDataList.get(position).getType() ;
    }

    @Override
    public int getItemCount() {
        return IndividualDataList.size();
    }

}
