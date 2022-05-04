package com.sparta.springcore.service;

import com.sparta.springcore.advice.RestException;
import com.sparta.springcore.dto.UserRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원 가입 처리
    public void registerUser(UserRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        boolean usernameDuplicate = userRepository.existsByUsername(username);
        if (usernameDuplicate) {
            throw new RestException(HttpStatus.BAD_REQUEST, "fail", "사용중인 ID");
        }

        // 닉네임 중복 확인
        String nickname = requestDto.getNickname();
        boolean nicknameDuplicate = userRepository.existsByNickname(nickname);
        if (nicknameDuplicate) {
            throw new RestException(HttpStatus.BAD_REQUEST, "fail", "사용중인 닉네임");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 이메일 중복 확인
        String email = requestDto.getEmail();
        boolean emailDuplicate = userRepository.existsByEmail(email);
        if (emailDuplicate) {
            throw new RestException(HttpStatus.BAD_REQUEST, "fail", "사용중인 이메일");
        }

        User user = new User(username, nickname, password, email);
        userRepository.save(user);
    }

    // 로그인 처리
    public User login(String username, String password) {
        Optional<User> findUserOptional = userRepository.findByUsername(username);
        User user = findUserOptional.get();
        // 암호화 하지 않은 비번과 암호화한 비번이 일치하는지 비교
        // 일치하면 유저 객체 넘겨주기
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
            // 일치하지 않으면 null 반환
        } else {
            return null;
        }
    }
}