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
- **엔티티 삭제**
</details>