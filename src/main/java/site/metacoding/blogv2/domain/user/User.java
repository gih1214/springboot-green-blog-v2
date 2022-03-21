package site.metacoding.blogv2.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호증가 전략 적용하기
    private Integer id;

    @Column(unique = true, nullable = false, length = 12)
    private String username;

    @Column(nullable = false, length = 12)
    private String password;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 300)
    private String addr; // API 주소 라이브러리 사용할 예정

    @CreatedDate // insert
    private LocalDateTime createDate;
    @LastModifiedDate // insert, update
    private LocalDateTime updateDate;

}
