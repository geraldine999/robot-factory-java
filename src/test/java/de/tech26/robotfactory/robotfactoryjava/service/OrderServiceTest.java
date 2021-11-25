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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        //When
        orderService.updateStock(robotPartsList);
        //Then
        assertEquals(14, RobotPartsEnum.G.getStock());
        assertEquals(6, RobotPartsEnum.H.getStock());
    }

    @Test
    public void shouldCreateANewOrderResponseWithCorrectOrderIdAndTotal(){
        OrderRequest orderRequest= new OrderRequest(RobotFactoryDataTestUtils.getWellConfiguredOrderRequestListExample2());
        OrderResponse orderResponseExpected = new OrderResponse(0, 143.56);
        assertThat(orderResponseExpected).isEqualToComparingFieldByField(orderService.createOrder(orderRequest));

    }

    @Test
    public void shouldNotThrowAnyExceptionWhenOrderRequestListIsValid(){
        List<RobotPartsEnum> robotPartsList = RobotFactoryDataTestUtils.getWellConfiguredOrderRequestListExample();
        assertDoesNotThrow(()->{
            orderService.validateOrder(robotPartsList);
        });
    }


    @Test
    public void shouldThrowAMoreThanOneOptionForATypeOfPartExceptionWhenOrderRequestSpecifiesMoreThanOptionForABodyPart(){
        List<RobotPartsEnum> robotPartsList = RobotFactoryDataTestUtils.getMoreThanOneOptionForATypeOfPartOrderRequestListExample();
        Exception exception = assertThrows(MoreThanOneOptionForATypeOfPartException.class, () -> {
            orderService.validateOrder(robotPartsList);
        });

        String expectedMessage = "The order specifies more than one option for a body part. It should" +
                " only contain ONE option for each part: face, material, arms and mobility ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldThrowARanOutOfStockExceptionWhenARobotPartsStockIsZero(){
        List<RobotPartsEnum> robotPartsList = RobotFactoryDataTestUtils.getAnOrderRequestListWithARobotPartThatHasRanOutOfStock();
        Exception exception = assertThrows(RanOutOfStockException.class, () -> {
            orderService.validateOrder(robotPartsList);
        });

        String expectedMessage = "We have unfortunately ran out of stock for "+ RobotPartsEnum.C.getName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldThrowAMorePartsExceptionWhenAmountOfRobotPartsInOrderRequestIsMoreThan4(){
        List<RobotPartsEnum> robotPartsList = RobotFactoryDataTestUtils.getMoreThan4RobotPartsOrderRequestListExample();
        Exception exception = assertThrows(MorePartsException.class, () -> {
            orderService.validateOrder(robotPartsList);
        });

        String expectedMessage = "The order specifies more robot parts than needed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void shouldThrowANotEnoughPartsExceptionWhenAmountOfRobotPartsInOrderRequestIsLessThan4(){
        List<RobotPartsEnum> robotPartsList  = RobotFactoryDataTestUtils.getLessThan4RobotPartsOrderRequestListExample();
        Exception exception = assertThrows(NotEnoughPartsException.class, () -> {
            orderService.validateOrder(robotPartsList);
        });

        String expectedMessage = "The order does not specify enough parts to build an entire robot";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void shouldReturnTotalPrice(){
        List<RobotPartsEnum> robotPartsList =  RobotFactoryDataTestUtils.getWellConfiguredOrderRequestListExample();
        assertEquals(160.11,orderService.calculateTotalPrice(robotPartsList));
    }


    @BeforeEach
    public void resetStock(){
        RobotPartsEnum.A.setStock(9);
        RobotPartsEnum.B.setStock(7);
        RobotPartsEnum.C.setStock(0);
        RobotPartsEnum.D.setStock(1);
        RobotPartsEnum.E.setStock(3);
        RobotPartsEnum.F.setStock(2);
        RobotPartsEnum.G.setStock(15);
        RobotPartsEnum.H.setStock(7);
        RobotPartsEnum.I.setStock(92);
        RobotPartsEnum.J.setStock(15);

    }



}
