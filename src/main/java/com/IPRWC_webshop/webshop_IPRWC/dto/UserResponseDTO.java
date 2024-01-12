package com.IPRWC_webshop.webshop_IPRWC.dto;

import com.IPRWC_webshop.webshop_IPRWC.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private Role role;
    private String rankName;
}
