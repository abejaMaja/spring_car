package pl.sda.arppl4.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.car.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
