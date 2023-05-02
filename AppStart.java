import java.util.Scanner;

public class AppStart {
    private static final Scanner _sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option = -1;
        Game game = null;
        System.out.println("Aperte 1 para iniciar.");
        while (option != 1) {
            option = _sc.nextInt();
        }
        _sc.nextLine();
       
