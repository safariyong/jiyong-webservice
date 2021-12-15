package com.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 createdDate,modifiedDate 칼럼으로 인식.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing 기능 포함.
public abstract class BaseTimeEntity{
    @CreatedDate
    private LocalDateTime createdDate; //Entitiy 생성될 때 시간저장

    @LastModifiedDate
    private LocalDateTime modifiedDate; //Entity 변경할 때 시간저장
}

