package pl.sda.arppl4.car.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///

    private String clientName;
    private String clientSurname;
    private Double price;

    @CreationTimestamp
    private LocalDate rentDateTime;
    private LocalDate returnDateTime;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car car;

    public CarRental(String clientName, String clientSurname, Double price, LocalDate rentDateTime, Car car) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.price = price;
        this.rentDateTime = rentDateTime;
        this.car = car;
    }
}
