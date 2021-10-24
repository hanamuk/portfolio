package com.example.testapp;
import android.content.ContentValues;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class RequestHttpURLConnection {

    public String request(String _url, ContentValues _params, String json) {
        HttpURLConnection urlConn = null;
        StringBuffer sbParams = new StringBuffer();
        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();
            // [2-1]. urlConn 설정.
            urlConn.setConnectTimeout(15000);
            urlConn.setReadTimeout(5000);
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 :
            urlConn.addRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Accept", "application/json");

            // urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
            //urlConn.setRequestProperty("Context_Type", "application/json");
            sbParams.append(json);


            String strParams = sbParams.toString(); //sbParams에 정리한 파라미터들을 스트링으로 저장. 예)id=id1&pw=123;


            OutputStream os = urlConn.getOutputStream();
            os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력.
            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제. // [2-3]. 연결 요청 확인. // 실패 시 null을 리턴하고 메서드를 종료.

            int s = urlConn.getResponseCode();
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null; // [2-4]. 읽어온 결과물 리턴. // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8")); // 출력물의 라인과 그 합에 대한 변수.
            String line;
            String page = ""; // 라인을 받아와 합친다.
            while ((line = reader.readLine()) != null) {
                page += line;

            }
            return page;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;

    }


    public String get(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정

            con.setRequestMethod("GET");


//URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. URL 연결은 입출력에 사용될 수 있다. URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.

            con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Stream을 처리해줘야 하는 귀찮음이 있음.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                String page = ""; // 라인을 받아와 합친다.
                while ((line = br.readLine()) != null) {
                    page += line;
                }
                br.close();
                return page;
            } else {
                System.out.println(con.getResponseMessage());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }
}