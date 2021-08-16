package com.cocktail.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @Column(name = "user_name", unique = true)
    private String name;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updatedDate;

    private String login_id;

    private String pw;

    private Date birth_date;

    private String gender;

    private String phone_num;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cocktail> cocktails;
}
