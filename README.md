각 팀원의 프로젝트 파일을 받아서 통합하는 과정 진행 중

# 설명

- 이클립스의 package, import명 자동 정렬 기능을 활용하였다.
- 기능 중심으로( Layered Architecture ) 으로 나눠서 폴더 분류하였다..
    - (dao, design, dto, event, function, run, service)
    - (이하 기능폴더라고 명명)
        
        ![image.png](attachment:86038c3a-fc28-41d7-9176-d7501c2484d8:image.png)
        

- 기능별 생성 파일이 3개 이상일 경우, 개발한 페이지 별로 추가 분류하여 폴더를 생성한다.
    
    ![image.png](attachment:aa3f90a1-260f-4c93-a8c7-a2d3d1584d5b:image.png)
    
    - 2개 이하는 기능 폴더에 저장
    - 페이지 폴더명은 하단 참고.

## 작업 순서

- 개인 프로젝트에서 src만 이름 바꾸고 복사해서 새 프로젝트 src에 넣는다.
- 이클립스 explorer에서 각 파일을 기능 폴더에 드래그로 이동한다.
(혹은 파일 우클릭 > refactor > move)
    - 순서 : dto -> dao ->service->design->event
- 이 때 패키지명에서 오류가 난다면 마우스 대고 ‘correct package declartion’.
    - 혹은 아무 폴더에 파일 이동 후 다시 원래 폴더로 파일 이동하면 패키지명이 자동 수정된다.
    - (반드시 이클립스 내에서 할 것)
- ctrl+shirt+O import 자동 세팅 후 저장하여 오류 없는지 확인
- run 폴더에서 기존 run 클래스를 실행하여 정상 작동 여부 확인.

# 작업자별 페이지 폴더명

- 최승준
    - 내정보메뉴 -> UserMenu
- 이정우
    - 정산 -> Settlement
- 민병조
    - 주문내역 _주문상세내역 -> OrderList
- 남지우
    - 로그인 회원가입 -> LoginRegister
    - 관리자 -> Admin
- 신승덕
    - 회원관리 ->UserMgr
- 이지원
    - 차량리스트 - CarList
    - 차량세부정보 - CarInfo
    - 관리자메뉴 - MgrMenu

# 특이사항

- getConnection은 dao 폴더에서 하나만 사용하기로 함.
- orderListDAO의 getconneciton의 경로 변경했는데
con=gc.getCon(); -> con=gc.getConn();
으로 변경

# 이후 작업 계획

- github 통해서 각자 브랜치 만들어서 작업하여 통합