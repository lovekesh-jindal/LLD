package org.example;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private List<ElevatorObserver> observers;
    private Queue<ElevatorRequest> requests;


          public int getId() {return id;}
          public int getCurrentFloor() {return currentFloor;}
          public Direction getDirection() {return direction;}
          public ElevatorState getState() {return state;}

          public Queue<ElevatorRequest> getRequestsQueue() {
            return new LinkedList<>(requests);
         }

          public List<ElevatorRequest> getDestinationFloors() {
            return new ArrayList<>(requests);
         }
}
