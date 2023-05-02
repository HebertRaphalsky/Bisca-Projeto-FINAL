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

        switch (option) {
            case 1:

                game = new Game(new Pc("R2-D2"), new Pc("J.A.R.V.I.S"), new Pc("Jailson"), new Pc("Capica"));
                break;
            default:
                break;
        }
        Player vencedor = game.play();
        System.out.println("O vencedor é :" + vencedor + " with " + vencedor.countPoints() + " points");
    }
}
