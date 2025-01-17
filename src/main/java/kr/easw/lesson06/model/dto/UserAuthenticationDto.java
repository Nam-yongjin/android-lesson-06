package kr.easw.lesson06.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAuthenticationDto {
    private final String token;

    public UserAuthenticationDto(String s, String token) {
        this.token = token;
    }
}
