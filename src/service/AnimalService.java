package service;

import model.Animal;
import model.AnimalType;

import java.io.*;
import java.util.*;

public class AnimalService {
    private final File file = new File("animals.xml");
    private final List<Animal> animals = new ArrayList<>();

    public AnimalService() {
        loadAnimals();
    }

    public List<Animal> getAllAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        saveAnimals();
    }

    public void deleteAnimal(String name) {
        animals.removeIf(a -> a.getName().equalsIgnoreCase(name));
        saveAnimals();
    }

    public void updateAnimal(String name, Animal updated) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName().equalsIgnoreCase(name)) {
                animals.set(i, updated);
                break;
            }
        }
        saveAnimals();
    }

    private void loadAnimals() {
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            animals.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 5) continue;
                Animal a = new Animal(
                    parts[0],
                    Double.parseDouble(parts[1]),
                    parts[2],
                    Integer.parseInt(parts[3]),
                    AnimalType.valueOf(parts[4])
                );
                animals.add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAnimals() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<animals>\n");

            for (Animal a : animals) {
                writer.write("\t<animal>\n");
                writer.write(String.format("\t\t<name>%s</name>\n", a.getName()));
                writer.write(String.format("\t\t<weight>%.2f</weight>\n", a.getWeight()));
                writer.write(String.format("\t\t<gender>%s</gender>\n", a.getGender()));
                writer.write(String.format("\t\t<birthYear>%d</birthYear>\n", a.getBirthYear()));
                writer.write(String.format("\t\t<type>%s</type>\n", a.getType()));
                writer.write("\t</animal>\n");
            }

            writer.write("</animals>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
