package com.contact_manager.app.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {

    private String username;
    private String email;
    private String password;
    private String phone;

}
