package helloJpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//엔티티 매핑
//JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
@Entity
//엔티티 속성 name을 이용하여 매핑 테이블 지정 가능(@Table은 엔티티와 매핑할 테이블 지정)
//@Table(name="MBR")
public class Member {

    @Id
    private Long id;
    //유니크 제약 조건 -> DDL 생성 기능 -> 런타임 영향을 주지 않는다. 단순 DDL 생성에 영향 준다.
    @Column(unique = true, length = 10)
    private String name;
    //update 적용 후 필드 추가, 단 지우는건 안된다. 추가만 가능하다.
    //private int age;
    //validate 적용 후 필드 추가 -> 에러가 난다. 컬럼 미존재 에러
    // -> 엔티티와 테이블 정상 매핑되었는지 확인
    //private int gogo;

    //동적 개채 생성위해 기본 생성자
    public Member(){
        
    }

    public Member(Long id, String name){
        this.id=id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
