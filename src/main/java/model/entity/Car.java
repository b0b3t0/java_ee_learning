package model.entity;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;

@Entity
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private String year;
    private Engines engines;
    private User user;

    public Car() {

    }

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(nullable = false)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Engines getEngines() {
        return engines;
    }

    public void setEngines(Engines engines) {
        this.engines = engines;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
