package de.tech26.robotfactory.robotfactoryjava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;
import de.tech26.robotfactory.robotfactoryjava.exceptions.MorePartsException;
import de.tech26.robotfactory.robotfactoryjava.exceptions.MoreThanOneOptionForATypeOfPartException;
import de.tech26.robotfactory.robotfactoryjava.exceptions.NotEnoughPartsException;
import de.tech26.robotfactory.robotfactoryjava.exceptions.RanOutOfStockException;
import de.tech26.robotfactory.robotfactoryjava.utils.RobotFactoryDataTestUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {
    //ORDER SERVICE UNIT TESTING

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldUpdateAllRobotPartsStock(){
        //Given
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.G);
        robotPartsList.add(RobotPartsEnum.H);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        //When
        orderService.calculateTotalPriceAndUpdateStock(orderRequest);
        //Then
        assertEquals(14, RobotPartsEnum.G.getStock());
        assertEquals(6, RobotPartsEnum.H.getStock());
    }

    @Test
    public void shouldCreateANewOrderResponseEntityWithStatusCode201AndCorrectOrderIdAndTotal(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getWellConfiguredOrderRequestExample2();
        OrderResponse orderResponseExpected = new OrderResponse(0, 143.56);
        ResponseEntity<OrderResponse> orderResponseEntityExpected = new ResponseEntity<>(orderResponseExpected, HttpStatus.CREATED);
        assertThat(orderResponseExpected).isEqualToComparingFieldByField(orderService.createOrder(orderRequest).getBody());
        assertThat(orderResponseEntityExpected.getStatusCode()).isEqualTo(orderService.createOrder(orderRequest).getStatusCode());

    }

    @Test
    public void shouldReturnTrueWhenOrderRequestIsValid(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getWellConfiguredOrderRequestExample();
        assertTrue(orderService.orderIsValid(orderRequest));
    }


    @Test
    public void shouldThrowAMoreThanOneOptionForATypeOfPartExceptionWhenOrderRequestSpecifiesMoreThanOptionForABodyPart(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getMoreThanOneOptionForATypeOfPartOrderRequestExample();
        Exception exception = assertThrows(MoreThanOneOptionForATypeOfPartException.class, () -> {
            orderService.orderIsValid(orderRequest);
        });

        String expectedMessage = "The order specifies more than one option for a body part. It should" +
                " only contain ONE option for each part: face, material, arms and mobility ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldThrowARanOutOfStockExceptionWhenARobotPartsStockIsZero(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getAnOrderRequestWithARobotPartThatHasRanOutOfStock();
        Exception exception = assertThrows(RanOutOfStockException.class, () -> {
            orderService.orderIsValid(orderRequest);
        });

        String expectedMessage = "We have unfortunately ran out of stock for "+ RobotPartsEnum.C.getName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldThrowAMorePartsExceptionWhenAmountOfRobotPartsInOrderRequestIsMoreThan4(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getMoreThan4RobotPartsOrderRequestExample();
        Exception exception = assertThrows(MorePartsException.class, () -> {
            orderService.orderIsValid(orderRequest);
        });

        String expectedMessage = "The order specifies more robot parts than needed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void shouldThrowANotEnoughPartsExceptionWhenAmountOfRobotPartsInOrderRequestIsLessThan4(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getLessThan4RobotPartsOrderRequestExample();
        Exception exception = assertThrows(NotEnoughPartsException.class, () -> {
            orderService.orderIsValid(orderRequest);
        });

        String expectedMessage = "The order does not specify enough parts to build an entire robot";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldReturnTotalPrice(){
        OrderRequest orderRequest = RobotFactoryDataTestUtils.getWellConfiguredOrderRequestExample();
        assertEquals(160.11,orderService.calculateTotalPriceAndUpdateStock(orderRequest));
    }



}
