package helloJpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//엔티티 매핑
//JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
@Entity
//엔티티 속성 name을 이용하여 매핑 테이블 지정 가능(@Table은 엔티티와 매핑할 테이블 지정)
//@Table(name="MBR")
public class Member {

    //@Id
    //private Long id;
    //유니크 제약 조건 -> DDL 생성 기능 -> 런타임 영향을 주지 않는다. 단순 DDL 생성에 영향 준다.
    //@Column(unique = true, length = 10)
    //private String name;
    //update 적용 후 필드 추가, 단 지우는건 안된다. 추가만 가능하다.
    //private int age;
    //validate 적용 후 필드 추가 -> 에러가 난다. 컬럼 미존재 에러
    // -> 엔티티와 테이블 정상 매핑되었는지 확인
    //private int gogo;

    @Id
    private Long id;
    
    //데이터베이스 컬럼명 -> updatable 변경 가능 여부(기본값 true)
    @Column(name = "name", updatable = false)
    private String username;

    private Integer age;

    //기본이 ORDINAL
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    //testLocalDate date,
    //testLocalDateTime timestamp 으로 DB에 생성된다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member(){

    }

}
