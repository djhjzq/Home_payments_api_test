package com.demchenko.homepay.dto.response;

import com.demchenko.homepay.entity.Role;
import lombok.Data;


@Data
public class JwtResponse {
    private  Long id;
    private  String token;
    private  String email;
    private  Role role;

}
