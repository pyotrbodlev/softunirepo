package softuni.productshop.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProducts {
    @XmlAttribute
    private int size;

    @XmlElement(name = "user")
    @XmlElementWrapper(name = "users")
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
