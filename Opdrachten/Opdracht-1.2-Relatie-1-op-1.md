## Opdracht: Stap 1 - Maak de `CarRegistration` Klasse

### Doel van de opdracht:
In deze stap ga je een nieuwe klasse genaamd `CarRegistration` aanmaken. Deze klasse zal de gegevens van de registratie van een auto bijhouden, zoals het kenteken en de datum van registratie. De klasse moet een relatie met de `Car` entiteit hebben, waarbij `CarRegistration` de eigenaar is van de relatie.

### Wat je moet doen:
1. Maak de `CarRegistration` klasse aan in de map `models`.
2. Voeg de volgende velden toe aan de klasse:
    - `id`: Het unieke identificatienummer van de registratie (type `Long`).
    - `plateNumber`: Het kenteken van de auto (type `String`).
    - `registrationDate`: De datum waarop de auto geregistreerd werd (type `LocalDate`).

3. Zorg ervoor dat de `CarRegistration` klasse:
    - Correct wordt gemarkeerd als een JPA-entiteit door de annotatie `@Entity`.
    - De tabelnaam wordt gedefinieerd als `car_registrations` door de annotatie `@Table(name = "car_registrations")`.
    - De primaire sleutel (`id`) wordt geannoteerd met `@Id` en `@GeneratedValue(strategy = GenerationType.IDENTITY)`.

4. Maak een `OneToOne` relatie tussen `CarRegistration` en `Car`:
    - Voeg een `@OneToOne` relatie toe in de `CarRegistration` klasse naar de `Car` entiteit.
    - Gebruik de annotatie `@JoinColumn(name = "car_id")` om de kolom te definiëren die de relatie tussen de registratie en de auto vastlegt. De `car_id` zal verwijzen naar de primaire sleutel van de `Car` entiteit.

### Vereisten:
- De klasse moet een constructor bevatten voor het initialiseren van de velden.
- De klasse moet getter- en setter-methoden bevatten voor alle velden.
- Zorg ervoor dat je alle benodigde import statements toevoegt.

### Verwachte structuur van de `CarRegistration` klasse:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "car_registrations")
public class CarRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // De primaire sleutel van de registratie
    private String plateNumber; // Het kenteken van de auto
    private LocalDate registrationDate; // De datum waarop de registratie plaatsvond

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id") // Koppelt de registratie aan een auto via de primaire sleutel van de auto
    private Car car; // Verwijzing naar de auto die deze registratie heeft
    
    // Constructor
    public CarRegistration() {}

    public CarRegistration(String plateNumber, LocalDate registrationDate, Car car) {
        this.plateNumber = plateNumber;
        this.registrationDate = registrationDate;
        this.car = car;
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
```

</details>

### Tips:
- Zorg ervoor dat je de juiste import statements toevoegt, zoals `import jakarta.persistence.*;` voor JPA-annotaties en `import java.time.LocalDate;` voor het `LocalDate` type.
- Let erop dat de `@JoinColumn(name = "car_id")` verwijst naar de `id` van de `Car` entiteit. Zorg ervoor dat de `Car` entiteit correct is opgezet met de juiste annotaties.

## Opdracht: Stap 2 - Maak de DTO's voor `CarRegistration`

### Doel van de opdracht:
In deze stap ga je de benodigde DTO's (Data Transfer Objects) aanmaken voor de `CarRegistration` entiteit. Deze DTO's zullen gebruikt worden voor het aanmaken en bijwerken van een `CarRegistration`, evenals voor het retourneren van een respons na succesvolle operaties.

### Wat je moet doen:
1. Maak een `CarRegistrationCreateDTO` klasse aan voor het aanmaken van een nieuwe `CarRegistration`.
2. Maak een `CarRegistrationUpdateDTO` klasse aan voor het bijwerken van een bestaande `CarRegistration`.
3. Maak een `CarRegistrationResponseDTO` klasse aan voor het retourneren van de gegevens van een `CarRegistration` na een succesvolle operatie.

### 1. CarRegistrationCreateDTO:
Deze DTO wordt gebruikt om de gegevens te ontvangen voor het aanmaken van een nieuwe `CarRegistration`. Het moet de noodzakelijke velden bevatten, zoals `plateNumber` en `registrationDate`, die nodig zijn om een nieuwe registratie aan te maken.

### 2. CarRegistrationUpdateDTO:
Deze DTO wordt gebruikt voor het bijwerken van een bestaande `CarRegistration`. Het bevat dezelfde velden als `CarRegistrationCreateDTO`, maar de data kan ook worden aangepast.

### 3. CarRegistrationResponseDTO:
Deze DTO wordt gebruikt om de gegevens van een bestaande `CarRegistration` terug te sturen na het succesvol aanmaken of bijwerken van een registratie. Het moet het `id`, `plateNumber`, `registrationDate`, en het gerelateerde `Car` object bevatten.

### Wat moet je doen?
1. Maak drie nieuwe klassen aan in de map `dtos`:
   - CarRegistrationCreateDTO
   - CarRegistrationUpdateDTO
   - CarRegistrationResponseDTO

2. Voeg de nodige velden en getters/setters toe aan deze DTO's.
3. Zorg ervoor dat de DTO's correct worden gemarkeerd als eenvoudige POJO-klassen, zonder dat ze JPA-annotaties nodig hebben.

### Verwachte structuur van de DTO's:

<details>
<summary>Klik hier voor de uitwerking van de DTO's</summary>

CarRegistrationCreateDTO.java:
```java
package nl.novi.cardemo.dtos;

import java.time.LocalDate;

public class CarRegistrationCreateDTO {
    private String plateNumber;
    private LocalDate registrationDate;

    // Getters en Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
```

CarRegistrationUpdateDTO.java:
```java
package nl.novi.cardemo.dtos;

import java.time.LocalDate;

public class CarRegistrationUpdateDTO {
    private String plateNumber;
    private LocalDate registrationDate;

    // Getters en Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
```

CarRegistrationResponseDTO.java:
```java
package nl.novi.cardemo.dtos;

import java.time.LocalDate;

public class CarRegistrationResponseDTO {
    private Long id;
    private String plateNumber;
    private LocalDate registrationDate;

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
```

</details>

### Tips:
- Zorg ervoor dat je alleen de noodzakelijke velden in de DTO's opneemt. De DTO's moeten het minimale aantal velden bevatten dat nodig is voor de respectieve operatie (aanmaken, bijwerken, respons).
- Gebruik `LocalDate` voor het `registrationDate` veld, aangezien de registratie een datum betreft.
- Houd de DTO's eenvoudig; ze moeten geen businesslogica bevatten, maar alleen de velden die nodig zijn voor de communicatie tussen de controller en de service.

## Opdracht: Stap 3 - Maak de Mapper voor `CarRegistration`

### Doel van de opdracht:
In deze stap ga je een mapper aanmaken voor de `CarRegistration` entiteit. De mapper wordt gebruikt om tussen de `CarRegistration` entiteit en de bijbehorende DTO's (`CarRegistrationCreateDTO`, `CarRegistrationUpdateDTO`, `CarRegistrationResponseDTO`) te converteren. Dit zorgt ervoor dat de data op de juiste manier tussen lagen wordt omgezet, zodat je deze kunt gebruiken in je service en controller.

### Wat je moet doen:
1. Maak een nieuwe klasse `CarRegistrationMapper` aan in de map `mappers`.
2. Voeg de volgende methoden toe aan de `CarRegistrationMapper` klasse:
   - `toResponseDTO(CarRegistration carRegistration)`: Zet een `CarRegistration` entiteit om naar een `CarRegistrationResponseDTO`.
   - `toEntity(CarRegistrationCreateDTO carRegistrationCreateDTO)`: Zet een `CarRegistrationCreateDTO` om naar een `CarRegistration` entiteit.
   - `toEntity(CarRegistrationUpdateDTO carRegistrationUpdateDTO)`: Zet een `CarRegistrationUpdateDTO` om naar een `CarRegistration` entiteit.
   - `toResponseDTOList(List<CarRegistration> carRegistrations)`: Zet een lijst van `CarRegistration` entiteiten om naar een lijst van `CarRegistrationResponseDTO` objecten.

### Verwachte structuur van de `CarRegistrationMapper` klasse:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.mappers;

import nl.novi.cardemo.dtos.CarRegistrationCreateDTO;
import nl.novi.cardemo.dtos.CarRegistrationResponseDTO;
import nl.novi.cardemo.dtos.CarRegistrationUpdateDTO;
import nl.novi.cardemo.models.CarRegistration;

import java.util.List;
import java.util.stream.Collectors;

public class CarRegistrationMapper {

    // Zet een CarRegistration entiteit om naar een CarRegistrationResponseDTO
    public static CarRegistrationResponseDTO toResponseDTO(CarRegistration carRegistration) {
        CarRegistrationResponseDTO dto = new CarRegistrationResponseDTO();
        dto.setId(carRegistration.getId());
        dto.setPlateNumber(carRegistration.getPlateNumber());
        dto.setRegistrationDate(carRegistration.getRegistrationDate());
        return dto;
    }

    // Zet een CarRegistrationCreateDTO om naar een CarRegistration entiteit
    public static CarRegistration toEntity(CarRegistrationCreateDTO carRegistrationCreateDTO) {
        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setPlateNumber(carRegistrationCreateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationCreateDTO.getRegistrationDate());
        return carRegistration;
    }

    // Zet een CarRegistrationUpdateDTO om naar een CarRegistration entiteit
    public static CarRegistration toEntity(CarRegistrationUpdateDTO carRegistrationUpdateDTO) {
        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setPlateNumber(carRegistrationUpdateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationUpdateDTO.getRegistrationDate());
        return carRegistration;
    }

    // Zet een lijst van CarRegistration entiteiten om naar een lijst van CarRegistrationResponseDTO's
    public static List<CarRegistrationResponseDTO> toResponseDTOList(List<CarRegistration> carRegistrations) {
        return carRegistrations.stream()
                .map(CarRegistrationMapper::toResponseDTO) // Zet elke CarRegistration om naar een CarRegistrationResponseDTO
                .collect(Collectors.toList());
    }
}
```

</details>

### Tips:
- Zorg ervoor dat de methoden in de mapper goed gescheiden zijn: een voor het converteren van entiteit naar DTO, en een andere voor het converteren van DTO naar entiteit.
- Maak gebruik van Java 8 Streams en `Collectors.toList()` om een lijst van entiteiten om te zetten naar een lijst van DTO's.
- De `toEntity` methoden voor `CarRegistrationCreateDTO` en `CarRegistrationUpdateDTO` zijn bijna hetzelfde, omdat de velden in de DTO's vergelijkbaar zijn. De enige wijziging zou kunnen zijn dat je de DTO voor een update zou kunnen gebruiken om specifieke velden in een bestaande entiteit bij te werken.

## Opdracht: Stap 4 - Maak de Service en Repository voor `CarRegistration` (met koppeling naar Auto)

### Doel van de opdracht:
In deze stap ga je de **repository** en **service** voor de `CarRegistration` entiteit aanmaken. De repository is verantwoordelijk voor de interactie met de database, terwijl de service de businesslogica bevat. De service moet de koppeling tussen `CarRegistration` en `Car` beheren door gebruik te maken van DTO's en mappers.

De service moet:
1. Controleren of een auto (`Car`) met een specifiek ID (`carId`) bestaat.
2. De registratie (`CarRegistration`) koppelen aan de auto.
3. De benodigde operaties uitvoeren voor het aanmaken, bijwerken, ophalen, en verwijderen van registraties.


### Wat je moet doen:
1. **Maak de `CarRegistrationRepository`**:
    - Zorg ervoor dat de repository de basis CRUD-operaties ondersteunt.
    - Voeg eventueel custom queries toe voor specifieke operaties, zoals het ophalen van registraties voor een specifieke auto.

2. **Maak de `CarRegistrationService`**:
    - Verwerk de logica om een nieuwe registratie aan te maken, bij te werken, of te verwijderen, inclusief de koppeling aan een specifieke auto.
    - Gebruik DTO's en mappers om de data te verwerken.


### 1. Maak de `CarRegistrationRepository`

De repository is verantwoordelijk voor de interactie met de database via Spring Data JPA. Zorg ervoor dat de repository de basisoperaties ondersteunt.

#### Wat je moet doen:
- Maak een interface genaamd `CarRegistrationRepository`.
- Laat de interface uitbreiden van `JpaRepository` voor basisfunctionaliteit.
- Voeg een methode toe om registraties op te halen voor een specifieke auto en registratie Id.

#### Verwachte structuur van de repository:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.repositories;

import nl.novi.cardemo.models.CarRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRegistrationRepository extends JpaRepository<CarRegistration, Long> {
    // Query om registraties op te halen voor een specifieke auto
    Optional<CarRegistration> findByIdAndCarId(Long Id,Long carId);
}
```

</details>


### 2. Maak de `CarRegistrationService`

De service bevat de logica om de `CarRegistration` entiteit te beheren. Dit omvat:
1. Het aanmaken van een nieuwe registratie die aan een auto is gekoppeld.
2. Het bijwerken van een bestaande registratie en de koppeling met de auto.
3. Het ophalen van registraties op basis van ID.
4. Het verwijderen van registraties.

#### Wat je moet doen:
- Maak een klasse genaamd `CarRegistrationService`.
- Gebruik de repository om interacties met de database af te handelen.
- Voeg methoden toe voor CRUD-operaties:
    - **`createCarRegistration`**: Voor het aanmaken van een nieuwe registratie.
    - **`updateCarRegistration`**: Voor het bijwerken van een bestaande registratie.
    - **`getCarRegistration`**: Voor het ophalen van een registratie op basis van ID.
    - **`deleteCarRegistration`**: Voor het verwijderen van een registratie.

#### Verwachte structuur van de service:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java

package nl.novi.cardemo.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.cardemo.dtos.CarRegistrationCreateDTO;
import nl.novi.cardemo.dtos.CarRegistrationResponseDTO;
import nl.novi.cardemo.dtos.CarRegistrationUpdateDTO;
import nl.novi.cardemo.models.Car;
import nl.novi.cardemo.models.CarRegistration;
import nl.novi.cardemo.repositories.CarRegistrationRepository;
import nl.novi.cardemo.repositories.CarRepository;
import nl.novi.cardemo.mappers.CarRegistrationMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRegistrationService {

    private final CarRegistrationRepository carRegistrationRepository;
    private final CarRepository carRepository;

    public CarRegistrationService(CarRegistrationRepository carRegistrationRepository, CarRepository carRepository) {
        this.carRegistrationRepository = carRegistrationRepository;
        this.carRepository = carRepository;
    }

    // Methode om een nieuwe CarRegistration aan te maken
    public CarRegistrationResponseDTO createCarRegistration(Long carId, CarRegistrationCreateDTO carRegistrationCreateDTO) {
        // Controleer of de Car bestaat
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Zet DTO om naar entiteit en koppel de Car
        CarRegistration carRegistration = CarRegistrationMapper.toEntity(carRegistrationCreateDTO);
        carRegistration.setCar(car);

        // Sla de registratie op
        CarRegistration savedCarRegistration = carRegistrationRepository.save(carRegistration);

        // Zet de opgeslagen registratie om naar ResponseDTO
        return CarRegistrationMapper.toResponseDTO(savedCarRegistration);
    }

    // Methode om een bestaande CarRegistration bij te werken
    public CarRegistrationResponseDTO updateCarRegistration(Long carId, Long registrationId, CarRegistrationUpdateDTO carRegistrationUpdateDTO) {
        // Haal de bestaande registratie op
        CarRegistration carRegistration = carRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("CarRegistration not found with id " + registrationId));

        // Controleer of de Car bestaat
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + carId));

        // Update de registratie en koppel de juiste Car
        carRegistration.setPlateNumber(carRegistrationUpdateDTO.getPlateNumber());
        carRegistration.setRegistrationDate(carRegistrationUpdateDTO.getRegistrationDate());
        carRegistration.setCar(car);

        // Sla de bijgewerkte registratie op
        CarRegistration updatedCarRegistration = carRegistrationRepository.save(carRegistration);

        // Zet de registratie om naar ResponseDTO
        return CarRegistrationMapper.toResponseDTO(updatedCarRegistration);
    }

    // Methode om een CarRegistration op te halen
    public CarRegistrationResponseDTO getCarRegistration(Long carId, Long registrationId) {
        CarRegistration carRegistration = carRegistrationRepository.findByIdAndCarId( registrationId, carId)
                .orElseThrow(() -> new EntityNotFoundException("CarRegistration not found with id " + registrationId));

        return CarRegistrationMapper.toResponseDTO(carRegistration);
    }

    // Methode om een CarRegistration te verwijderen
    public boolean deleteCarRegistration(Long carId, Long registrationId) {
        if (carRegistrationRepository.existsById(registrationId)) {
            carRegistrationRepository.deleteById(registrationId);
            return true;
        }
        return false;
    }
}


```

</details>

### Uitleg van de service methoden:
1. **`createCarRegistration`**:
    - Controleert of de auto bestaat via `CarRepository`.
    - Zet de `CarRegistrationCreateDTO` om naar een entiteit en koppelt deze aan de auto.
    - Slaat de registratie op en retourneert een `CarRegistrationResponseDTO`.

2. **`updateCarRegistration`**:
    - Haalt een bestaande registratie op via het registratie-id.
    - Controleert of de auto bestaat.
    - Werkt de registratie bij en koppelt deze aan de auto.
    - Slaat de bijgewerkte registratie op en retourneert een `CarRegistrationResponseDTO`.

3. **`getCarRegistration`**:
    - Haalt een registratie op via het registratie-id en retourneert een `CarRegistrationResponseDTO`.

4. **`deleteCarRegistration`**:
    - Verwijdert een registratie op basis van het registratie-id.

## Opdracht: Stap 5 - Maak de Controller voor `CarRegistration`

### Doel van de opdracht:
In deze stap ga je de **controller** voor de `CarRegistration` entiteit aanmaken met de API-structuur die werkt via de URL `/cars/{id}/carregistrations`. De controller moet gebruik maken van de bestaande service- en mapper-implementaties. Het doel is om de `CarRegistration` entiteit volledig te beheren, gekoppeld aan een specifieke auto (`Car`).

---

### Wat je moet doen:
1. Maak een **`CarRegistrationController`** klasse aan in de map `controllers`, die werkt met de API-structuur `/cars/{id}/carregistrations`.
2. Zorg ervoor dat de controller de volgende acties uitvoert:
    - **Aanmaken van een nieuwe registratie** (`POST /cars/{id}/carregistrations`).
    - **Ophalen van een specifieke registratie** (`GET /cars/{id}/carregistrations/{registrationId}`).
    - **Bijwerken van een registratie** (`PUT /cars/{id}/carregistrations/{registrationId}`).
    - **Verwijderen van een registratie** (`DELETE /cars/{id}/carregistrations/{registrationId}`).

De businesslogica moet volledig in de service worden afgehandeld. De controller roept de service aan om de benodigde acties uit te voeren.

---

### Verwachte structuur van de controller:

#### **`POST /cars/{id}/carregistrations`**:
- Maakt een nieuwe registratie aan voor een specifieke auto (`Car`) op basis van het auto-id (`carId`).
- Ontvangt de gegevens via een `CarRegistrationCreateDTO`.
- Roept de service aan om de registratie aan te maken en koppelt deze aan de auto.
- Retourneert een `CarRegistrationResponseDTO` met de details van de aangemaakte registratie.

#### **`GET /cars/{id}/carregistrations/{registrationId}`**:
- Haalt een specifieke registratie op via het registratie-id (`registrationId`) en auto-id (`carId`).
- Roept de service aan om de registratie op te halen.
- Retourneert een `CarRegistrationResponseDTO` met de details van de opgehaalde registratie.

#### **`PUT /cars/{id}/carregistrations/{registrationId}`**:
- Werkt een bestaande registratie bij voor een specifieke auto (`Car`) op basis van het registratie-id (`registrationId`) en auto-id (`carId`).
- Ontvangt de gegevens via een `CarRegistrationUpdateDTO`.
- Roept de service aan om de registratie bij te werken.
- Retourneert een `CarRegistrationResponseDTO` met de bijgewerkte details van de registratie.

#### **`DELETE /cars/{id}/carregistrations/{registrationId}`**:
- Verwijdert een bestaande registratie op basis van het registratie-id (`registrationId`) en auto-id (`carId`).
- Roept de service aan om de registratie te verwijderen.
- Retourneert een `204 No Content` status als de registratie succesvol is verwijderd.

---

#### **Voorbeeldcode voor de controller**:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.controllers;

import nl.novi.cardemo.dtos.CarRegistrationCreateDTO;
import nl.novi.cardemo.dtos.CarRegistrationResponseDTO;
import nl.novi.cardemo.dtos.CarRegistrationUpdateDTO;
import nl.novi.cardemo.services.CarRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars/{carId}/carregistrations")
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    // Endpoint om een nieuwe CarRegistration aan te maken voor een specifieke Car
    @PostMapping
    public ResponseEntity<CarRegistrationResponseDTO> createCarRegistration(
            @PathVariable Long carId,
            @RequestBody CarRegistrationCreateDTO carRegistrationCreateDTO) {

        // Roep de service aan om de registratie aan te maken
        CarRegistrationResponseDTO responseDTO = carRegistrationService.createCarRegistration(carId, carRegistrationCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Endpoint om een specifieke CarRegistration op te halen
    @GetMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> getCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        // Roep de service aan om de registratie op te halen
        CarRegistrationResponseDTO responseDTO = carRegistrationService.getCarRegistration(carId, registrationId);

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint om een CarRegistration bij te werken, gekoppeld aan een specifieke Car
    @PutMapping("/{registrationId}")
    public ResponseEntity<CarRegistrationResponseDTO> updateCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId,
            @RequestBody CarRegistrationUpdateDTO carRegistrationUpdateDTO) {

        // Roep de service aan om de registratie bij te werken
        CarRegistrationResponseDTO responseDTO = carRegistrationService.updateCarRegistration(carId, registrationId, carRegistrationUpdateDTO);

        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint om een CarRegistration te verwijderen
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteCarRegistration(
            @PathVariable Long carId, @PathVariable Long registrationId) {

        // Roep de service aan om de registratie te verwijderen
        boolean isDeleted = carRegistrationService.deleteCarRegistration(carId, registrationId);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Succesvol verwijderd
        } else {
            return ResponseEntity.notFound().build(); // Niet gevonden
        }
    }
}
```

</details>

---

### Uitleg van de controller:
- **`POST /cars/{id}/carregistrations`**:
    - Ontvangt een `CarRegistrationCreateDTO` in de body van het verzoek.
    - Het `carId` wordt uit de URL gehaald met behulp van `@PathVariable`.
    - Roept de `createCarRegistration` methode van de service aan.
    - Retourneert een `CarRegistrationResponseDTO` met status `201 Created`.

- **`GET /cars/{id}/carregistrations/{registrationId}`**:
    - Ontvangt het `carId` en `registrationId` uit de URL met `@PathVariable`.
    - Roept de `getCarRegistration` methode van de service aan.
    - Retourneert een `CarRegistrationResponseDTO` met status `200 OK`.

- **`PUT /cars/{id}/carregistrations/{registrationId}`**:
    - Ontvangt een `CarRegistrationUpdateDTO` in de body van het verzoek.
    - Ontvangt het `carId` en `registrationId` uit de URL met `@PathVariable`.
    - Roept de `updateCarRegistration` methode van de service aan.
    - Retourneert een `CarRegistrationResponseDTO` met status `200 OK`.

- **`DELETE /cars/{id}/carregistrations/{registrationId}`**:
    - Ontvangt het `carId` en `registrationId` uit de URL met `@PathVariable`.
    - Roept de `deleteCarRegistration` methode van de service aan.
    - Retourneert een status `204 No Content` bij succesvolle verwijdering of `404 Not Found` als de registratie niet bestaat.

---

### Tips:
1. **Gebruik van de service**:
    - Verplaats alle businesslogica naar de service.
    - Laat de controller alleen verantwoordelijk zijn voor het ontvangen van verzoeken en het aanroepen van de juiste service-methoden.
2. **Consistente API-structuur**:
    - Gebruik `@RequestMapping` en `@PathVariable` correct om een duidelijke en consistente API-structuur te garanderen.
3. **HTTP-statuscodes**:
    - Gebruik de juiste HTTP-statuscodes (`201 Created`, `200 OK`, `204 No Content`, `404 Not Found`) afhankelijk van de uitkomst van de operaties.

