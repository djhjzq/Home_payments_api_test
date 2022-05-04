package com.demchenko.homepay.dto.request;


import java.io.Serializable;

public record UserUpdateForm(Long userId, String firstName, String lastName,
                             String email) implements Serializable {
}
