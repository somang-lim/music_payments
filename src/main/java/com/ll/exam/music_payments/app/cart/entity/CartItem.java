package com.ll.exam.music_payments.app.cart.entity;

import com.ll.exam.music_payments.app.base.entity.BaseEntity;
import com.ll.exam.music_payments.app.member.entity.Member;
import com.ll.exam.music_payments.app.product.entity.Product;
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
public class CartItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member buyer;
    @ManyToOne(fetch = LAZY)
    private Product product;
}
