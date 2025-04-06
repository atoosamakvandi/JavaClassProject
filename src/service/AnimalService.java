package service;

import model.Animal;
import model.AnimalType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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


    public void loadAnimals() {
        if (!file.exists()) return;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList nodeList = document.getElementsByTagName("animal");

            animals.clear();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    double weight = Double.parseDouble(element.getElementsByTagName("weight").item(0).getTextContent());
                    String gender = element.getElementsByTagName("gender").item(0).getTextContent();
                    int birthYear = Integer.parseInt(element.getElementsByTagName("birthYear").item(0).getTextContent());
                    AnimalType type = AnimalType.valueOf(element.getElementsByTagName("type").item(0).getTextContent());

                    Animal animal = new Animal(name, weight, gender, birthYear, type);
                    animals.add(animal);
                }
            }
        } catch (Exception e) {
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
