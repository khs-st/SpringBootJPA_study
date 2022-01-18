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