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

          public Elevator(int id){
              this.id = id;
              this.currentFloor = 1;
              this.direction = Direction.IDLE;
              this.state = ElevatorState.IDLE;
              this.observers = new ArrayList<>();
              this.requests = new LinkedList<>();
          }
          public void addObserver(ElevatorObserver observer){
              observers.add(observer);
          }
          public void removeObserver(ElevatorObserver observer){
              observers.remove(observer);
          }
          public void notifyStateChange(ElevatorState state){
              for(ElevatorObserver observer : observers){
                  observer.onElevatorStateChange(this,state);
              }
          }
          public void  notifyFloorChange(int floor){
              for(ElevatorObserver observer : observers){
                  observer.onElevatorFloorChange(this,floor);
              }
          }

          public void setState(ElevatorState state){
              this.state = state;
              notifyStateChange(state);
          }
          public void setDirection( Direction direction){
              this.direction = direction;
          }
          public void addRequest(ElevatorRequest request){
              if(!requests.contains(request)){
                  requests.add(request);
              }
              int requestedFloor = request.getFloor();
              if(state == ElevatorState.IDLE && !requests.isEmpty()){
                  if(requestedFloor > currentFloor){
                      direction = Direction.UP;
                  }else if(requestedFloor < currentFloor){
                      direction = Direction.DOWN;
                  }
                  setState(ElevatorState.MOVING);
              }
          }

    public void moveToNextStop(int nextStop) {
        // Only move if the elevator is currently in the MOVING state
        if (state != ElevatorState.MOVING)
            return;
        while (currentFloor != nextStop) {
            // Update floor based on direction
            if (direction == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            // Notify observers about the floor change
            notifyFloorChange(currentFloor);
            // Complete arrival once the target floor is reached
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }
    private void completeArrival() {
        // Stop the elevator and notify observers
        setState(ElevatorState.STOPPED);
        // Remove the current floor from the requests queue
        requests.removeIf(request -> request.getFloor() == currentFloor);
        // If no more requests, set state to IDLE
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            // Otherwise, continue moving after a brief stop
            setState(ElevatorState.MOVING);
        }
    }


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
