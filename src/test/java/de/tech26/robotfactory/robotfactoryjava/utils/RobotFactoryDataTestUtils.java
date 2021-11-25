package de.tech26.robotfactory.robotfactoryjava.utils;

import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;

import java.util.ArrayList;
import java.util.List;

public class RobotFactoryDataTestUtils {

    public static List<RobotPartsEnum> getWellConfiguredOrderRequestListExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.F);
        return robotPartsList;
    }

    public static List<RobotPartsEnum> getWellConfiguredOrderRequestListExample2(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.E);
        robotPartsList.add(RobotPartsEnum.F);
        return robotPartsList;
    }

    public static  List<RobotPartsEnum> getMoreThanOneOptionForATypeOfPartOrderRequestListExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.A); //face body part
        robotPartsList.add(RobotPartsEnum.B); //face body part
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.D);
        return robotPartsList;
    }

    public static List<RobotPartsEnum> getMoreThan4RobotPartsOrderRequestListExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.F);
        robotPartsList.add(RobotPartsEnum.H);
        return robotPartsList;
    }

    public static List<RobotPartsEnum> getLessThan4RobotPartsOrderRequestListExample(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.A);
        robotPartsList.add(RobotPartsEnum.D);
        return robotPartsList;
    }

    public static List<RobotPartsEnum> getAnOrderRequestListWithARobotPartThatHasRanOutOfStock(){
        List<RobotPartsEnum> robotPartsList = new ArrayList<>();
        robotPartsList.add(RobotPartsEnum.I);
        robotPartsList.add(RobotPartsEnum.C); //ran out of stock
        robotPartsList.add(RobotPartsEnum.D);
        robotPartsList.add(RobotPartsEnum.H);
        return robotPartsList;
    }

}
