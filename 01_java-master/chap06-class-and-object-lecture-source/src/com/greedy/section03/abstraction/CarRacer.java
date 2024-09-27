package com.greedy.section03.abstraction;

public class CarRacer {

	private Car car = new Car();
	
	public CarRacer() {}
	
	public void startUp() {
		car.starUp();
	}

	public void stepAccelator() {
		car.go();
	}

	public void stepBreak() {
		car.stop();
	}

	public void turnOff() {
		car.turnOff();
	}

}
