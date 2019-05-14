package softuni.gamestore.domain.dtos;

import softuni.gamestore.domain.entities.Role;

import javax.validation.constraints.NotNull;

public class UserActiveDto {
    private String email;
    private Role role;
    private String fullName;

    public UserActiveDto() {
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
