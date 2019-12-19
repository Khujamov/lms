package com.restgo;

import com.restgo.model.User;
import com.restgo.service.LibService;

public class Test {
    public static void main(String[] args) {
        LibService service = new LibService();
        service.addUser(new User(1,"r","r"));
    }
}
