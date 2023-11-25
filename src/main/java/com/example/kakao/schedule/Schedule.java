package com.example.kakao.schedule;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.enums.ScheduleEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "schedule_tb")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Aquarium aquarium;
    
    @Column(length = 30, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ScheduleEnum scheduleEnum;

    private Integer betweenDay;

    private Timestamp targetDay;

    private Boolean isCompleted;
    
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;






    @Builder
    public Schedule(int id, Aquarium aquarium, String title, ScheduleEnum scheduleEnum, Integer betweenDay,
            Timestamp targetDay, Boolean isCompleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.aquarium = aquarium;
        this.title = title;
        this.scheduleEnum = scheduleEnum;
        this.betweenDay = betweenDay;
        this.targetDay = targetDay;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    

    



    


}
