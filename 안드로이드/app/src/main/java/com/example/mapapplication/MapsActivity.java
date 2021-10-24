package com.example.mapapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//mssql 연동 방법 참고:https://www.youtube.com/watch?v=MnmEXqfV5BU
public class MapsActivity extends Activity implements OnMapReadyCallback {

    private MapView mapView;

    ArrayList <Double>XList=new ArrayList<>();
    ArrayList <Double>YList=new ArrayList<>();
    ArrayList <String>StateList=new ArrayList<>();

    //sql 관련 내용----------------------------------------------------------
    private  static  String ip="192.168.0.2";
    private  static  String port="1433";
    private  static String Classes="net.sourceforge.jtds.jdbc.Driver";
    private  static String database="AndroidDatabase"; //데이터 DB 이름  변경해서 사용
    private static String username="ojb123";
    private static String password="ojb123";
    private static String url="jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection=null;
    //-----------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//지도를 생성해주는 역활
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView=findViewById(R.id.map_view);
        mapView.getMapAsync(this);

        //sql---------------------------------------------------------------------
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection= DriverManager.getConnection(url,username,password);

            Toast.makeText(MapsActivity.this,"연결 성공",Toast.LENGTH_SHORT).show();

            //DB에 저장되 데이터를 배열에 저장 시킨다.
            InformationX();
            InformationY();
            InformationST();

        }catch (ClassNotFoundException e) {
          e.printStackTrace();;
            Toast.makeText(MapsActivity.this,"에러 발생",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(MapsActivity.this,"연결 실패",Toast.LENGTH_SHORT).show();
        }


        //----------------------------------------------------------------------------------------------------
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {//해당 좌표에  마커를 설정해준다.
        //현재 위치 초기 화면으로 표시하는 방법
        double x=36.337928;// x좌표
        double y=127.446049;//y좌표

        double xa=XList.get(0);
        double ya=YList.get(0);

        LatLng initialPosition = new LatLng(x, y); //좌표 설정
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(initialPosition);
        naverMap.moveCamera(cameraUpdate);
//        마커 표시하는 방법
        for(int i=0;i<XList.size();i++)
        {
            Marker[] markers=new Marker[XList.size()];
            markers[i]=new Marker();
            //지도에 좌표 및 옵션 설정 -------
            markers[i].setPosition(new LatLng(XList.get(i),YList.get(i)));
            markers[i].setWidth(70);
            markers[i].setHeight(70);
            markers[i].setIcon(MarkerIcons.BLACK);
            markers[i].setIconTintColor(Color.RED);//색상
            markers[i].setIconPerspectiveEnabled(true);//원근 효과
            markers[i].setCaptionText(StateList.get(i));//아이콘
            markers[i].setCaptionOffset(20);//마커와 아이콘의 간격 조절 20pixel
            markers[i].setCaptionColor(Color.BLUE); //아이콘 색상 변경
            markers[i].setCaptionHaloColor(Color.rgb(200, 255, 200));//아이콘 색상 변경
            markers[i].setCaptionTextSize(13);//아이콘 크기
            markers[i].setMap(naverMap);//지도에 표시하기
        }
    }

    public void InformationX()
    {
        if(connection!=null)
        {
            Statement statement=null;//SQL문을 데이터베이스에 보내기위한 객체입니다.
            try{
                statement=connection.createStatement();
                ResultSet resultSetX=statement.executeQuery("select X from androidDB2;");//SQL 문장을 실행

                while(resultSetX.next()){
                    XList.add(resultSetX.getDouble("X"));

                }
                Toast.makeText(MapsActivity.this,"X좌표 연결 성공",Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(MapsActivity.this,"X좌표 연결 실패",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void InformationY()
    {
        if(connection!=null)
        {
            Statement statement=null;//SQL문을 데이터베이스에 보내기위한 객체입니다.
            try{
                statement=connection.createStatement();
                ResultSet resultSetY=statement.executeQuery("select Y from androidDB2;");//SQL 문장을 실행

                while(resultSetY.next()){
                    YList.add(resultSetY.getDouble("Y"));

                }
                Toast.makeText(MapsActivity.this,"Y좌표 연결 성공",Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(MapsActivity.this,"Y좌표 연결 실패",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void InformationST()
    {
        if(connection!=null)
        {
            Statement statement=null;//SQL문을 데이터베이스에 보내기위한 객체입니다.
            try{
                statement=connection.createStatement();
                ResultSet resultSetST=statement.executeQuery("select State from androidDB2;");//SQL 문장을 실행

                while(resultSetST.next()){
                    StateList.add(resultSetST.getString("State"));

                }
                Toast.makeText(MapsActivity.this,"State 연결 성공",Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(MapsActivity.this,"State 연결 실패",Toast.LENGTH_SHORT).show();
            }
        }
    }

}