## Opdracht: Stap 1 - Maak de `Accessory` Klasse en de Veel-op-Veel Relatie met `Car`

### Doel van de opdracht:
In deze stap ga je een nieuwe klasse genaamd `Accessory` aanmaken. Deze klasse houdt gegevens bij over accessoires die aan auto's kunnen worden toegevoegd. Er is een **"veel-op-veel" relatie** tussen `Car` en `Accessory`, omdat één auto meerdere accessoires kan hebben en een accessoire aan meerdere auto's gekoppeld kan worden.

---

### Wat je moet doen:
1. **Maak de `Accessory` klasse**:
    - Voeg velden toe voor de unieke identificatie en eigenschappen van een accessoire.
    - Maak de klasse correct aan als een JPA-entiteit.

2. **Definieer de Veel-op-Veel Relatie**:
    - Implementeer de **"veel-op-veel" relatie** tussen `Car` en `Accessory` in beide klassen.
    - Gebruik een **join-tabel** om de relatie te beheren.

---

### 1. Maak de `Accessory` Klasse

De `Accessory` klasse moet de volgende velden bevatten:
- **`id`**: Het unieke identificatienummer van het accessoire (type `Long`).
- **`name`**: De naam van het accessoire (type `String`).
- **`price`**: De prijs van het accessoire (type `Double`).

De klasse moet:
- Correct worden gemarkeerd als een JPA-entiteit door de annotatie `@Entity`.
- Een tabelnaam hebben gedefinieerd als `accessories` met de annotatie `@Table(name = "accessories")`.
- Een primaire sleutel (`id`) hebben met `@Id` en `@GeneratedValue(strategy = GenerationType.IDENTITY)`.

---

### 2. Definieer de Veel-op-Veel Relatie

De relatie tussen `Car` en `Accessory` moet worden gedefinieerd in beide klassen:
1. Voeg een **`@ManyToMany`** annotatie toe aan beide klassen.
2. Gebruik de annotatie **`@JoinTable`** in één van de klassen om de join-tabel te configureren. De join-tabel verbindt de primaire sleutels van `Car` en `Accessory`.

---

### Verwachte structuur van de `Accessory` Klasse

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "accessories")
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // De primaire sleutel van het accessoire
    private String name; // De naam van het accessoire
    private Double price; // De prijs van het accessoire

    @ManyToMany(mappedBy = "accessories") // Omgekeerde kant van de veel-op-veel relatie
    private List<Car> cars;

    // Constructor
    public Accessory() {}

    public Accessory(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
```

</details>

---

### Verwachte Aanpassing van de `Car` Klasse

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // De primaire sleutel van de auto
    private String brand; // Het merk van de auto
    private String model; // Het model van de auto
    private int year; // Het bouwjaar van de auto

    @ManyToMany
    @JoinTable(
        name = "car_accessories", // Naam van de join-tabel
        joinColumns = @JoinColumn(name = "car_id"), // Kolom die verwijst naar de Car
        inverseJoinColumns = @JoinColumn(name = "accessory_id") // Kolom die verwijst naar de Accessory
    )
    private List<Accessory> accessories; // Lijst van accessoires die aan deze auto gekoppeld zijn

    // Constructor
    public Car() {}

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }
}
```

</details>

---

### Tips:
1. **Annotaties**:
    - Gebruik `@ManyToMany` in beide klassen en configureer de join-tabel in één van de klassen.
2. **Join-tabel**:
    - Zorg ervoor dat de join-tabel correct wordt genoemd (`car_accessories`), met de juiste kolommen (`car_id` en `accessory_id`) om de relatie tussen auto's en accessoires vast te leggen.
3. **Omgekeerde relatie**:
    - Gebruik `mappedBy` in de `Accessory` klasse om de relatie in beide richtingen te kunnen navigeren.