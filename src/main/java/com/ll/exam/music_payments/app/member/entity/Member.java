package com.ll.exam.music_payments.app.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.music_payments.app.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean emailVerified;
    private long restCash;

    public String getName() {
        return username;
    }

    public Member(long id) {
        super(id);
    }
}
