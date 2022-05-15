package project.entity;


//import org.hibernate.annotations.Table;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "calories")
    int calories;
    @Column(name = "proteins")
    int proteins;
    @Column(name = "fats")
    int fats;
    @Column(name = "carbonydrates")
    int carbonydrates;

   /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingridient_product")
    private List<Ingridient> ingridientList;

    */

    @OneToMany(mappedBy = "product",
            cascade = {CascadeType.ALL})
    private List<Ingridient> ingridientList;


    public void addIngridient(Ingridient ingridient) {
        if (ingridientList == null) {
            ingridientList = new ArrayList<>();
        }
        ingridientList.add(ingridient);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbonydrates=" + carbonydrates +
                '}';
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbonydrates() {
        return carbonydrates;
    }

    public void setCarbonydrates(int carbonydrates) {
        this.carbonydrates = carbonydrates;
    }

    public Product(String name, int calories, int proteins, int fats, int carbonydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbonydrates = carbonydrates;
    }


}