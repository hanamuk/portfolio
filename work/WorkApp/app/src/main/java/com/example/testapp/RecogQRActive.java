package com.example.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class RecogQRActive  extends AppCompatActivity {

    private IntentIntegrator qrScan;
    public static int qr_joborderid =  -1;

    WebView wv;
    EditText et;
    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogqr);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.initiateScan();

        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);
        bt = findViewById(R.id.bt);
        WebSettings webSettings = wv.getSettings();
        //자바 스크립트 사용을 할 수 있도록 합니다.
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //Toast.makeText(RecogQRActive.this, "로딩 끝", Toast.LENGTH_SHORT).show();
            }
        });

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //bt의 onClick을 실행
                    bt.callOnClick();
                    //키보드 숨기기
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        new IntentIntegrator(this).initiateScan();
    }

    public void onClick(View view) {
        String address = et.getText().toString();
        if (!address.startsWith("http://")) {
            address = "http://" + address;
        }
        wv.loadUrl(address);
    }

    @Override
    public void onBackPressed() {
        if (wv.isActivated()) {
            if (wv.canGoBack()) {
                wv.goBack();
            } else {
                qrScan.initiateScan();
            }
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                //qrcode 가 없으면
                if (result.getContents() == null) {
                    Toast.makeText(RecogQRActive.this, "취소!", Toast.LENGTH_SHORT).show();
                } else {
                    //qrcode 결과가 있으면
                    Toast.makeText(RecogQRActive.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                    try {
                        //data를 json으로 변환
                        JSONObject obj = new JSONObject(result.getContents());
                        qr_joborderid = obj.getInt("JOBORDER_ID");
                        //페이지 샐오고침
                        Intent refresh = new Intent(MainActivity.main_context, MainActivity.class);
                        MainActivity.main_context.startActivity(refresh);
                        ((Activity)MainActivity.main_context).finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}