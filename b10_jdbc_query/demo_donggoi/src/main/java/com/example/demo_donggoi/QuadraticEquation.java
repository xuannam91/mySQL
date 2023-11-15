package com.example.demo_donggoi;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation() {
    }

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getDiscriminant(){
        return (b * b) - (4 * a * c);
    }

    public String resultQuadraticEquation(){
        String result;
        double delta = getDiscriminant();

        if(a==0){
            if(b==0){
                if(c == 0){
                    result = "phương trình vô số nghiệm";
                }else {
                    result = "phương trình vô nghiệm";
                }

            }else {
                result = "Phương trình có 1 nghiệm duy nhất x=  " + (-c/b);
            }
        }else{
            if(delta == 0){
                result = "phương trình có nghiệm kép" + (-b/(2*a));
            } else if (delta< 0) {
                result = "phương trình vô nghiệm";
            }else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                result = "Phương trình có 2 nghiệm là x1 = " + x1 + " và x2 = " + x2;
            }

        }
        return result;
    }
}
