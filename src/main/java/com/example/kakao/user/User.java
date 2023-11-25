package com.example.kakao.user;

import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.board.Board;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @Column(length = 100, nullable = false, unique = true)
    private String email; // 인증시 필요한 필드

    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 45, nullable = false, unique = true)
    private String username; // 별명

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userTypeEnum;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;



    
    @Builder
    public User(int id, String email, String password, String username, UserTypeEnum userTypeEnum, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userTypeEnum = userTypeEnum;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    


}
