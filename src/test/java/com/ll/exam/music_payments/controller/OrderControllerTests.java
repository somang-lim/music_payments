package com.ll.exam.music_payments.controller;

import com.ll.exam.music_payments.app.order.controller.OrderController;
import com.ll.exam.music_payments.app.song.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class OrderControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SongService songService;

    @Test
    @DisplayName("주문 상세화면")
    @WithUserDetails("user2")
    void t1() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(get("/order/3")
                )
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(OrderController.class))
                .andExpect(handler().methodName("showDetail"))
                .andExpect(content().string(containsString("주문")));
    }
}
