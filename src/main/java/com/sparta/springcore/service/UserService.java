package com.sparta.springcore.service;

import com.sparta.springcore.dto.UserRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        boolean usernameDuplicate = userRepository.existsByUsername(username);
        if (usernameDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        
        // 닉네임 중복 확인
        String nickname = requestDto.getNickname();
        boolean nicknameDuplicate = userRepository.existsByNickname(nickname);
        if (nicknameDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 이메일 중복 확인
        String email = requestDto.getEmail();
        boolean emailDuplicate = userRepository.existsByEmail(email);
        if (emailDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = new User(username, nickname, password, email);
        userRepository.save(user);
    }
}