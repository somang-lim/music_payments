package com.ll.exam.music_payments.app.cash.entity;

import com.ll.exam.music_payments.app.base.entity.BaseEntity;
import com.ll.exam.music_payments.app.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CashLog extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member member;
    private long price; // 변동
    private String eventType;
}
