package com.sparta.hanghae_homework_week04.service;

import com.sparta.hanghae_homework_week04.domain.Authority;
import com.sparta.hanghae_homework_week04.domain.RefreshToken;
import com.sparta.hanghae_homework_week04.domain.User;
import com.sparta.hanghae_homework_week04.dto.ResponseDto;
import com.sparta.hanghae_homework_week04.dto.TokenDto;
import com.sparta.hanghae_homework_week04.dto.UserDto;
import com.sparta.hanghae_homework_week04.dto.UserRequestDto;
import com.sparta.hanghae_homework_week04.repository.RefreshTokenRepository;
import com.sparta.hanghae_homework_week04.repository.UserRepository;
import com.sparta.hanghae_homework_week04.security.JwtFilter;
import com.sparta.hanghae_homework_week04.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    public ResponseDto<?> signup(UserDto userDto) throws RuntimeException {

        if (userRepository.existsByNickname(userDto.getNickname())) {
            throw new RuntimeException("중복된 닉네임이 존재합니다.");
        }

        if (!userDto.getPassword().equals(userDto.getPasswordChk())) {
            throw new RuntimeException("비밀번호 값이 서로 일치하지 않습니다.");
        }

        String secret_password = passwordEncoder.encode( userDto.getPassword() );

        User saveUser = new User(userDto.getNickname(), secret_password, Authority.ROLE_USER);

        User user = userRepository.save(saveUser);

        UserDto resultDto = new UserDto(user.getNickname(), user.getPassword(), user.getAuthority());

        return ResponseDto.success(resultDto);
    }

    public ResponseEntity<?> login(UserRequestDto userRequestDto) throws RuntimeException {

        if (!userRepository.existsByNickname(userRequestDto.getNickname())) {
            throw new RuntimeException("입력한 아이디가 존재하지 않습니다.");
        }

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = userRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        User user = userRepository.findByNickname( userRequestDto.getNickname()).orElse( null );
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER , JwtFilter.BEARER_PREFIX + tokenDto.getAccessToken());
        httpHeaders.add("Refresh-Token" , tokenDto.getRefreshToken());

        // 5. 토큰 발급
        return new ResponseEntity<>( ResponseDto.success( user ), httpHeaders, HttpStatus.OK) ;
    }
}
