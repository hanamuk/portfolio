
# 1. Yolo v4 을 활용한 도로 노후화 자동 감지

## 개발 소개

### 목적
: 도로에 발생하는 포트홀과 같은 현상들은 지자체마다 다르게 해야하는데, 도로의 범위가 매우 넓어 인력이 부족할 뿐더러
검사를 하는 장비에 가격이 높기때문에 장비가 부족합니다. 또한 통합 관리프로그램의 부재로 문제 해결에 어려움이 있습니다.
따라서 위와 같은 문제에 필요할 것으로 판단되어 개발을 진행하였습니다.

### 주요 기능
1. 포트 홀 인식, DB연동을 통한 포트 홀 정보 제공
2. 발생 좌표 및 처리상태 관리
3. 지도를 통한 정보 확인

 * 포트홀의 정제된 데이터의 부족으로 포트홀 이미지를 확보한 후 라벨링을 진행하여 데이터 셋을 구성하였습니다.  
 해당 데이터를 yolo-darknet을 통해 학습을 진행하였고 weight파일 생성하고, 카메라를 통해 촬영된 영상을 OpenCV와 Yolo를 통해 
 인식,처리 후 이미지 및 좌표 정보를 소켓통신을 통해 서버로 전달 하였고 서버는 데이터베이스에 정보를 저장합니다.   
 사용자는 Winform과 android app 프로그램 형태로 데이터들에 대한 접근이 가능하도록 개발을 진행하였습니다.  


## 처리흐름

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138589936-e8f8ff3e-d1aa-47d3-a92d-1af2b6f1528a.JPG" width="60%" height="100%"></p>

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138584267-95b84575-c46b-4ae5-9f25-15b3e3adab28.png" width="60%" height="100%"></p>




## 화면

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138584288-0c366a5a-e683-4dbb-baf7-e2f6d29d2557.png" width="60%" height="100%"></p>
<h4 align="center"> [포트홀 감지 및 데이터 전송]</h4>

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586582-1b0d7aeb-fd4b-4f59-8c61-f306ebbff6f7.jpg" width="60%" height="100%"></p>
<h4 align="center"> [사용자 Windows form]</h4>





## 💻 사용 기술

### 영상 처리 및 포트홀 검출
- Yolo v4
- Open CV
- Python

### Server
- C#
- Python
- Socket 통신

### 사용자 화면
- Windows Form
- Android(JAVA)
- Naver Maps API
- Google MAP API

### 데이터베이스
- SQL Server

### 기타
- 검출영상 촬영 및 GPS장치 (Raspberry Pi4, ABKO APC930 QHD 웹캠, USB-Port-GPS Module SKU:EZ-0048 )  
  
***
  
# 2. 공장 공정관리 애플리케이션(작업자 시스템 부문)  


## 개발 소개

### 목적
: 공장에서 사용되는 공정에 대한 지시서는 지금까지 아날로그방법으로 전달되어 왔습니다. 따라서 업데이트를 하기에 힘들 뿐더러 반영되는
시간 또한 많이 필요하였고 때문에 관리자와 작업자 간에 의사소통이 어려운 점이 있었습니다. 이번 프로젝트는 관리자가 배포하는 작업지시서를
작업자는 태블릿을 통해 제공받고 자신의 파트에 맞게 해당 공정을 할당 할 수 있도록 하는 프로그램 개발(작업자 부문)을 진행하였습니다.

### 
1. json 형태로 전달되어지는 데이터를 작업자가 확인 할 수 있는 리스트 형태로 출력한다.
2. 서버와의 통신은 REST방식을 사용하여 이루어지도록 한다.
3. 작업자가 입고하여 작업 중인 공정에 대해서는 서버에서 다른 작업자가 입고하지 못하도록 한다.
4. 작업자는 화면을 통해 작업지시서의 세부내용을 확인 할 수 있도록한다





## 처리흐름

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586121-67d0a931-a843-42c7-8c01-a6b91980fd62.JPG" width="80%" height="100%"></p>


## 화면

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586250-ae317011-e194-450d-8bbb-24e2395a27de.JPG" width="60%" height="%"></p>
<h4 align="center"> [전체 작업공정 리스트 출력]</h4>


<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586263-5376d000-9f10-4900-a72f-d52d16b7baae.JPG" width="60%" height="70%"></p>
<h4 align="center"> [작업공정 할당]</h4>


<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586267-e61fcbc7-c183-4938-9be3-4a169faf2017.JPG" width="60%" height="70%"></p>
<h4 align="center"> [개인 할당작업 및 작업지시서]</h4>



## 💻 사용 기술

- JAVA
- Json
- REST API
- AVD (Galaxy Tab A 7.0 ) 

***   
   
   

## 3. 채팅서버 및 클라이언트 제작

### 설명
: SOCKET통신을 사용하여 서버에 접속 및 클라이언트 간의 데이터 전송이 가능하고, Thread를 사용하여 다중 클라이언트 접속이 가능하도록 개발한 간단한 Chating 프로그램입니다.
MFC를 사용하여 클라이언트의 UI를 구성하였습니다.

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138597211-d7926a60-013d-44a3-aa7f-a7d92c83a47f.JPG" width="50%" height="70%"></p>
<h4 align="center"> [Server]</h4>
<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138597265-3248b632-631f-4b66-9379-cf1955581d02.JPG" width="50%" height="70%"></p>
<h4 align="center"> [Client]</h4>


## 💻 사용 기술

- C++
- MFC
- SOCKET통신
- Thread
***   
   
