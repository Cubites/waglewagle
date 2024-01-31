# waglewagle

## 프로젝트 주제
* 중고 경매 사이트 - 와글와글

## 팀원
* 강태연, 김이슬, 송진주, 장원, 주희철

## 프로젝트 진행 순서
<details>
  <summary>
    1. 화면 디자인
  </summary>
  
  ![화면 디자인](https://github.com/Cubites/waglewagle/assets/75084369/ec938f09-1bdc-4d1b-80c5-d55411de7ae4)
</details>
<details>
  <summary>
    2. 사용자 시나리오 작성
  </summary>
  
  ![사용자 시나리오](https://github.com/Cubites/waglewagle/assets/75084369/7956e714-deff-40d0-be17-8a8767d8f90d)
</details>

<details>
  <summary>
    3. 기능 명세서 작성
  </summary>
  
  ![기능 명세서](https://github.com/Cubites/waglewagle/assets/75084369/b77cc58c-0305-4e15-a285-ed19dbf00469)
</details>

<details>
  <summary>
    4. ERD 설계
  </summary>
  
  ![ERD](https://github.com/Cubites/waglewagle/assets/75084369/bfa994e8-cbee-4710-86e6-339d9bd77196)
</details>

<details>
  <summary>
    5. 인터페이스 명세서 작성
  </summary>
  
  ![인터페이스 명세서](https://github.com/Cubites/waglewagle/assets/75084369/cbf31a39-70e9-4612-9587-f77745f0418e)
</details>

6. 개발 

7. 테스트
  
8. 배포

# 최종 결과

## 메인 페이지

![와글와글_메인페이지](https://github.com/Cubites/waglewagle/assets/75084369/9bce1ea6-eaaf-4345-8d4d-ea88f62ffb70)

- 메인페이지
    - 배너를 통해 서비스 사용 방법을 안내합니다.
    - 실시간 인기상품, 관심지역 상품 등 다양한 기준에 따라 상품 리스트를 화면에 표시합니다.

## 로그인, 로그아웃

![와글와글_로그인_로그아웃](https://github.com/Cubites/waglewagle/assets/75084369/9a1d53e3-0bca-46f3-a8a8-ac2dd467de0e)

- 로그인 및 로그아웃 기능
    - 로그인이 필요한 페이지에 접근하면 로그인 페이지로 이동합니다.
    - 로그인 후 상단 아이콘을 통해 로그아웃할 수 있습니다.

## 경매글 검색

![검색](https://github.com/Cubites/waglewagle/assets/75084369/655528a6-5395-4c40-8769-6c704f85bc27)

- 키워드 검색 기능
    - 관련 키워드를 입력하여 원하는 상품을 검색할 수 있습니다,
- 카테고리 + 키워드 검색 기능
    - 상품의 카테고리와 키워드를 통해 원하는 상품을 검색할 수 있습니다

## 경매글 등록

![등록](https://github.com/Cubites/waglewagle/assets/75084369/6077c38f-d380-45e2-b4da-e30cb181846d)

- 판매 상품 사진 첨부 및 글 제목 및 상세 설명, 카테고리, 거래 지역, 경매 마감일 입력
- 이미지는 총 5개 지정 가능합니다.
- 첫 이미지는 대표 이미지로 지정됩니다. 여러 이미지가 있을 때 앞에 이미지를 삭제하면, 다음 이미지가 자동으로 대표 이미지로 지정됩니다.
- 물품 등록 검증:
    - 클라이언트 검증: 대표이미지, 시작가격, 카테고리, 거래장소를 정하지 않을 시 등록 게시글 등록하지 못하도록 자바스크립트를 활용하여 막아두었습니다.
        
        거래 마감일은 오늘 날짜에 이전 날짜는 지정하지 못하도록 자바스크립트를 이용하여 막아 두었습니다.
        
        시작가격은 0보다 작은 가격을 적거나, 문자를 적지 못하도록 막아두었습니다.
        
    - 서버 검증 : Bean Validation 기능을 확용하여, 대표 이미지, 시작가격, 카테고리, 거래장소를 등록하지 않을 시 게시글 등록에 실패하고, 이미 입력한 값은 유지한 채 다시 게시글 등록 페이지로 넘어가도록 하였습니다.
        
        시작 가격이 0보다 작거나, 숫자가 아닐 시 다시 게시글 등록 페이지로 넘어가도록 하였습니다.
        

## 경매 호가

![호가](https://github.com/Cubites/waglewagle/assets/75084369/21ed196f-2185-4b71-954d-b6c5c9b05dc1)

- 차점가격 봉인경매 방식 사용
    - 최고가를 입찰한 사람에게 낙찰 + 낙찰 가격은 2번째로 높은 가격입니다.
        - 호가 예외 상황:
            - 이미 본인의 최대호가를 제출했다면, 호가 불가 합니다.
            - 본인이 올린 글에 호가 불가 합니다.
            - 가용 포인트보다 높은 호가 불가 합니다.
    - 게시글에 찜하기
        - 마이페이지에서 찜한 글 확인할 수 있습니다.
    - 경매 종료
        - 지정된 시간이 지나면 경매가 자동 종료되도록 스케쥴링을 등록해 두었습니다.
    - 신고하기
        - 게시글을 신고할 수 있습니다.

## 거래 확정 & 파기 신고 페이지

- 거래확정 기능
    - 거래가 확정된 사용자들은 직거래를 통해 상품 및 재화를 교환하므로 사용성을 고려해 모바일 맞춤 페이지로 제작했습니다.
    - 거래 확정 페이지
        - 상품을 받은 사용자는 거래 완료를 진행할 수 있습니다.
    - 거래 완료 후 페이지
        - 거래가 완료되면, 판매자의 계정으로 포인트가 입금됩니다.
    - 거래 파기 및 신고 페이지
        - 정상적으로 거래가 완료되지 않은 경우 구매자는 판매자를 신고할 수 있습니다.

- 거래 확정 혹은 거래 파기 신고 선택 페이지 / 거래 확정 후 페이지
<p float="left">
  <img src="https://github.com/Cubites/waglewagle/assets/75084369/c008b658-484a-4681-bde6-81c31915001e" width="40%" />
  <img src="https://github.com/Cubites/waglewagle/assets/75084369/28010c4b-f69f-4a6b-8981-ba3390721df8" width="40%" />
</p>

- 거래 파기 신고 페이지 / 거래 파기 신고 후 페이지
<p float="middle">
  <img src="https://github.com/Cubites/waglewagle/assets/75084369/f21f61a8-faa3-44aa-bef9-856f3e766367" width="40%" />
  <img src="https://github.com/Cubites/waglewagle/assets/75084369/90a1eb32-10ca-43a7-91ec-0cc36fd85b1e" width="40%" />
</p>

## 마이페이지

![마이 페이지](https://github.com/Cubites/waglewagle/assets/75084369/e336f549-ddc6-41cc-a48c-3c4acac43261)

- 상품 메뉴
    - 경매 중 : 사용자가 참여한 경매 중 현재 진행 중인 상품 목록입니다.
    - 거래 중 : 사용자가 낙찰받았지만 아직 상품을 인계받지 못한 상품 목록입니다.
    - 판매 완료 : 사용자가 작성한 판매글 중 낙찰자에게 상품 인계까지 완료된 상품 목록입니다.
    - 구매 완료 : 사용자가 낙찰받고 수령까지 완료된 상품 목록입니다.
    - 거래 파기 : 사용자가 작성한 판매글 중, 낙찰 후 거래를 파기한 상품 목록입니다.
    - 유찰 상품 : 사용자가 작성한 판매글 중, 입찰자가 없이 경매가 종료된 상품 목록입니다.
    - 찜 상품 : 사용자가 찜한 상품 목록입니다
- 내 정보 메뉴
    - 관심 지역 수정 : 관심지역을 수정하는 페이지입니다.
    - 비밀번호 수정 : 사용자 계정의 비밀번호를 변경하는 페이지입니다.
    - 문의 내역 조회 : 사용자가 작성한 문의 목록입니다.
    - 회원 탈퇴 : 회원탈퇴 기능입니다. 참여중인 경매가 있거나 거래 중인 상품이 있으면 탈퇴할 수 없습니다.

## 충전

![충전](https://github.com/Cubites/waglewagle/assets/75084369/1fb3dc6c-34f0-46b1-a656-96f60e125b44)

## 관리자 페이지

![관리자 페이지](https://github.com/Cubites/waglewagle/assets/75084369/9cfda55a-511c-406c-882f-23fa5450bb51)

- 공지 관리, 문의 답변 작성 및 관리, 상품 관리, 회원 관리, 통계 기능
    - 회원 /  상품별 신고수에 맞게 관리자가 접근 권한 설정 가능 ( 회원 : 3단계 , 상품 : 2단계 )
    - 공지/ 문의내역에 대한 CRUD 기능
    - 전체 데이터 확인 가능한 통계 페이지 ( 카테고리 / 시간 / 성별 )
- 관리자 계정 관리(관리자 등급에 따라 접근 권한이 달라짐)
    - 접근이 불가능한 등급은 접근 불가능한 페이지
        
        ![관리자접근불가페이지](https://github.com/Cubites/waglewagle/assets/75084369/97c2d0cd-9882-4f8c-887a-008378552a9a)
        
    - 관리자 로그인 페이지 제외한 url 접근시  404 에러 페이지로 이동
        ![4XX 오류 페이지](https://github.com/Cubites/waglewagle/assets/75084369/cb87043e-8837-4516-b1f6-a8b5e7df2284)
