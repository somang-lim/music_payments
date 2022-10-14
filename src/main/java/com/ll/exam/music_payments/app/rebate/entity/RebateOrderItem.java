package com.ll.exam.music_payments.app.rebate.entity;

import com.ll.exam.music_payments.app.base.entity.BaseEntity;
import com.ll.exam.music_payments.app.cash.entity.CashLog;
import com.ll.exam.music_payments.app.member.entity.Member;
import com.ll.exam.music_payments.app.order.entity.Order;
import com.ll.exam.music_payments.app.order.entity.OrderItem;
import com.ll.exam.music_payments.app.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import java.time.LocalDateTime;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class RebateOrderItem extends BaseEntity {
    @OneToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private OrderItem orderItem;

    @OneToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Product product;

    // 가격
    private int price; // 권장판매가
    private int salePrice; // 실제판매가
    private int wholesalePrice; // 도매가
    private int pgFee; // 결제대행사 수수료
    private int payPrice; // 결제금액
    private int refundPrice; // 환불금액
    private boolean isPaid; // 결제여부
    private LocalDateTime payDate; // 결제날짜

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private CashLog rebateCashLog; // 정산에 관련된 환급지급내역
    private LocalDateTime rebateDate;

    // 상품
    private String productSubject;

    // 주문날짜
    private LocalDateTime orderItemCreateDate;

    // 구매자 회원
    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member buyer;
    private String buyerName;

    // 판매자 회원
    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member seller;
    private String sellerName;

    public RebateOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        order = orderItem.getOrder();
        product = orderItem.getProduct();
        price = orderItem.getPrice();
        salePrice = orderItem.getSalePrice();
        wholesalePrice = orderItem.getWholesalePrice();
        pgFee = orderItem.getPgFee();
        payPrice = orderItem.getPayPrice();
        refundPrice = orderItem.getRefundPrice();
        isPaid = orderItem.isPaid();
        payDate = orderItem.getPayDate();

        // 상품 추가데이터
        productSubject = orderItem.getProduct().getSubject();

        // 주문날짜
        orderItemCreateDate = orderItem.getCreateDate();

        // 구매자 추가데이터
        buyer = orderItem.getOrder().getBuyer();
        buyerName = orderItem.getOrder().getBuyer().getName();

        // 판매자 추가데이터
        seller = orderItem.getProduct().getAuthor();
        sellerName = orderItem.getProduct().getAuthor().getName();
    }

    public int calculateRebatePrice() {
        if (refundPrice > 0) {
            return 0;
        }

        return payPrice - pgFee - wholesalePrice;
    }

    public boolean isRebateAvailable() {
        if (refundPrice > 0) {
            return false;
        }

        return true;
    }
}
