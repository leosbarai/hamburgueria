package com.github.leosbarai.hamburgueria.user.dto;

public class UserDTO implements Comparable<UserDTO> {

    public Long id;
    public String name;
    public String email;
    public String password;
    public Boolean admin;

    @Override
    public int compareTo(UserDTO o) {
        int compare;
        compare = email.compareTo(o.email);
        return compare;
    }
}
