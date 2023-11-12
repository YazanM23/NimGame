package Backend;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Integer> matchesGroups;
    public List<Node> childrens;
    public int MaxMin;
    public boolean isLeaf;
    public float tieBreaker;
    public int divided;
    public int firstValue;
    public int secondValue;

    public Node() {
        this.matchesGroups =new ArrayList<>();
        this.childrens =new ArrayList<>();
        this.isLeaf=false;
        this.MaxMin=0;
        this.tieBreaker =0;
        this.divided=0;
        this.firstValue=0;

    }
    public Node(List<Integer> matchesNumber) {
        this.matchesGroups = matchesNumber;
        this.childrens =new ArrayList<>();
        this.isLeaf=false;
        this.MaxMin=0;
        this.tieBreaker =0;
        this.divided=0;
        this.secondValue=0;

    }
//    public Node(List<Integer> matchesNumber, List<Node> childrens) {
//        this.matchesGroups = matchesNumber;
//        this.childrens = childrens;
//        this.isLeaf=false;
//        this.MaxMin=0;
//        this.tieBreaker =0;
//    }




    public void setChildrens(List<Node> childrens) {
        this.childrens = childrens;
    }
    public List<Integer> getMatchesGroups() {
        return matchesGroups;
    }
    public List<Node> getChildrens() {
        return childrens;
    }
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
    public boolean isLeaf() {
        return isLeaf;
    }

}
