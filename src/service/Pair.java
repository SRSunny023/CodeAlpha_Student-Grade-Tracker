package service;

public class Pair {

    private String id;
    private double cgpa;

    public Pair(String id, double cgpa){
        this.id = id;
        this.cgpa = cgpa;
    }

    public String getId(){
        return id;
    }

    public double getCGPA(){
        return cgpa;
    }

}
