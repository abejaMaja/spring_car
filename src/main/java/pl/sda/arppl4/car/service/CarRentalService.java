package pl.sda.arppl4.car.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.car.exception.CarNotAvailableException;
import pl.sda.arppl4.car.model.Car;
import pl.sda.arppl4.car.model.CarRental;
import pl.sda.arppl4.car.repository.CarRentalRepository;
import pl.sda.arppl4.car.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;
    private final CarRepository carRepository;

    public List<Car> getAllAvailableCars() {
        List<Car> carList = carRepository.findAll();

        List<Car> cars = new ArrayList<>();
        for (Car car : carList) {
            if (!isRented(car)) {
                // samochód jest dostępny, zwróć go...
                cars.add(car);
            }
        }

        return cars;
    }




    /**
     * Metoda sprawdza czy dany samochód jest wynajęty. Jeśli jego data zwrotu (dowolnego najmu)
     * jest równa null, to samochód jest wynajęty.
     *
     * @param car - sprawdzany samochód.
     * @return informacja czy samochód jest wynajęty (true/false).
     */
    private boolean isRented(Car car) {
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getReturnDateTime() == null) {
                return true;
            }
        }
        return false;
    }


    public void rentCar(Long carId, CarRental carRental) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if(optionalCar.isPresent()){
            Car carToRent = optionalCar.get();

            if(!isRented(carToRent)) {
                CarRental stworzonyNowyWnajem= new CarRental();
                stworzonyNowyWnajem.setClientName(carRental.getClientName());
                stworzonyNowyWnajem.setClientSurname(carRental.getClientSurname());
                stworzonyNowyWnajem.setPrice(carRental.getPrice());
                stworzonyNowyWnajem.setCar(carToRent);

                carRentalRepository.save(stworzonyNowyWnajem);
                return;

            }
            throw new CarNotAvailableException("Car not available, id " + carId);
        }
        throw new EntityNotFoundException("Unable to find your id");
    }
}

