package com.cocktail.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@ToString
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String login_id;

    private String pw;

    private Date birth_date;

    private String gender;

    private String phone_num;

    @CreatedDate
    @Column(name = "create_date")
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private Timestamp updatedDate;
}
