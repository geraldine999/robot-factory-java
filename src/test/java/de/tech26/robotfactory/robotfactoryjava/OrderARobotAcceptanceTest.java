package de.tech26.robotfactory.robotfactoryjava;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;
import de.tech26.robotfactory.robotfactoryjava.exceptions.RobotFactoryExceptionResponse;
import de.tech26.robotfactory.robotfactoryjava.utils.RobotFactoryDataTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderARobotAcceptanceTest {
    //INTEGRATION TESTING

    @Autowired
    private TestRestTemplate testRestTemplate;


    @DisplayName("Should order a robot.")
    @Test
    public void shouldCreateOrderResponseEntityStatusCreatedWhenOrdersEndpointIsCalledWithAWellConfiguredOrderRequest(){
        OrderRequest orderRequest = new OrderRequest(RobotFactoryDataTestUtils.getWellConfiguredOrderRequestListExample());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderRequest> orderRequestHttpEntity = new HttpEntity<>(orderRequest, httpHeaders);

        ResponseEntity<OrderResponse> orderResponse = testRestTemplate.postForEntity("/orders", orderRequestHttpEntity, OrderResponse.class);

        assertEquals(HttpStatus.CREATED, orderResponse.getStatusCode());
        assertNotNull(orderResponse.getBody());
        assertEquals(0,orderResponse.getBody().getOrderId());
        assertEquals(160.11, orderResponse.getBody().getTotal());
    }

    @DisplayName("Should not allow an order with more than one option for a body part.")
    @Test
    public void shouldThrowAMoreThanOneOptionForABodyPartException(){
        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = postOrderExpectAnException(new OrderRequest(RobotFactoryDataTestUtils.getMoreThanOneOptionForATypeOfPartOrderRequestListExample()));
        String expectedMessage = "The order specifies more than one option for a body part. It should" +
                " only contain ONE option for each part: face, material, arms and mobility ";

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exceptionResponse.getStatusCode());
        assertNotNull(exceptionResponse.getBody());
        assertEquals(expectedMessage,exceptionResponse.getBody().getMessage());
        assertEquals("uri=/orders", exceptionResponse.getBody().getDetails());

    }

    @DisplayName("Should not allow an empty order.")
    @Test
    public void shouldThrowANullPointerException(){
        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = postOrderExpectAnException(new OrderRequest(null));
        String expectedMessage = "Order cannot be empty";

        assertEquals(HttpStatus.BAD_REQUEST, exceptionResponse.getStatusCode());
        assertNotNull(exceptionResponse.getBody());
        assertEquals(expectedMessage,exceptionResponse.getBody().getMessage());
        assertEquals("uri=/orders", exceptionResponse.getBody().getDetails());

    }

    @DisplayName("Should not allow an order with more than 4 robot parts.")
    @Test
    public void shouldThrowAMorePartsException(){
        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = postOrderExpectAnException(new OrderRequest(RobotFactoryDataTestUtils.getMoreThan4RobotPartsOrderRequestListExample()));
        String expectedMessage = "The order specifies more robot parts than needed";

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exceptionResponse.getStatusCode());
        assertNotNull(exceptionResponse.getBody());
        assertEquals(expectedMessage,exceptionResponse.getBody().getMessage());
        assertEquals("uri=/orders", exceptionResponse.getBody().getDetails());

    }

    @DisplayName("Should not allow an order with less than 4 robot parts.")
    @Test
    public void shouldThrowANotEnoughPartsException(){
        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = postOrderExpectAnException(new OrderRequest(RobotFactoryDataTestUtils.getLessThan4RobotPartsOrderRequestListExample()));
        String expectedMessage = "The order does not specify enough parts to build an entire robot";

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exceptionResponse.getStatusCode());
        assertNotNull(exceptionResponse.getBody());
        assertEquals(expectedMessage,exceptionResponse.getBody().getMessage());
        assertEquals("uri=/orders", exceptionResponse.getBody().getDetails());

    }

    @DisplayName("Should inform when a robot part is out of stock.")
    @Test
    public void shouldThrowARanOutOfStockException(){
        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = postOrderExpectAnException(new OrderRequest(RobotFactoryDataTestUtils.getAnOrderRequestListWithARobotPartThatHasRanOutOfStock()));
        String expectedMessage = "We have unfortunately ran out of stock for "+ RobotPartsEnum.C.getName();

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exceptionResponse.getStatusCode());
        assertNotNull(exceptionResponse.getBody());
        assertEquals(expectedMessage,exceptionResponse.getBody().getMessage());
        assertEquals("uri=/orders", exceptionResponse.getBody().getDetails());

    }



    private ResponseEntity<RobotFactoryExceptionResponse> postOrderExpectAnException(OrderRequest orderRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderRequest> orderRequestHttpEntity = new HttpEntity<>(orderRequest, httpHeaders);

        ResponseEntity<RobotFactoryExceptionResponse> exceptionResponse = testRestTemplate.postForEntity("/orders", orderRequestHttpEntity, RobotFactoryExceptionResponse.class);
        return exceptionResponse;

    }
}
