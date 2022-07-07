package pl.sda.arppl4.car.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data // Getter Setter ToString EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    ///
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///

    private String name;
    private String make;
    private LocalDate productionDate;

    //  @Enumerated(EnumType.STRING)
    //  private CarBodyType bodyType;

    private Integer seats;

    @Enumerated(EnumType.STRING)
    private CarGearBox carGearBox;

    private Double engineCapacity;


    // @EqualsAndHashCode.Exclude
    // private Set<CarRental> carRentals;

    public Car(String name, String make, LocalDate productionDate, Integer seats, Double engineCapacity, CarGearBox carGearBox) {
        this.name = name;
        this.make = make;
        this.productionDate = productionDate;
        this.carGearBox = carGearBox;
        this.seats = seats;
        this.engineCapacity = engineCapacity;
    }
}
