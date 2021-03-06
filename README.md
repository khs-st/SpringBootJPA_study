# SpringBootJPA_study
1. 이 저장소는 인프런 [초급~활용] 김영한의 스프링 부트와 JPA 실무 완전 정복 수강 후 공부하고 실습한 코드가 저장되어 있는 저장소입니다.
2. 강의 로드맵 - https://www.inflearn.com/roadmaps/149
*****
## 제가 공부한 순서입니다.
1. [자바 ORM 표준 JPA 프로그래밍 - 기본편](https://www.inflearn.com/course/ORM-JPA-Basic)
2. [실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1)
3. [실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-API%EA%B0%9C%EB%B0%9C-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94)
4. [실전! 스프링 데이터 JPA](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%8D%B0%EC%9D%B4%ED%84%B0-JPA-%EC%8B%A4%EC%A0%84)
5. [실전! Querydsl](https://www.inflearn.com/course/Querydsl-%EC%8B%A4%EC%A0%84)
*****
## 1. 자바 ORM 표준 JPA 프로그래밍 - 기본편
### 1.1 JPA 소개
#### JPA를 사용하는 이유
- SQL 중심적인 개발에서 객체 중심으로 개발
- 생산성
- 유지보수
- 패러다임의 불일치 해결
- 성능
- 데이터 접근 추상화와 벤더 독립성
- 표준
### 1.2 Hello JPA - 프로젝트 생성
- JPA 설정하기
- /META-INF/pesistence.xml 파일 및 디렉토리 생성
- persistence-unit name으로 이름 지정
- javax.persistence로 시작: JPA 표준 속성
- hibernate로 시작: 하이버네이트 전용 속성
### 1.3 Hello JPA - 애플리케이션 개발
- JpaMain 클래스 생성
- JPA 동작 확인
- javax.xml.bind.JAXBException 에러 해결 -> pom.xml에 dependency 추가
- **엔티티 매니저 팩토리는 하나만 생성하여 애플리케이션 전체에서 공유한다.**
- **엔티티 매니저는 쓰레드간 공유X**
- **JPA의 모든 데이터 변경은 트랜잭션 안에서 실행해야한다.**
- 회원 등록, 수정, 삭제, 단건 조회 테스트
- 애플리케이션이 필요한 데이터만 DB에서 불러오기 위해 검색 조건 포함된 SQL이 필요하다.
- <details><summary>JPQL</summary>
  - JPA를 사용하면 엔티티 객체를 중심으로 개발한다.<br>
  - SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원<br>
  - JPQL은 엔티티 객체를 대상으로 쿼리<br>
  - SQL은 데이터베이스 테이블을 대상으로 쿼리
  - 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
</details>

### 1.4 영속성 관리 - 내부 동작 방식
#### 1.4.1 영속성 컨텍스트 1
- 영속성 컨텍스트란? “엔티티를 영구 저장하는 환경”이라는 뜻이며 논리적인 개념이다.
- 엔티티 매니저를 통해서 영속성 컨텍스트에 접근
- **EntityManager.persist(entity);**
- <details><summary>엔티티의 생명주기</summary>
  - 비영속 (new/transient) -> 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태<br>
  - 영속 (managed) -> 영속성 컨텍스트에 관리되는 상태 <br>
  - 준영속 (detached) -> 영속성 컨텍스트에 저장되었다가 분리된 상태<br>
  - 삭제 (removed) -> 삭제된 상태 
</details>

- <details><summary>영속성 컨텍스트의 이점</summary>
  - 1차 캐시<br>
  - 동일성(identity) 보장<br>
  - 트랜잭션을 지원하는 쓰기 지연<br>
  - 변경 감지(Dirty Checking)<br>
  - 지연 로딩(Lazy Loading)
</details>

#### 1.4.2 영속성 컨텍스트2
<details><summary>엔티티 조회, 등록, 수정, 삭제, 플러시 정리</summary>

- **엔티티 조회, 1차 캐시**
<pre>
//엔티티를 생성한 상태(비영속) 
Member member = new Member(); 
member.setId("member1"); 
member.setUsername("회원1");
//엔티티를 영속 
em.persist(member);
</pre>
- **1차 캐시에서 조회**
<pre>
Member member = new Member();
member.setId("member1");
member.setUsername("회원1");
//1차 캐시에 저장됨
em.persist(member);
//1차 캐시에서 조회
Member findMember = em.find(Member.class, "member1");
</pre>
- **데이터베이스에서 조회, 영속 엔티티의 동일성 보장**
<pre>
Member findMember2 = em.find(Member.class, "member2");
//영속 엔티티의 동일성 보장
Member a = em.find(Member.class, "member1"); 
Member b = em.find(Member.class, "member1");
//아래 코드 실행 시 true로 반환된다.
System.out.println(a == b); 
</pre>
- **엔티티 등록 -> 트랜잭션을 지원하는 쓰기 지연**
<pre>
//영속
Member member1 = new Member(150L,"A");
Member member2 = new Member(160L,"B");
em.persist(member1);
em.persist(member2);
System.out.println("=================");
//커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
tx.commit();
</pre>
- **엔티티 수정 - 변경 감지**
<pre>
//엔티티 수정 - 변경 감지
Member member = em.find(Member.class,150L);
//데이터 변경 후 persist 호출할 필요 없다.
member.setName("Modify Name");
//순서: 1. flush -> 2. 엔티티와 스냅샷 비교 -> 3. UPDATE SQL 생성 -> 4. flush -> 5. commit
</pre>
- **플러시**
  - 영속성 컨텍스트를 플러시 하는 방법
  - em.flush() -> 직접 호출
  - 트랜잭션 커밋 -> 플러시 자동 호출
  - JPQL 쿼리 실행 -> 플러시 자동 호출

- **준영속 상태**
  - 준영속 -> 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
  - em.detach(member); -> 특정 엔티티만 준영속 상태로 전환
  - em.clear(); -> 영속성 컨텍스트를 완전히 초기화

- **엔티티 삭제**
</details>

### 1.5 엔티티 매핑
#### 1.5.1 객체와 테이블 매핑
- **엔티티 매핑**
<pre>
@Entity : JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
• 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자) 
• final 클래스, enum, interface, inner 클래스 사용X 
• 저장할 필드에 final 사용 X
@Entity 속성 정리
• 속성: name
• JPA에서 사용할 엔티티 이름을 지정한다.
• 기본값: 클래스 이름을 그대로 사용
@Table:  엔티티와 매핑할 테이블 지정
• name 속성을 이용해 매핑할 테이블 이름 지정이 가능하다.
• 기본값은 엔티티 이름을 사용한다.
• catalog, schema, uniqueConstraints(DDL) 등이 있다.
</pre>

#### 1.5.2 데이터베이스 스키마 자동 생성

<details><summary>데이터베이스 스키마 자동 생성</summary>

  - DDL을 애플리케이션 실행 시점에 자동 생성
  - 테이블 중심 -> 객체 중심
  - 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 생성(DDL은 개발 장비에서만 사용)
  - 속성
    - create: 기존 테이블 삭제 후 다시 생성(DROP + CREATE TABLE)
    - create-drop: create와 같으나 종료시점에 테이블 DROP 시킨다.
    - update: 변경분만 반영(운영DB에 사용하면 안된다.)
    - validate: 엔티티와 테이블이 정상 매핑되었는지 체크
    - none: 사용하지 않음
  - **주의!!! -> 운영 장비에는 절대 create, create-drop, update 사용하면 안된다.**
    - 개발 초기 단계 -> create 또는 update
    - 테스트 서버 -> update 또는 validate
    - 스테이징, 운영 서버 -> validate 또는 none
  - DDL 생성 기능
    - 런타임 영향을 주지 않고 단순 DDL 생성에 영향 준다.
</details>

#### 1.5.3 필드와 컬럼 매핑

<details><summary>매핑 어노테이션 정리</summary>

<pre>
• @Column: 컬럼 매핑
• @Temporal: 날짜 타입 매핑
• @Enumerated: enum 타입 매핑
• @Lob: BLOB, CLOB 매핑
• @Transient: 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
</pre>
- @Column
<pre>
• name: 필드와 매핑할 테이블의 컬럼 이름(기본값은 객체의 필드 이름이다.)
• insertable, updatable: 등록, 변경 가능 여부
• nullable: null값 허용 여부를 설정, false로 설정 시 DDL 생성 시 not null 제약조건 붙음
• unique: @Table의 uniqueConstraints와 같지만 간단하게 유니크 제약조건 시 사용
• columnDefinition: 데이터베이스 컬럼 정보를 직접 줄 수 있다.( 예시: varchar(100) default 'EMPTY')
• length: 문자 길이 제약조건, String 타입에 사용한다.
• precision, scale: BigDecimal 타입에서 사용한다.
</pre>
- @Enumerated: 자바 enum 타입을 매핑할 떄 사용한다. -> ORDINAL 사용X
<pre>
속성 value
• EnumType.ORDINAL: enum 순서를 데이터베이스에 저장
• EnumType.STRING: enum 이름을 데이터베이스에 저장
기본값: • EnumType.ORDINAL: enum 순서를 데이터베이스에 저장
• EnumType.STRING: enum 이름을 데이터베이스에 저장
</pre>
- @Temporal: 날짜 타입(Date, Calendar)을 매핑할 때 사용
- @Lob: 데이터베이스 BLOB, CLOB 타입과 매핑
<pre>
• @Lob에는 지정할 수 있는 속성이 없다. 
• 매핑하는 필드 타입이 문자면 CLOB 매핑, 나머지는 BLOB 매핑
</pre>

</details>

#### 1.5.4 기본 키 매핑

<details><summary>기본 키 매핑 방법</summary>
<pre>
• 직접 할당: @Id만 사용
• 자동 생성(@GeneratedValue) 
   • IDENTITY: 데이터베이스에 위임, MYSQL 
     • JPA는 보통 트랜잭션 커밋 시점에 INSERT SQL 실행
     • IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자를 조회
   • SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE 
     • @SequenceGenerator 필요
   • TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
     • @TableGenerator 필요
     • 장점: 모든 데이터베이스에 적용 가능
     • 단점: 성능
• 권장하는 식별자 전략
   • 기본 키 제약 조건: null 아님, 유일, 변하면 안된다.
   • 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(비지니스와 상관없는)를 사용하자. 
   • 예를 들어 주민등록번호도 기본 키로 적절하기 않다. 
   • 권장: Long형 + 대체키 + 키 생성전략 사용
</pre>

</details>

#### 1.5.5 실전 예제 1 - 요구사항 분석과 기본 매핑
- 요구사항 분석
  - 회원은 상품을 주문할 수 있다. 
  - 주문 시 여러 종류의 상품을 선택할 수 있다.
- 기능 목록
  - 회원 기능
    - 회원등록
    - 회원조회
  - 상품 기능
    - 상품등록
    - 상품수정
    - 상품조회
  - 주문 기능
    - 상품주문
    - 주문내역조회
    - 주문취소
- 테이블 설계
![image](https://user-images.githubusercontent.com/64995062/150982057-82f372d8-8ab7-4e4a-aff6-a01648254375.png)