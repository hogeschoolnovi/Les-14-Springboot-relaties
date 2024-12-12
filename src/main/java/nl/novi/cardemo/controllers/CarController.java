package nl.novi.cardemo.controllers;

import jakarta.validation.Valid;
import nl.novi.cardemo.dtos.CarCreateDTO;
import nl.novi.cardemo.dtos.CarResponseDTO;
import nl.novi.cardemo.mappers.CarMapper;
import nl.novi.cardemo.models.Car;
import nl.novi.cardemo.repositories.CarRepository;
import nl.novi.cardemo.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller: CarController
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CarCreateDTO carCreateDTO, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors()); // Fouten teruggeven
        }

        Car car=CarMapper.toEntity(carCreateDTO); // Conversie van DTO naar Entity
        Car savedCar=carService.save(car); // Opslaan van de Car in de database
        return ResponseEntity.status(HttpStatus.CREATED).body(CarMapper.toResponseDTO(savedCar)); // Respons
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> carOptional = carService.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setBrand(carDetails.getBrand());
            car.setModel(carDetails.getModel());
            Car updatedCar = carService.save(car);
            return ResponseEntity.ok(CarMapper.toResponseDTO(updatedCar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        var result = carService.delete(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model) {

        return ResponseEntity.ok(CarMapper.toResponseDTOList(carService.getCars(brand,model)));
    }
}
