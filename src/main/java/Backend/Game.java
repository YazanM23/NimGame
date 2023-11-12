package Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Backend.Tree.printOneLevel;

public class Game {
    public static int playHard(Node head, int whoPlay) {
        if (head.isLeaf) return head.MaxMin;

        printOneLevel(head,0,0);

        if (whoPlay==-1)
        {
            int index=playerTurn(head);
            if (index == -1) System.out.println("No matching child found. Handle this case accordingly.");
            else head.MaxMin = playEasy(head.childrens.get(index), -whoPlay);
        }
        else if (whoPlay==1)
        {
            int index=machineTurn(head,1);
            head.MaxMin= playHard(head.childrens.get(index),-whoPlay);
        }

        return head.MaxMin;
    }
    public static int playMedium(Node head, int whoPlay) {
        if (head.isLeaf) return head.MaxMin;

        printOneLevel(head,0,0);

        if (whoPlay==-1)
        {
            int index=playerTurn(head);
            if (index == -1) System.out.println("No matching child found. Handle this case accordingly.");
            else head.MaxMin = playEasy(head.childrens.get(index), -whoPlay);
        }
        else if (whoPlay==1)//pc turn
        {
            int index= machineTurn(head,2);
            head.MaxMin = playMedium(head.childrens.get(index),-whoPlay);
        }
        return head.MaxMin;
    }
    public static int playEasy(Node head, int whoPlay) {
        if (head.isLeaf) return head.MaxMin;
        printOneLevel(head,0,0);

        if (whoPlay==-1)
        {
            int index=playerTurn(head);
            if (index == -1) System.out.println("No matching child found. Handle this case accordingly.");
            else head.MaxMin = playEasy(head.childrens.get(index), -whoPlay);
        }
        else if (whoPlay==1)
        {
            int index= machineTurn(head,3);
            head.MaxMin = playEasy(head.childrens.get(index),-whoPlay);
        }
        return head.MaxMin;
    }

    public static int machineTurn (Node head,int level) {
        int index = -1;
        Random random = new Random();
        System.out.println("Pc turn");
        switch (level) {
            case 1://hard

                int positiveCount = 0;
                for (int i = 0; i < head.childrens.size(); i++) {
                    Node child = head.childrens.get(i);
                    if (child.MaxMin == 1) {
                        positiveCount++;
                    }
                }
                float percentage;
                float max = Integer.MIN_VALUE;
                if (positiveCount == 0)     // no positive -> all children's are -1
                {
                    for (int i = 0; i < head.childrens.size(); i++) {
                        percentage = tieBreaker(head.childrens.get(i), 2);
                        if (percentage > max) {
                            max = percentage;
                            index = i;
                        }
                    }
                }
                else if (positiveCount == 1)// all childes -1 except one is +1
                {
                    for (int i = 0; i < head.childrens.size(); i++) {
                        Node child = head.childrens.get(i);
                        if (child.MaxMin == 1) {
                            index = i;
                            break;
                        }
                    }
                }
                else                         // mex between 1 and -1 || all positive
                {
                    for (int i = 0; i < head.childrens.size(); i++) {
                        if (head.childrens.get(i).MaxMin == 1) {
                            percentage = tieBreaker(head.childrens.get(i), 2);
                            if (percentage > max) {
                                max = percentage;
                                index = i;
                            }
                        }
                    }
                }

                break;

            case 2://medium
                List<Integer> positiveNumbers = new ArrayList<>();
                List<Integer> negativeNumbers = new ArrayList<>();
                for (int i = 0; i < head.childrens.size(); i++) {
                    Node child = head.childrens.get(i);
                    if (child.MaxMin == 1) {
                        positiveNumbers.add(i);
                    } else {
                        negativeNumbers.add(i);
                    }
                }
                if (positiveNumbers.isEmpty()) {
                    int randomNumber = random.nextInt(negativeNumbers.size());
                    index = negativeNumbers.get(randomNumber);
                } else {
                    int randomNumber = random.nextInt(positiveNumbers.size());
                    index = positiveNumbers.get(randomNumber);
                }
                break;

            case 3://easy
                index = random.nextInt(head.childrens.size());
                break;
            default:

        }
        return index;
    }
    public static int playerTurn (Node head){
        System.out.println("Your turn");
        List <Integer> templist= getPlayerMovements(head);
        int index=-1;
        for (int i = 0; i < head.childrens.size(); i++) {
            Node child = head.childrens.get(i);
            if (child.matchesGroups.equals(templist)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static List<Integer> getPlayerMovements(Node head){

        System.out.println("Enter Group value: ");
        int groupValue= Main.scanner.nextInt();

        System.out.println("Enter first value: ");
        int firstValue= Main.scanner.nextInt();

        System.out.println("Enter second value: ");
        int secondValue= Main.scanner.nextInt();

        List <Integer> tempList=new ArrayList<>(head.matchesGroups);
        tempList.remove((Integer) groupValue);

        tempList.add(firstValue);
        tempList.add(secondValue);

        return tempList;
    }
    private static float tieBreaker(Node head, int depth) {
        if (head.isLeaf ){
            return head.MaxMin;
        }

        if (depth ==1)
        {
            int countNegatives=0;
            int countPositives=0;
            for (int i = 0; i < head.childrens.size(); i++) {
                Node child = head.childrens.get(i);
                if (child.MaxMin==1) countPositives++;
                else  countNegatives++;
            }
            if (countNegatives==0)countNegatives++;

            return ((float)countPositives)/((float)countNegatives);
        }
        else {
            float sum=0;
            for (int i = 0; i < head.childrens.size(); i++)
                sum += tieBreaker(head.childrens.get(i), depth-1);

            return sum;
        }
    }
}
