package com.miniproject.miniprojectgroupthree.domain.entity;


public class UserEntityFixture {

    public static UserEntity get(String userName,String password) {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUserName(userName);
        entity.setPassword(password);
        return entity;
    }

}


