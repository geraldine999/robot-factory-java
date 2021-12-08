package de.tech26.robotfactory.robotfactoryjava.service;

import de.tech26.robotfactory.robotfactoryjava.repository.RobotPartRepository;
import de.tech26.robotfactory.robotfactoryjava.entities.RobotPart;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.enums.TypeOfPartEnum;
import de.tech26.robotfactory.robotfactoryjava.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final RobotPartRepository robotPartRepository;


    @Autowired
    public OrderService(RobotPartRepository robotPartRepository) {
        this.robotPartRepository = robotPartRepository;
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        List<RobotPart> robotPartsList = robotPartRepository.findPartsInOrderRequest(orderRequest);
        validateOrder(robotPartsList);
        double totalPrice = calculateTotalPrice(robotPartsList);
        updateStock(robotPartsList);
        return new OrderResponse(createOrderId(), totalPrice);
    }

    private String createOrderId(){
        return  UUID.randomUUID().toString();
    }

    private void validateOrder(List<RobotPart> robotPartsList) {
        int amountOfRobotParts = robotPartsList.size();
        if (amountOfRobotParts < 4) {
            throw new NotEnoughPartsException("The order does not specify enough parts to build an entire robot");
        }
        if (amountOfRobotParts > 4) {
            throw new MorePartsException("The order specifies more robot parts than needed");
        }
        List<TypeOfPartEnum> bodyPartsCheckList = new ArrayList<>();
        for (RobotPart part : robotPartsList) {
            if (part.getStock() == 0) {
                throw new RanOutOfStockException("We have unfortunately ran out of stock for " + part.getName());
            }
            if (bodyPartsCheckList.contains(part.getTypeOfPart())) {
                throw new MoreThanOneOptionForATypeOfPartException("The order specifies more than one option for a body part. It should" +
                        " only contain ONE option for each part: face, material, arms and mobility ");
            } else {
                bodyPartsCheckList.add(part.getTypeOfPart());
            }
        }
    }

    private double calculateTotalPrice(List<RobotPart> robotPartsList) {
        return robotPartsList.stream().mapToDouble(RobotPart::getPrice).sum();
    }

    private void updateStock(List<RobotPart> robotPartsList){
       for (RobotPart robotPart : robotPartsList) {
            int previousStock = robotPart.getStock();
            robotPart.setStock(previousStock - 1);
        }
    }



}
