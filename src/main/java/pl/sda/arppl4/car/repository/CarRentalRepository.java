package pl.sda.arppl4.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.car.model.Car;
import pl.sda.arppl4.car.model.CarRental;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
}
