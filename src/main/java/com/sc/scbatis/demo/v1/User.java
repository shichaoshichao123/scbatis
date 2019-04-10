package com.sc.scbatis.demo.v1;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description:
 */
public class User {

    private Integer id;
    private String name;
    private Integer gender;

    public User() {
    }

    public User(Integer id, String name, Integer gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
