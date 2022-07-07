package pl.sda.arppl4.car.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.car.model.Car;
import pl.sda.arppl4.car.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    // READ
    @GetMapping("/list")
    public List<Car> getAllCar() {
        log.info("Wywołano listę samochodów.");
        List<Car> list = carService.getAllCar();
        return list;
    }

    // CREATE
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car) {
        log.info("Wywołano dodanie auta: " + car);
        carService.addCar(car);
    }

    // DELETE
    @DeleteMapping("/delete/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable(name = "identifier") Long identyfikator) {
        log.info("Wywołano usunięcie auta: " + identyfikator);
        carService.deleteById(identyfikator);
    }

    @PatchMapping("/update{identifier}")
    public void updateCar(@PathVariable(name = "identifier")@RequestBody Car car) {
        log.info("Wywołano aktualizację auta: " + car);
        carService.updateCar(car);
    }

}
