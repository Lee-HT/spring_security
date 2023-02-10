package com.lagrange.infi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class createdE {
    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private LocalDateTime createAt;

    @LastModifiedDate
    @NotNull
    private LocalDateTime updateAt;


}
