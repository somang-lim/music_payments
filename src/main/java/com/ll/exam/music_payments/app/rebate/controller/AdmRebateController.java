package com.ll.exam.music_payments.app.rebate.controller;

import com.ll.exam.music_payments.app.base.dto.RsData;
import com.ll.exam.music_payments.app.rebate.entity.RebateOrderItem;
import com.ll.exam.music_payments.app.rebate.service.RebateService;
import com.ll.exam.music_payments.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/adm/rebate")
@RequiredArgsConstructor
public class AdmRebateController {
    private final RebateService rebateService;

    @GetMapping("/makeData")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showMakeData() {
        return "adm/rebate/makeData";
    }

    @PostMapping("/makeData")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String makeData(String yearMonth) {
        rebateService.makeDate(yearMonth);

        return "redirect:/adm/rebate/rebateOrderItemList?yearMonth=" + yearMonth + "&msg=" + Ut.url.encode("정산데이터가 성공적으로<br>생성되었습니다.");
    }

    @GetMapping("/rebateOrderItemList")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showRebateOrderItemList(String yearMonth, Model model) {
        if (yearMonth == null) {
            yearMonth = "2022-10";
        }

        List<RebateOrderItem> items = rebateService.findRebateOrderItemByPayDateIn(yearMonth);

        model.addAttribute("items", items);

        return "adm/rebate/rebateOrderItemList";
    }

    @PostMapping("/rebateOne/{orderItemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public String rebateOne(@PathVariable long orderItemId) {
        RsData rebateRsData = rebateService.rebate(orderItemId);

        return rebateRsData.getMsg();
    }
}
