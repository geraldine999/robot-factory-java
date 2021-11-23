package de.tech26.robotfactory.robotfactoryjava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.service.OrderService;
import de.tech26.robotfactory.robotfactoryjava.utils.RobotFactoryDataTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderControllerTest {
    //ORDER CONTROLLER UNIT TESTING

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;


    @Test
    public void testCreateOrder() throws Exception {

        OrderRequest orderRequest = RobotFactoryDataTestUtils.getWellConfiguredOrderRequestExample();
        Mockito.when(this.orderService.createOrder(orderRequest)).thenReturn(null);
        String content = (new ObjectMapper()).writeValueAsString(orderRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.orderController).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);


        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }


}

