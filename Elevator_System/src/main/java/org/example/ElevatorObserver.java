package org.example;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator , ElevatorState elevatorState);
    void onElevatorFloorChange(Elevator elevator , int floor);
}
