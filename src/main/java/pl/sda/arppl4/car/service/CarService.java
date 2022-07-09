package pl.sda.arppl4.car.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sda.arppl4.car.model.Car;
import pl.sda.arppl4.car.model.CarRental;
import pl.sda.arppl4.car.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public void deleteById(Long identyfikator) {
        carRepository.deleteById(identyfikator);
    }

    public void updateCar(Long id, Car daneAktualizujące) {
        Long identifier = daneAktualizujące.getId(); // identyfikator obiektu edytowanego

        log.info("Auto id danych aktualizujacych " + identifier);

        Optional<Car> carOptional = carRepository.findById(identifier);
        if (carOptional.isPresent()) {
            Car editedCar = carOptional.get();
            log.info("Auto edytowane " + editedCar);


            if (daneAktualizujące.getName() != null) {
                // zastąp name jeśli dane aktualizujące zawierają 'name'
                editedCar.setName(daneAktualizujące.getName());
                log.info("Nazwa zmieniana na " + daneAktualizujące.getName());
            }
            if (daneAktualizujące.getCarGearBox() != null) {

                editedCar.setCarGearBox(daneAktualizujące.getCarGearBox());
            }
            if (daneAktualizujące.getEngineCapacity() != null) {
                editedCar.setEngineCapacity(daneAktualizujące.getEngineCapacity());
            }

            if (daneAktualizujące.getProductionDate() != null) {
                editedCar.setProductionDate(daneAktualizujące.getProductionDate());
            }
            if (daneAktualizujące.getSeats() != null) {
                editedCar.setSeats(daneAktualizujące.getSeats());
            }

            carRepository.save(editedCar);
            log.info("Samochód został zapisany.");
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono auta o id: " + daneAktualizujące.getId());
    }

    public Optional<Car> findByIdCar(Long identyfikator) {
        return carRepository.findById(identyfikator);
    }



}

