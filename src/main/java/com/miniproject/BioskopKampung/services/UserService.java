package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.user.UserInsertDTO;
import com.miniproject.BioskopKampung.dtos.user.UserInsertResponseDTO;
import com.miniproject.BioskopKampung.models.User;

import java.util.List;

public interface UserService {

    public UserInsertResponseDTO registrationUser(UserInsertDTO userDto);
    public String createUserID(List<User> users);
}
