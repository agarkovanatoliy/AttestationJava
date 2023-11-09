import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Raffle {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> prizes = new PriorityQueue<>();

    private static int idCounter = 0;

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите название: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Некоректнай ввод. Попробуйте еще раз.");
                break;
            }
            System.out.print("Введите шанс выпадения: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Некоректнай ввод. Попробуйте еще раз.");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Добавлена новай игрушка.");
                    } else {
                        System.out.println("Эта игрушка уже находится в призовом фонде.");
                    }
                }
            } else {
                System.out.println("Некоректнай ввод. Попробуйте еще раз.");
            }
            break;
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + toys.get(selectedId).getToyTitle() +
                        " имеет следующую частоту побед " + toys.get(selectedId).getfrequency());
                System.out.print("Введите новый шанс выпадения: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setfrequency(newFrequency);
                    System.out.println("Шанс выпадения был изменен.");
                } else {
                    System.out.println("Некоректнай ввод. Попробуйте еще раз.");
                }
            } else {
                System.out.println("В призовом фонде нет ни одной игрушки с таким ID.");
            }
        } else {
            System.out.println("Некоректнай ввод. Попробуйте еще раз.");
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Toy getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getfrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(),
                            rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    public void raffle() {
        if (toys.size() >= 2) {
            Toy prize = getPrize();
            System.out.println("Приз: " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("В призовой фонд необходимо добавить не менее двух игрушек.");
        }
    }

    private void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}