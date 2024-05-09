package com.example.churchFucTest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Data
@Entity
@Table(name = "WednesdaySermons")
@AllArgsConstructor
@Builder
public class WednesdaySermons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // idx 값

    @NotNull
    @Column(length = 1000, nullable = false)
    private String title; // 제목

    @NotNull
    @Column(length = 1000)
    private String mainText; // 내용

    @Column(length = 1000, nullable = false)
    private String user_id; // 글쓴이

    @Temporal(TemporalType.DATE)
    private Date write_date; // 작성날짜

    @Column
    private Integer views;

    @Column(length = 1000, nullable = false)
    private String youtube_id; // youtubeID


    @Column(nullable = false, columnDefinition = "boolean default 0")
    private boolean is_delete; // 삭제여부

    public WednesdaySermons(String title, String mainText, String user_id, Date write_date, boolean is_delete, Integer views) {
        this.title = title;
        this.mainText = mainText;
        this.user_id = user_id;
        this.write_date = write_date;
        this.is_delete = is_delete;
        this.views = views;
    }

    public WednesdaySermons() {
    }
}