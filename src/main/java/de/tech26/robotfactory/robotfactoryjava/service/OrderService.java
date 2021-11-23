package de.tech26.robotfactory.robotfactoryjava.service;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.enums.TypeOfPartEnum;
import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;
import de.tech26.robotfactory.robotfactoryjava.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    int order_id;

    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        if(orderIsValid(orderRequest)){
            double totalPrice = calculateTotalPriceAndUpdateStock(orderRequest);
            OrderResponse orderResponse = new OrderResponse(this.order_id++, totalPrice);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        }
        return null;
    }

    public boolean orderIsValid(OrderRequest orderRequest) {
        List<RobotPartsEnum> robotPartsList = orderRequest.getComponents();
        int amountOfRobotParts = robotPartsList.size();
        if (amountOfRobotParts < 4) {
            throw new NotEnoughPartsException("The order does not specify enough parts to build an entire robot");
        }
        if (amountOfRobotParts > 4) {
            throw new MorePartsException("The order specifies more robot parts than needed");
        }
        List<TypeOfPartEnum> bodyPartsCheckList = new ArrayList<>();
        for (RobotPartsEnum part : robotPartsList) {
            if (part.getStock() == 0) {
                throw new RanOutOfStockException("We have unfortunately ran out of stock for " + part.getName());
            }
            if (bodyPartsCheckList.contains(part.getPart())) {
                throw new MoreThanOneOptionForATypeOfPartException("The order specifies more than one option for a body part. It should" +
                            " only contain ONE option for each part: face, material, arms and mobility ");
            } else {
                bodyPartsCheckList.add(part.getPart());
            }

        }

        return true;

    }

    public double calculateTotalPriceAndUpdateStock(OrderRequest orderRequest) {
        List<RobotPartsEnum> robotParts = orderRequest.getComponents();
        double totalPrice = 0;
        for (RobotPartsEnum robotPart : robotParts) {
            totalPrice += robotPart.getPrice();
            int previousStock = robotPart.getStock();
            robotPart.setStock(previousStock - 1);
        }
        return totalPrice;
    }


}
