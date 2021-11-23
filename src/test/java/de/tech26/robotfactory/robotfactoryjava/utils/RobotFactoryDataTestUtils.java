package de.tech26.robotfactory.robotfactoryjava.utils;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;

import java.util.ArrayList;
import java.util.List;

public class RobotFactoryDataTestUtils {

    public static OrderRequest getWellConfiguredOrderRequestExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.F);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

    public static OrderRequest getWellConfiguredOrderRequestExample2(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.E);
        robotPartsList.add(RobotPartsEnum.F);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

    public static OrderRequest getMoreThanOneOptionForATypeOfPartOrderRequestExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.A); //face body part
        robotPartsList.add(RobotPartsEnum.B); //face body part
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.D);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

    public static OrderRequest getMoreThan4RobotPartsOrderRequestExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.F);
        robotPartsList.add(RobotPartsEnum.H);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

    public static OrderRequest getLessThan4RobotPartsOrderRequestExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

    public static OrderRequest getAnOrderRequestWithARobotPartThatHasRanOutOfStock(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.C); //ran out of stock
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.H);
        OrderRequest orderRequest = new OrderRequest(robotPartsList);
        return orderRequest;
    }

}
