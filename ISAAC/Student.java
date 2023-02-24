package com.langsin.show;

/**
 * @author LQWQK
 * @version 1.0
 * @since 2023-02-24 19:21:19
 */
public class Student extends Person implements Danceable{

    private int age;
    public String name;

    class In{

    }

    public Student() {
    }

    private Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private void show(){
        System.out.println("私有方法!");
    }
    public void say(){
        System.out.println("公有方法!");
    }
    @Override
    public void dancing() {

    }
}
