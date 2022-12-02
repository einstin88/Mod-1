package main;

import java.util.Date;

import models.Cars;

public class Main {
    public static void main(String[] args) {
        Cars myCar = new Cars("black");

        // myCar.setColor("red");
        myCar.setModel("Honda");
        myCar.setRegistration("SXX");
        myCar.setRegistrationDate(new Date());

        myCar.accelerate();
        myCar.accelerate();
        myCar.accelerate();

        System.out.println("My " + myCar.getColour() + " " +
                myCar.getModel() + " is " + myCar.getRegistration() +
                " and at acceleration " + myCar.getAcceleration() 
                );
    }
}
