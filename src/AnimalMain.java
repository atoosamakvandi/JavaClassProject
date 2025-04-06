import model.Animal;
import model.AnimalType;
import service.AnimalService;

import java.util.Scanner;

public class AnimalMain {
    public static void main(String[] args) {
        AnimalService service = new AnimalService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. نمایش همه حیوانات");
            System.out.println("2. افزودن حیوان");
            System.out.println("3. حذف حیوان");
            System.out.println("4. ویرایش حیوان");
            System.out.println("5. خروج");
            System.out.print("انتخاب شما: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    service.getAllAnimals().forEach(System.out::println);
                    break;
                case 2:
                    Animal a = readAnimal(scanner);
                    service.addAnimal(a);
                    break;
                case 3:
                    System.out.print("نام حیوان برای حذف: ");
                    String delName = scanner.nextLine();
                    service.deleteAnimal(delName);
                    break;
                case 4:
                    System.out.print("نام حیوان برای ویرایش: ");
                    String editName = scanner.nextLine();
                    Animal updated = readAnimal(scanner);
                    service.updateAnimal(editName, updated);
                    break;
                case 5:
                    return;
            }
        }
    }

    private static Animal readAnimal(Scanner scanner) {
        System.out.print("نام: ");
        String name = scanner.nextLine();
        System.out.print("وزن: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("جنسیت: ");
        String gender = scanner.nextLine();
        System.out.print("سال تولد: ");
        int birthYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("نوع (DOG, CAT, BIRD, FISH, OTHER): ");
        AnimalType type = AnimalType.valueOf(scanner.nextLine().toUpperCase());

        return new Animal(name, weight, gender, birthYear, type);
    }
}
