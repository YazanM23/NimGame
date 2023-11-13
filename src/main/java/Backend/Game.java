package Backend;

import com.example.aihome.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Backend.Tree.printOneLevel;

public class Game {
    public static int playHard(Node head, int whoPlay) {
        int index=machineTurn(head,1);

        return index;

    }
    public static int playMedium(Node head, int whoPlay) {
        if (head.isLeaf) return head.MaxMin;

        printOneLevel(head,0,0);

        if (whoPlay==-1)
        {
            int index=playerTurn(head);

            if (index == -1) System.out.println("No matching child found. Handle this case accordingly.");
            else head.MaxMin = playMedium(head.childrens.get(index), -whoPlay);
        }
        else if (whoPlay==1)//pc turn
        {
            int index= machineTurn(head,2);
            List<Integer> temp=getPcValues(head,head.childrens.get(index));
            for (Integer integer : temp) System.out.println(integer);

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
        List <Integer> templist=getPlayerMovements(head);
//        for(int i=0;i<templist.size();i++){
//            System.out.println(templist.get(i));
//        }
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

    public static List<Integer> getPcValues(Node head, Node child) {
        List<Integer> tempList = new ArrayList<>();

        List<Integer> headGroups = new ArrayList<>(head.matchesGroups);
        List<Integer> childGroups = new ArrayList<>(child.matchesGroups);

        for (Integer value : head.matchesGroups) {
            childGroups.remove(value);
        }

        for (Integer value : child.matchesGroups) {
            headGroups.remove(value);
        }

        if (!headGroups.isEmpty()) {
            tempList.add(headGroups.get(0));
        }

        if (!childGroups.isEmpty()) {
            tempList.add(childGroups.get(0));
        }

        if (childGroups.size() > 1) {
            tempList.add(childGroups.get(1));
        }

        return tempList;
    }

    public static List<Integer> agetPcValues(Node head, Node child) {
        List<Integer> tempList = new ArrayList<>();

        List<Integer> headGroups = new ArrayList<>(head.matchesGroups);
        List<Integer> childGroups = new ArrayList<>(child.matchesGroups);
        headGroups.removeAll(childGroups);
        childGroups.removeAll(head.matchesGroups);
        tempList.add(headGroups.get(0));
                    tempList.add(childGroups.get(0));
                    tempList.add(childGroups.get(1));

//        int flag = 1;
//        for (int i = 0; i < childGroups.size(); i++){
//            for (int j = 0; j < childGroups.size(); j++)
//                if (childGroups.get(i) + childGroups.get(j) == headGroups.get(0)) {
//                    tempList.add(headGroups.get(0));
//                    tempList.add(childGroups.get(i));
//                    tempList.add(childGroups.get(j));
//                   flag=0;
//                    break;
//
//                }
//            if (flag==0) break;
//         }



        return tempList;
    }


}