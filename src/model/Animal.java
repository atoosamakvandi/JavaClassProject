package model;

public class Animal {
    private String name;
    private double weight;
    private String gender;
    private int birthYear;
    private AnimalType type;

    public Animal(String name, double weight, String gender, int birthYear, AnimalType type) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.birthYear = birthYear;
        this.type = type;
    }

    public Animal() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public AnimalType getType() { return type; }
    public void setType(AnimalType type) { this.type = type; }

    @Override
    public String toString() {
        return name + " - " + type + " - " + gender + " - " + weight + "kg - Born: " + birthYear;
    }
}
