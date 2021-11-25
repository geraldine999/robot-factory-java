package de.tech26.robotfactory.robotfactoryjava.service;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.enums.TypeOfPartEnum;
import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;
import de.tech26.robotfactory.robotfactoryjava.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    int order_id;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        List<RobotPartsEnum> robotPartsList = orderRequest.getComponents();
        validateOrder(robotPartsList);
        double totalPrice = calculateTotalPrice(robotPartsList);
        updateStock(robotPartsList);
        return new OrderResponse(this.order_id++, totalPrice);
    }

    public void validateOrder(List<RobotPartsEnum> robotPartsList) {
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
    }

    public double calculateTotalPrice(List<RobotPartsEnum> robotPartsList) {
        double totalPrice = 0;
        for (RobotPartsEnum robotPart : robotPartsList) {
            totalPrice += robotPart.getPrice();
        }
        return totalPrice;
    }

    public void updateStock(List<RobotPartsEnum> robotPartsList){
        for (RobotPartsEnum robotPart : robotPartsList) {
            int previousStock = robotPart.getStock();
            robotPart.setStock(previousStock - 1);
        }
    }



}
