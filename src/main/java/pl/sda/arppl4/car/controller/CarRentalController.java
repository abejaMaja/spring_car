package pl.sda.arppl4.car.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.car.model.Car;
import pl.sda.arppl4.car.model.CarRental;
import pl.sda.arppl4.car.service.CarRentalService;
import pl.sda.arppl4.car.service.CarService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        log.info("Requested rental available cars");
        return carRentalService.getAllAvailableCars();
    }

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    public void rentCar(@RequestParam Long carId, @RequestBody CarRental carRental){

        log.info("Wywołano wynajem auta i id " + carId);
        carRentalService.rentCar(carId, carRental);
    }


}
