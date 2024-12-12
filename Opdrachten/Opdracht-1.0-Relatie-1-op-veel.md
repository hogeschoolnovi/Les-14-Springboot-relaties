## Opdracht:  Maak de `RepairInvoice` Klasse

### Doel van de opdracht:
In deze stap ga je een nieuwe klasse genaamd `RepairInvoice` aanmaken. Deze klasse zal de gegevens van een reparatienota bijhouden, zoals het reparatienummer, de datum van de reparatie en de totale kosten. De klasse moet een **"één-op-veel" relatie** hebben met de `Car` entiteit, waarbij één auto (`Car`) meerdere reparatienota's (`RepairInvoice`) kan hebben.

### Wat je moet doen:
1. Maak de `RepairInvoice` klasse aan in de map `models`.
2. Voeg de volgende velden toe aan de klasse:
    - `id`: Het unieke identificatienummer van de reparatienota (type `Long`).
    - `repairDate`: De datum waarop de reparatie plaatsvond (type `LocalDate`).
    - `totalCost`: De totale kosten van de reparatie (type `Double`).

3. Zorg ervoor dat de `RepairInvoice` klasse:
    - Correct wordt gemarkeerd als een JPA-entiteit door de annotatie `@Entity`.
    - De tabelnaam wordt gedefinieerd als `repair_invoices` door de annotatie `@Table(name = "repair_invoices")`.
    - De primaire sleutel (`id`) wordt geannoteerd met `@Id` en `@GeneratedValue(strategy = GenerationType.IDENTITY)`.

4. Maak een `ManyToOne` relatie tussen `RepairInvoice` en `Car`:
    - Voeg een `@ManyToOne` relatie toe in de `RepairInvoice` klasse naar de `Car` entiteit.
    - Gebruik de annotatie `@JoinColumn(name = "car_id")` om de kolom te definiëren die de relatie tussen de reparatienota en de auto vastlegt. De `car_id` zal verwijzen naar de primaire sleutel van de `Car` entiteit.

5. Zorg ervoor dat de `Car` klasse:
    - Een `List<RepairInvoice>` bevat die alle reparatienota's van de auto bijhoudt.
    - De relatie met de `RepairInvoice` klasse wordt gedefinieerd als `@OneToMany(mappedBy = "car")`.



### Vereisten:
- De klasse moet een constructor bevatten voor het initialiseren van de velden.
- De klasse moet getter- en setter-methoden bevatten voor alle velden.
- Zorg ervoor dat je alle benodigde import statements toevoegt.



### Verwachte structuur van de `RepairInvoice` klasse:

<details>
<summary>Klik hier voor de uitwerking</summary>

```java
package nl.novi.cardemo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repair_invoices")
public class RepairInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // De primaire sleutel van de reparatienota
    private LocalDate repairDate; // De datum waarop de reparatie plaatsvond
    private Double totalCost; // De totale kosten van de reparatie

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id") // Koppelt de reparatienota aan een auto via de primaire sleutel van de auto
    private Car car; // Verwijzing naar de auto die gerepareerd is

    // Constructor
    public RepairInvoice() {}

    public RepairInvoice(LocalDate repairDate, Double totalCost, Car car) {
        this.repairDate = repairDate;
        this.totalCost = totalCost;
        this.car = car;
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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

---

### Verwachte structuur van de aangepaste `Car` klasse:

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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL) // Eén auto kan meerdere reparatienota's hebben
    private List<RepairInvoice> repairInvoices;

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

    public List<RepairInvoice> getRepairInvoices() {
        return repairInvoices;
    }

    public void setRepairInvoices(List<RepairInvoice> repairInvoices) {
        this.repairInvoices = repairInvoices;
    }
}
```

</details>



### Tips:
1. **Annotaties**:
    - Gebruik `@ManyToOne` in de `RepairInvoice` klasse en `@OneToMany` in de `Car` klasse om de relatie correct te definiëren.
2. **Cascade-opties**:
    - Gebruik `cascade = CascadeType.ALL` in de `@OneToMany` annotatie als wijzigingen in de `Car` entiteit ook moeten worden doorgegeven aan de bijbehorende `RepairInvoice` entiteiten.
3. **Referentievelden**:
    - Zorg ervoor dat de `mappedBy` waarde in de `Car` klasse exact overeenkomt met de veldnaam in de `RepairInvoice` klasse.
