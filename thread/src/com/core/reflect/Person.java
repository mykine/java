package com.core.reflect;

public class Person {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
//        this.name = "默认佚名";
//        this.age = 0;
    }


    public void print(){
        System.out.println("hello world");
    }

    public int print(int a,int b){
        System.out.println(this.name+"正在计算,a+b="+(a+b));
        return (a+b);
    }

}
