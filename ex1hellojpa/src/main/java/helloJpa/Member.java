package helloJpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//엔티티 매핑
//JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
@Entity
//엔티티 속성 name을 이용하여 매핑 테이블 지정 가능(@Table은 엔티티와 매핑할 테이블 지정)
@Table(name="MBR")
public class Member {

    @Id
    private Long id;
    private String name;
    
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
