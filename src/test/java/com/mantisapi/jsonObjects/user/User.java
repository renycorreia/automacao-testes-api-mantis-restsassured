package com.mantisapi.jsonObjects.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String real_name;
    private String email;
    private String access_level_name;
    Access_level access_level;
    private boolean enabled;
    @JsonProperty("protected")
    private boolean protectd;
}
