package com.ll.exam.music_payments.app.cash.repository;

import com.ll.exam.music_payments.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashLogRepository extends JpaRepository<CashLog, Long> {
}
