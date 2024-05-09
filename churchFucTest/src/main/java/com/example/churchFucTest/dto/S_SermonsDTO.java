package com.example.churchFucTest.dto;

import com.example.churchFucTest.domain.SundaySermons;
import lombok.Getter;

import java.sql.Date;

@Getter
public class S_SermonsDTO {

    private Long id;
    private String title;
    private String mainText;
    private String user_id;
    private Date write_date; // 작성날짜
    private Integer views;
    private boolean is_delete; // 삭제여부

    public S_SermonsDTO(SundaySermons entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.mainText = entity.getMainText();
        this.user_id = entity.getUser_id();
        this.write_date = entity.getWrite_date();
        this.views = entity.getViews();
    }


}
