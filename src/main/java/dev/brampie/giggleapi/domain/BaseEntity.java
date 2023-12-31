package dev.brampie.giggleapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    protected String id;

    @CreatedBy
    protected String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "tr_TR", timezone = "GMT+3")
    @CreatedDate
    protected Date createdDate;

    @LastModifiedBy
    protected String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "tr_TR", timezone = "GMT+3")
    @LastModifiedDate
    protected Date updatedDate;

    @Column
    protected boolean status = true;
}
