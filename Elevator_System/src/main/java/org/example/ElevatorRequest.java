package org.example;

public class ElevatorRequest implements ElevatorCommand{
    private final int elevatorID;
    private final int floor;
    private final Direction requestDirection;
    private final boolean isInternalRequest;
    private final ElevatorController controller;
    public ElevatorRequest(int elevatorID , int floor , Direction requestDirection , boolean isInternalRequest){
        this.controller = new ElevatorController();
        this.elevatorID = elevatorID;
        this.requestDirection = requestDirection;
        this.isInternalRequest = isInternalRequest;
        this.floor = floor;
    }
    @Override
    public void execute() {
           if(isInternalRequest){
               controller.requestFloor(elevatorID , floor);

           }else{
               controller.requestElevator(elevatorID,floor,requestDirection);
           }
    }

    public Direction getRequestDirection(){
        return requestDirection;
    }
    public int getElevatorID(){
        return elevatorID;
    }
    public int getFloor(){
        return  floor;
    }

    public  boolean isInternalRequest(){
        return isInternalRequest;
    }


}
