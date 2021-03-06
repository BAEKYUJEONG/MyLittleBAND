# TID(Today I Do)



###### 2021-01-18

> 엄재웅

- 팀원들과 Git 사용법 공유
- 일과중에는 화면 설계를 같이 작업하였다 - draw.io



> 김영현

- 밴드 멤버 추가 관련 아이디어 제시. 카카오링크를 통해 구현 계획
- 동영상 재생, 메인 스트리밍 부분, 밴드 관리 와이어프레임 제작



> 신인섭

* git환경설정
* DB 설계
* 프론트 페이지 구축



> 박성우

- git 환경설정

- 페이지 레이아웃 구성



> 백유정

- 기능 확정 및 컨펌
- draw.io 화면 설계
  - 메인 페이지
  - 로그인 페이지
  - 밴드 관리 페이지
  - 공지 페이지



*****



###### 2021-01-19

> 엄재웅

* 팀원들과 함께 페이지 설계
  * 팀원들과 페이지들의 연결을 시각화 하여 설계함
  * 디자인에 대한 회의가 조금 더 필요하다고 느낌 
    * 특히 타임테이블 쪽의 디자인을 어떻게 가지고 갈 것인가?
* 내일부터는 화면을 바탕으로 DB설계를 할 예정
* 팀원들에게 다시 git flow에 대한 전략을 설정할 필요성을 어필, 연습을 다같이 해봄



> 김영현

- 화면 설계 마무리(밴드 관리, 타임 테이블 관련 수정)
- 컨설턴트님과 코치님과 회의 후 타임 테이블 설계 수정 및, 팔로우 관련 보완 부분 회의



> 신인섭

* DB 설계
* 프론트 페이지 구축



> 박성우

- 페이지 구축
- UI/UX 정리하기



> 백유정

- draw.io 화면설계
  - Main page timtable 수정
  - ManageBand 수정 : 필터 추가 구상 필요
  - Follow 탭 추가
  - VideoBoard 검색 및 필터 수정
- DB 설계 : 추가 구현 필요



*****



###### 2021-01-20

> 엄재웅

* 시나리오 제작
  * 회원가입
  * 아이디/비밀번호 찾기
  * 공연 스트리밍
  * 공연 일정(Main), 타임 테이블
  * 랭킹
  * 공연 신청
* 팀원들과 시나리오를 보며 기능 Flow 회의
* 화면, 기능 부분 수정에 관한 회의
  * 공연 신청 시 상세 정보는?
  * 좋아요 한 영상들을 모아서 보여줄 것인지
  * 팔로우 버튼이 위치할 곳들
  * 탈퇴시 가입된 밴드, 게시물, 댓글 처리는?
  * 가입 프로세스에서 이메일을 재발송하도록



> 김영현

​	



> 신인섭

 - 팀원들과 useCaseDiagram 생성
 - DB설계



> 박성우

- 화면별 기능 설계
- 화면별 flow 정리



> 백유정

- 화면 설계를 기반으로 한 시나리오 제작
- Use Case Name, Actor, 화면, Pre-condition, Flow of Events, Alternate Flows 로 구분하여 제작
- 회의를 통해 수정 및 보안 : 추후 DB 설계 시 명사, 동사 구분하여 data, 속성으로 이용
- 밴드 멤버 초대, 공연 신청 부분 추가 회의 필요 
- 제작 목록
  - 밴드
  - 밴드 추가
  - 밴드 수정
  - 밴드 해체
  - 팔로우
  - 팔로우 목록
  - 밴드 상세
  - 밴드 비디오
  - 좋아요



*****



###### 2021-01-21

> 엄재웅

* 시나리오 별 엔티티를 뽑아서 테이블, 컬럼으로 추출
* 부족한 컬럼과 테이블을 추가하거나 조정
* ERD를 그려 팀원들과 토의하면서 데이터베이스 구조를 조정
* docker에 관하여 설명하면서 스터디 시간을 가짐
  * jira 정책을 어떻게 가져갈 것인지 생각해야됨

> 김영현

- DB 설계를 진행하였다. 
- ERD 다이어그램을 팀원들과 같이 설계하였다.

> 신인섭

 -팀원들과 DB설계
   - UseCase에서 키를 찾아내고 분류하여 테이블과 컬럼 판별
   - 판별된 값을 바탕으로 ERD설계 및 데이터베이스 정규화

> 박성우

- 화면별 기능과 설계를 바탕으로 명사 추출 및 정리
- 명사를 기능과 DB로 구성
- 기능에 맞게 DB테이블과 속성을 나누고 작성
- 테이블간의 관계를 설정하고 작성

> 백유정

- Key abstraction : 시나리오를 이용한 DB 설계 과정
  - TABLE, COLUMN 뽑아내기
- ERD 만들기 : draw.io를 이용 
  - TABLE끼리의 관계 설정 및 TABLE 수정 및 추가
  - 공통 코드로 TABLE 정리
- 추가적으로 캘린더, 파일 저장, 게시판에 대한 구상 및 피드백 필요


*****


###### 2021-01-22

> 엄재웅

* 디비 설계 마무리
* 팀원들과 프론트, 백 구성 토의
* 프론트 환경설정, vuex 설정



> 김영현



> 신인섭

- 팀원들과 DB, Frontend, Backend 항목 구성 회의
- Frontend Components 스켈레톤 코드 생성

> 박성우



> 백유정

- Frontend 구성 및 DB, Backend 구성
- Backend 환경설정 및 DTO, DAO, Service, Controller 구성




*****



###### 2021-01-25

> 엄재웅

* 타임라인 컴포넌트 부분 제작

* css만으로 디자인 하다가 vuetify 로 제작하기로 결정...

  

> 김영현

- Streaming 관련하여 개념 공부 및 사용할 API 공부

> 신인섭

- Frontend MainPage 중 Ranking 기능 구현
  - 보드로 동영상 목록 표시
  - 좌우로 페이지 이동 가능
  - 태그를 활용한 추가 정보 기입 가능

> 박성우



> 백유정






*****



###### 2021-01-26

> 엄재웅

* AWS docker, mariadb설치 완료
* Restful API 설계를 위한 문서 작성
* 팀원들과 AWS mariaDB - workbench간 연결
* Vuetify header 작업



> 김영현





> 신인섭
- Frontend MainPage 중 FindId 기능 구현
- 모달창으로 성공 및 에러 알림 표시
- 비동기통신
- 이메일 전송 시스템 구축 필요함




> 박성우





> 백유정





*****



###### 2021-01-27

> 엄재웅

* 팀원들과 Restful API 문서 작성



> 김영현





> 신인섭
- FrontEnd 페이지 중 공지사항 페이지 구현
- NoticeWrite, NoticeDetail, NoticeCreate, NoticeModify구현
- router를 이용한 페이지 이동 가능
- backend와의 연동테스트 필요함



> 박성우





> 백유정

- NoticeController mapping 부분 수정 필요
- VideoBoard dao, service 작성 및 controller 구상 중

- PostMan 관련 공부




*****



###### 2021-01-28

> 엄재웅





> 김영현





> 신인섭

- Frontend component중 BoardBand 기능구현
  - BoardBand List,Create,Modify,Detail 컴포넌트 구성
- Frontend Notice 컴포넌트 수정
  - axios 잘못된 함수 수정
- Frontend Login 컴포넌트 기능구현
  - 모달창 띄우는 작업완료
  - Vuex는 아직 작업 중



> 박성우





> 백유정




*****



###### 2021-01-28

> 백유정
- boardId 등 id 부분 string 문제로 인해 
    comment 기능들에 관한 모든 코드 약간 수정

- db 연동 및 스웨거 연동 중

- videoboard db 설정 및 foreign key 문제로 dto 재설정

- 파일 업로드 부분 공부 및 작업 중
  - MultipartFile or MultipartResolver 이용 or  commons-fileupload 를 이용하기 위해서는 dependencies를 반드시 추가해야함 
  +bean파일도 필요
  - 파일 업로드 참고링크.hwp 참고 : 본 사이트 중 그나마 괜찮은 것들 몇개 뽑아둠