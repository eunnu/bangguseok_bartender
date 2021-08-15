package com.cocktail.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class TimeEntity extends BaseEntity {

    @CreatedDate
    @Column(name = "create_date")
    private Timestamp createdDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private Timestamp updatedDate;
}
