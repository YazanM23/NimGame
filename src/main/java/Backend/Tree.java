package Backend;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public static void generateTree(Node head) {
        if (!head.getMatchesGroups().isEmpty()) {
            generateChildren(head);
            for (Node child : head.getChildrens()) {
                generateTree(child);
            }
        }
    }
    public static void generateChildren(Node node) {
        List<Integer> matchesGroups = node.getMatchesGroups();
        List<Node> newChildren = new ArrayList<>();
        boolean lastLevelReached = true;

        for (int i = 0; i < matchesGroups.size(); i++) {
            int valueOfGroup = matchesGroups.get(i);
            int condition=valueOfGroup;
            if (valueOfGroup % 2==0) condition--;
            if (valueOfGroup >= 3) {
                lastLevelReached = false; // At least one value is >= 3

                for (int j = 1; j <= condition / 2; j++) {
                    List<Integer> tempList = new ArrayList<>(matchesGroups);
                    tempList.remove(i);
                    tempList.add(valueOfGroup - j);
                    tempList.add(j);
                    Node tempNode = new Node(tempList);
                    newChildren.add(tempNode);
                }
            }
        }

        node.setChildrens(newChildren);
        if (lastLevelReached) {
            node.setLeaf(true);
        }
    }


    public static int alphaBeta(Node node, int alpha, int beta, int player) {
        if ( node.isLeaf) {
            node.MaxMin=-player;
            return node.MaxMin; // Assuming MaxMin is the evaluation function for the node
        }

        if (player == 1) { // Maximizer's turn
            int maxEval = Integer.MIN_VALUE;
            for (Node child : node.childrens) {
                int eval = alphaBeta(child,  alpha, beta, -player);
                child.MaxMin=maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
//                if (beta <= alpha) {
//                    break; // Beta cutoff
//                }
            }
            return maxEval;
        } else { // Minimizer's turn
            int minEval = Integer.MAX_VALUE;
            for (Node child : node.childrens) {
                int eval = alphaBeta(child, alpha, beta, -player);
                child.MaxMin = minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
//                if (beta <= alpha) {
//                    break; // Alpha cutoff
//                }
            }
            return minEval;

        }
    }

    public static void printOneLevel(Node node, int targetDepth, int currentDepth) {
        if (currentDepth == targetDepth)
            System.out.println(getIndentation(currentDepth) + node.getMatchesGroups() + "   -->   (Is Leaf: " + node.isLeaf() + ")" + " MaxMin: " + node.MaxMin);
        else {
            for (Node child : node.getChildrens()) {
                printOneLevel(child, targetDepth, currentDepth + 1);
            }
        }
    }
    public static void printTree(Node node, int depth) {
        System.out.println(getIndentation(depth) + node.getMatchesGroups() + " (Is Leaf: " + node.isLeaf() + ")"+ " MaxMin: "+node.MaxMin);

        for (Node child : node.getChildrens()) {
            printTree(child, depth + 1);
        }
    }
    public static String getIndentation(int depth) {
        return "  ".repeat(Math.max(0, depth)) // Two spaces per level of depth
                ;
    }
}

