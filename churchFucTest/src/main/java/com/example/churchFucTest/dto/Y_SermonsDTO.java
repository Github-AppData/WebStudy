package com.example.churchFucTest.dto;

import com.example.churchFucTest.domain.WednesdaySermons;
import com.example.churchFucTest.domain.YouthSermons;
import lombok.Getter;

import java.sql.Date;

@Getter
public class Y_SermonsDTO {

    private Long id;
    private String title;
    private String content;
    private String user_id;
    private Date write_date; // 작성날짜
    private Integer like_num;
    private Integer views;
    private boolean is_delete; // 삭제여부

    public Y_SermonsDTO(YouthSermons entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.user_id = entity.getUser_id();
        this.like_num = entity.getLike_num();
        this.write_date = entity.getWrite_date();
        this.views = entity.getViews();
    }


}
