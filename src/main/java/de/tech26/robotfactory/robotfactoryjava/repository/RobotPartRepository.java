package de.tech26.robotfactory.robotfactoryjava.repository;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.entities.RobotPart;
import de.tech26.robotfactory.robotfactoryjava.enums.TypeOfPartEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class RobotPartRepository {

    RobotPart robotPartA = new RobotPart("A", "Humanoid Face", 9, 10.28, TypeOfPartEnum.FACE );
    RobotPart robotPartB = new RobotPart("B","LCD Face", 7, 24.07, TypeOfPartEnum.FACE );
    RobotPart robotPartC = new RobotPart("C","Steampunk Face", 0, 13.30, TypeOfPartEnum.FACE );
    RobotPart robotPartD = new RobotPart("D", "Arms with Hands", 1, 28.94, TypeOfPartEnum.ARMS );
    RobotPart robotPartE = new RobotPart("E","Arms with Grippers",3,  12.39, TypeOfPartEnum.ARMS );
    RobotPart robotPartF = new RobotPart("F", "Mobility with Wheels",2,30.77, TypeOfPartEnum.MOBILITY );
    RobotPart robotPartG = new RobotPart("G", "Mobility with Legs",15,  55.13,  TypeOfPartEnum.MOBILITY);
    RobotPart robotPartH = new RobotPart("H","Mobility with Tracks",7,  50.00, TypeOfPartEnum.MOBILITY );
    RobotPart robotPartI = new RobotPart("I", "Material Bioplastic",92,  90.12, TypeOfPartEnum.MATERIAL );
    RobotPart robotPartJ = new RobotPart("J","Material Metallic", 15, 82.31,  TypeOfPartEnum.MATERIAL );

    ArrayList<RobotPart> robotParts = new ArrayList<>(){{
        add(robotPartA);
        add(robotPartB);
        add(robotPartC);
        add(robotPartE);
        add(robotPartD);
        add(robotPartF);
        add(robotPartG);
        add(robotPartH);
        add(robotPartI);
        add(robotPartJ);
    }
    };


    public List<RobotPart> findPartsInOrderRequest(OrderRequest orderRequest) {
        List<String> componentsIds = orderRequest.getComponentsId();
        List<RobotPart> robotPartsInOrderRequest = new ArrayList<>();
        for (String id: componentsIds) {
            for(RobotPart r: robotParts){
                if(r.getId().equals(id)){
                    robotPartsInOrderRequest.add(r);
                }
            }
        }
        return robotPartsInOrderRequest;
    }


}
