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
import java.util.List;

@ToString
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends TimeEntity {
    private String login_id;

    private String pw;

    private Date birth_date;

    private String gender;

    private String phone_num;

    @OneToMany(mappedBy = "user")
    private List<Cocktail> cocktails;
}
