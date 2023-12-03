package com.example.kakao.aquarium;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.Diary;
import com.example.kakao._entity.Equipment;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.board.Board;
import com.example.kakao.fish.Fish;
import com.example.kakao.schedule.Schedule;
import com.example.kakao.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "aquarium_tb")
public class Aquarium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
    private List<Fish> fishList = new ArrayList<>();

    @OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();
    
    @OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
    @OrderBy("createdAt DESC") // 에피소드 최근 순서대로 정렬
    private List<Diary> diaryList = new ArrayList<>();
    
    // @OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
    // private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
    private List<Equipment> equipmentList = new ArrayList<>();
    // 여과기, 히터, 바닥재, 조명, CO2 ...

    @Column(length = 30, nullable = false)
    private String title;

    private String intro;

    private String photo;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum markColorEnum;

    private Boolean isFreshWater;

    private String size; // 길이/폭/높이

    // 염도, 수온, 질산염, 암모니아 등등 그래프로 보여주기?

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;



    @Builder
    public Aquarium(int id, User user, List<Fish> fishList, List<Schedule> scheduleList, List<Diary> diaryList,
            List<Equipment> equipmentList, String title, String intro, String photo, UserTypeEnum markColorEnum,
            Boolean isFreshWater, String size, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.fishList = fishList;
        this.scheduleList = scheduleList;
        this.diaryList = diaryList;
        this.equipmentList = equipmentList;
        this.title = title;
        this.intro = intro;
        this.photo = photo;
        this.markColorEnum = markColorEnum;
        this.isFreshWater = isFreshWater;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    


}
