# AndroidTeamProject
졸업작품용 앱 개발 팀 프로젝트 

안드로이드 스튜디오 IDE 사용
## 활동 설명
AWS EC2에 배포한 nginx/PHP REST API와 MySQL을 Retrofit2·OkHttp 기반 안드로이드(Java) 앱과 연동하고, AWS S3 업로드 파이프라인을 통해 수집한 스타일 사진을 인기·최신 홈 피드, 상세 좋아요 인터랙션, 프로필 그리드까지 연결하는 패션 커뮤니티 서비스를 팀 프로젝트로 구축했습니다.

UI는 Figma를 활용
## 기술 스택
- **Android: Java, Android Studio** — View/Data Binding과 RecyclerView(GridLayoutManager)로 화면을 구성하고 하단 내비게이션에 ObjectAnimator 전환 효과를 적용했습니다.
- **Network: Retrofit2, OkHttp, Gson** — `ApiClient`에서 공통 Retrofit 인스턴스를 정의해 로그인, 회원가입, 피드 조회·업로드, 좋아요 토글 API를 호출했습니다.
- **Storage: AWS S3** — `Uploading_re`에서 Amazon S3 TransferUtility로 갤러리 이미지를 업로드하고 완료 시점의 URL을 서버에 전달했습니다.
- **Backend: PHP, Nginx** — EC2에서 구동되는 PHP 스크립트가 게시물 CRUD, 로그인/회원가입, 좋아요 토글 로직을 처리합니다.
- **Database: MySQL, phpMyAdmin** — PHP에서 prepared statement로 `user_table`, `post_table`, `like_table`을 조작하고 phpMyAdmin으로 스키마와 데이터를 관리했습니다.
- **Server: AWS EC2** — nginx/PHP 환경과 S3 업로드 엔드포인트를 호스팅하며 안드로이드 클라이언트와 REST 통신을 담당했습니다.
- **Tools: Android Studio, Sublime Text(SFTP 배포)** — `BackEnd/sftp-config.json` 설정을 통해 Sublime Text에서 저장과 동시에 EC2의 `/var/www/html/`로 PHP 파일을 배포했습니다.

aws ec2로 nginx로 웹서버를 띄워서 php를 사용하여 Restful API 작성
## 주요 기능 및 개발 내용

앱 최적화를 위해 aws s3 서버를 사용하여 대용량 이미지 처리
### 안드로이드 앱
- **로그인·회원가입 플로우** — `Login_re`에서 입력값 검증 후 로그인 API를 호출해 사용자 정보를 세션(`Login_User`)에 보관하고 홈으로 이동하며, `Signup_re`는 신체 정보까지 입력받아 `register.php`로 전송합니다.
- **홈 피드 정렬·탐색** — `Homepage_re`가 `image.php` 응답을 수신한 뒤 좋아요 순·최신 순으로 복제 리스트를 정렬하고 Glide로 썸네일을 바인딩하여 탑 카드와 최신 카드에서 `StylePage`로 이동시킵니다.
- **스타일 상세 상호작용** — `StylePage`가 Glide로 원본 이미지를 출력하고, 좋아요/좋아요 취소 버튼이 `stylepage.php`에 type 파라미터로 토글 요청을 보내며 좋아요 수·아이콘 상태를 실시간 갱신합니다. 댓글 목록, 업로드 화면, 좋아요 사용자 목록으로의 네비게이션도 제공합니다.
- **이미지 업로드 파이프라인** — `Uploading_re`에서 갤러리 이미지를 선택해 S3 `sunghobucket`의 `images/` 경로에 업로드하고, 업로드 완료 콜백에서 생성된 URL을 `image.php`에 POST 후 프로필로 리다이렉트합니다.
- **프로필 및 팔로우 탐색** — `Profile_re`가 사용자 게시물 목록을 가져와 게시물 수를 집계하고, `ProfileFragment`에서 3열 RecyclerView로 썸네일을 보여준 뒤 항목 클릭 시 상세 페이지로 이동합니다. 팔로워/팔로잉 카드는 `Following_followerPage`로 연결되어 탭 전환식 Fragment UI를 제공합니다.
- **세션·네비게이션 관리** — 하단 내비게이션 버튼들은 홈·업로드·검색·프로필 간의 이동 흐름을 통일하며, 로그인된 사용자 정보는 `Login_User` 싱글톤으로 액티비티 전반에 공유됩니다.

백엔드는 sublime text로 php코드 작성후 ftp를 사용해서 aws ec2로 전송 
### 백엔드 (PHP)
- **게시물 API (`image.php`)** — GET 요청 시 전체 또는 특정 이메일의 게시물을 조회하고, POST로 전달된 이미지 URL과 메타데이터를 `post_table`에 저장해 JSON으로 결과를 반환합니다.
- **사용자 인증·등록 (`login.php`, `register.php`)** — prepared statement로 이메일/비밀번호를 검증하여 사용자 프로필을 JSON으로 돌려주고, 회원가입 시 신체·연락처 정보를 함께 저장합니다.
- **좋아요 관리 (`stylepage.php`)** — `type` 파라미터에 따라 like_table을 삽입/삭제하고 `post_table.postlike`를 업데이트한 뒤 최신 좋아요 수와 사용자 목록을 JSON 배열로 제공합니다.

## 프로젝트를 통해 얻은 기술적 인사이트
1. **Fragment·RecyclerView 조합의 재사용성** — `ProfileFragment`를 재사용 가능한 그리드 어댑터로 구성해 프로필 갤러리와 상세 진입 흐름을 깔끔하게 유지했습니다.
2. **클라우드 업로드 상태 제어** — S3 TransferUtility 상태 리스너를 활용해 업로드 완료 이후에만 게시물 등록 API를 호출하여 비동기 파일 전송과 서버 반영 타이밍을 정밀하게 제어했습니다.
3. **Retrofit·OkHttp 로깅으로 네트워크 품질 향상** — `ApiClient`와 `Signup_re`에서 HttpLoggingInterceptor를 구성해 요청/응답을 추적하며 오류 재현과 API 계약 검증을 수월하게 했습니다.
4. **PHP prepared statement로 데이터 무결성 확보** — 로그인·게시물·좋아요 로직 전반에서 prepared statement와 UTF-8 인코딩 설정을 적용해 SQL 인젝션을 방지하고 다국어 데이터 처리 안정성을 높였습니다.
