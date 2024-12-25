package com.contact_manager.app.service;

import com.contact_manager.app.model.entities.CustomResponseEntity;
import com.contact_manager.app.model.request.LoginRequest;
import com.contact_manager.app.model.request.UserSignupDto;

public interface AuthService {

    CustomResponseEntity<LoginRequest> login(LoginRequest loginRequest);

    CustomResponseEntity<UserSignupDto> signup(UserSignupDto userSignupDto);
}
