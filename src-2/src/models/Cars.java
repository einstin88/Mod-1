package models;

import java.util.Date;
import java.util.List;

public class Cars {
    /*
     * ATTRIBUTES / MEMBERS
     */
    private String colour;
    private String model;
    private String registration;
    private Date registrationDate;
    private List<String> permittedModels = List.of("Honda", "Toyota", "Mazda");

    private int acceleration = 0;


    /*
     * CONSTRUCTOR
     */
    public Cars(String colour) {
        this.colour = colour;
    }

     
    /*
     * METHODS
     */
    public String getColour() {
        return this.colour;
    }
    
    public void setColor(String colour) {
        this.colour = colour;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        if (permittedModels.contains(model)) {
            this.model = model;
        }
    }

    public String getRegistration() {
        return this.registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public int getAcceleration() {
        return acceleration;
    }

    public void accelerate() {
        if (this.acceleration < 200) {
            this.acceleration++;
        }
    }

    public void decelerate() {
        this.acceleration--;
    }
}
