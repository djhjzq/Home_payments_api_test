package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public record UserLoginForm(@Email String email, @Size(min = 4, max = 16) String password) {

}
