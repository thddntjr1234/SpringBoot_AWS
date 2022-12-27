package com.springboot.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // Auditing기능을 BaseTimeEntity 클래스에 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될 떄 시간이 자동으로 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 마지막으로 변경했을 때의 시간을 자동으로 저장
    private LocalDateTime modifiedDate;
}
