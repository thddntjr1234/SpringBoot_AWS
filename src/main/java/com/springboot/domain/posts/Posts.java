package com.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
// 실제 DB 테이블과 매칭될 클래스(엔티티), DB에 실제 쿼리를 보내는 것이 아닌 Entity 클래스의 수정을 통해 작업한다.
// 엔티티의 카멜케이스 클래스명과 DB의 언더스코어 테이븡명으로 매칭하는 것이 기본
@Entity
public class Posts extends BaseTimeEntity {

    @Id // Primary Key 필드를 지정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙, 이 옵션은 auto_increasement 옵션을 지정
    private Long id;

    @Column(length = 500, nullable = false) // 기본값이 아닌 변경하고자 하는 옵션이 있을 때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
