package nl.novi.cardemo.services;

import nl.novi.cardemo.dtos.CarResponseDTO;
import nl.novi.cardemo.models.Car;
import nl.novi.cardemo.repositories.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){

        this.carRepository = carRepository;
    }
    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Car> getCars(
             String brand,
             String model)
    {
        List<Car> cars;

        if (brand != null && model != null) {
            cars = carRepository.findByBrandAndModel(brand, model);
        } else if (brand != null) {
            cars = carRepository.findByBrand(brand);
        } else if (model != null) {
            cars = carRepository.findByModel(model);
        } else {
            cars = carRepository.findAll();
        }
        //filter alle auto eruit die ouder zijn dan 13 jaar

        return cars;
    }
}
