
# 1. Yolo v4 을 활용한 도로 노후화 자동 감지

## 개발 소개
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
<h4 align="center"> 메인화면1</h4>

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586582-1b0d7aeb-fd4b-4f59-8c61-f306ebbff6f7.jpg" width="60%" height="100%"></p>
<h4 align="center"> 메인화면2</h4>





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
  
  
# 2. 스마트 공장 공정관리 솔루션 애플리케이션(작업자 시스템 부문)

## 화면

### 기능구성도
<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586107-63161121-24de-4797-b11e-7cc7153e4119.JPG" width="70%" height="70%"></p>



### 흐름도
<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586121-67d0a931-a843-42c7-8c01-a6b91980fd62.JPG" width="70%" height="70%"></p>

### 메인화면

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586250-ae317011-e194-450d-8bbb-24e2395a27de.JPG" width="70%" height="70%"></p>
<h4 align="center"> 메인화면1</h4>

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586263-5376d000-9f10-4900-a72f-d52d16b7baae.JPG" width="70%" height="70%"></p>
<h4 align="center"> 메인화면1</h4>

<p align="center"><img src="https://user-images.githubusercontent.com/46813878/138586267-e61fcbc7-c183-4938-9be3-4a169faf2017.JPG" width="70%" height="70%"></p>
<h4 align="center"> 메인화면1</h4>



## 💻 사용 기술

### 안드로이드
- JAVA
- Json
- REST API



