package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.user.UserInsertDTO;
import com.miniproject.BioskopKampung.dtos.user.UserInsertResponseDTO;
import com.miniproject.BioskopKampung.models.User;
import com.miniproject.BioskopKampung.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInsertResponseDTO registrationUser(UserInsertDTO userDto){
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (userDto.getUsername().equals(user.getUsername())){
                throw new IllegalArgumentException("Username tidak boleh sama");
            }
        }

        String userId = createUserID(users);

        User user = new User(userId, userDto.getUsername(), userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        return UserInsertResponseDTO.set(user);
    }

    public String createUserID(List<User> users){
        int satuan = 0;
        int puluhan = 0;
        int ratusan = 0;
        int year = LocalDate.now().getYear();
        int size = users.size();
        boolean cek = false;

        do {
            try {
                if (size < 10) {
                    satuan = size + 1;
                }

                if (size < 100) {
                    puluhan = (int)Math.floor(size/10);

                }

                if (size < 1000){
                    ratusan = (int)Math.floor(size/100);
                }

                cek = false;
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed creating id");
            }

            cek = true;
        } while (!cek);

        String id = String.format("C%d%d%d%d", year, ratusan, puluhan, satuan);

        return id;
    }
}
