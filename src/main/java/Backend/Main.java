package Backend;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Name: ");
                String Name= scanner.nextLine();
//            System.out.print("\n");


            System.out.print("Enter Number of Matches: ");
            int NumberOfMatches = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
//            System.out.print("\n");

            System.out.print("Enter level of game (H:hard, M:medium, E:easy): ");
            String Level = scanner.nextLine();
//            System.out.print("\n");

            System.out.print("Who start? (M:me, P:pc):");
            String WhoStart = scanner.nextLine();
//            System.out.print("\n");

            if (NumberOfMatches < 3) System.out.println("Invalid Number of matches!");
            else {
                int state = -1;
                int whoStartInt = (WhoStart.equals("M")) ? -1 : 1;

                Node head = new Node();
                head.matchesGroups.add(NumberOfMatches);
                Tree.generateTree(head);

                Tree.alphaBeta(head, Integer.MIN_VALUE, Integer.MAX_VALUE, whoStartInt);

                Tree.printTree(head, 0);

                System.out.println("Dear "+Name+", YOU ARE WELCOME!");
                System.out.println("Be ready to play");
                if (Level.equals("H")) {
                    state = Game.playHard(head, whoStartInt);
                } else if (Level.equals("M")) {
                    state = Game.playMedium(head, whoStartInt);
                } else if (Level.equals("E")) {
                    state = Game.playEasy(head, whoStartInt);
                }
                if (state == -1)     System.out.println("Tra ra ra raaaaa! \nCongratulations! "+Name+" You win !!!!!!!!!!");  //-1 -> pc lose
                else if (state == 1) System.out.println("Oops! \nGame Over!\nTry again, You Can Do It ^_^");// 1 -> pc win
                else                 System.out.println("Sth wont wrong, Play again please");
            }

    }
}
