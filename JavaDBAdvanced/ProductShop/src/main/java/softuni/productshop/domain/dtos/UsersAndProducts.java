package softuni.productshop.domain.dtos;

import java.util.List;

public class UsersAndProducts {
    private int size;
    private List<UserWithProductDto> users;

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<UserWithProductDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductDto> users) {
        this.users = users;
    }
}
