package com.miniproject.BioskopKampung.dtos.user;

import com.miniproject.BioskopKampung.models.User;
import lombok.Data;

@Data
public class UserInsertResponseDTO {

    private final String userId;
    private final String username;
    private final String password;
    private final String role;

    public static UserInsertResponseDTO set(User user){
        return new UserInsertResponseDTO(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getRole());
    }
}
