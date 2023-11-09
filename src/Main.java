import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Raffle r = new Raffle();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Главное меню:
                    1 - Добавить в призовой фонд новую игрушку
                    2 - Изменить частоту выпадения некоторых игрушек
                    3 - Проведение лотереи и сохранение результатов
                    0 - ВЫХОД
                    >\s""");
            var selection = sc.next();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.setFrequency();
                case "3" -> r.raffle();
                case "0" -> {
                    System.out.println("Досвидание");
                    System.exit(0);
                }
                default -> System.out.println("Некоректный выбор, повторите ввод.");
            }
        }
    }
}