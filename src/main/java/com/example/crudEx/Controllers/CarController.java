package com.example.crudEx.Controllers;

import com.example.crudEx.Entities.Car;
import com.example.crudEx.Entities.CarTypes;
import com.example.crudEx.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok().body(savedCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok().body(carRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(name = "id") Long id) {
//        if(carRepository.existsById(id)) {
//            return ResponseEntity.ok().body(carRepository.findById(id).get());
//        } else {
//            return ResponseEntity.ok().body(new Car());
//        }

        return carRepository.findById(id).map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") Long id, @RequestParam CarTypes carType) {
//        if(carRepository.existsById(id)) {
//            Car foundCar = carRepository.findById(id).get();
//            foundCar.setCarType(carType);
//            Car updatedCar = carRepository.save(foundCar);
//            return ResponseEntity.ok().body(updatedCar);
//        } else {
//            return ResponseEntity.notFound().build();
//        }

                return carRepository.findById(id)
                .map(car -> {
                    car.setCarType(carType);
                    Car updatedCar = carRepository.save(car);
                    return ResponseEntity.ok().body(updatedCar);})
                        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCars() {
        carRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
