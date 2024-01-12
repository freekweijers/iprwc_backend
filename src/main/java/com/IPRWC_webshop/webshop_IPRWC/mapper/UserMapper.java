package com.IPRWC_webshop.webshop_IPRWC.mapper;

import com.IPRWC_webshop.webshop_IPRWC.dto.UserResponseDTO;
import com.IPRWC_webshop.webshop_IPRWC.dto.UserCreateDTO;
import com.IPRWC_webshop.webshop_IPRWC.model.Role;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(UserCreateDTO userCreateDTO) {
//        Rank rank = rankDao.findById(UUID.fromString(userCreateDTO.getRank()));
//        System.out.println(rank.getRankName());
        Role role = Role.valueOf(userCreateDTO.getRole());

        return User.builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .role(role)
                .build();
    }

    public UserResponseDTO fromEntity(User user) {

        UserResponseDTO.UserResponseDTOBuilder res = UserResponseDTO
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole());


        return res.build();
    }
}
