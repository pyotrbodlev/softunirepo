package app.web.manage_beans;

import app.domain.models.binding.UserLoginModel;
import app.domain.models.sevice.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserLoginModel userLoginModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
        this.userLoginModel = new UserLoginModel();
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserLoginModel getUserLoginModel() {
        return userLoginModel;
    }

    public void setUserLoginModel(UserLoginModel userLoginModel) {
        this.userLoginModel = userLoginModel;
    }

    public void loginUser() throws IOException {
        UserServiceModel user = this.userService.getByUsername(this.userLoginModel.getUsername());
        FacesContext currentInstance = FacesContext.getCurrentInstance();

        if (user != null && DigestUtils.sha256Hex(this.userLoginModel.getPassword()).equals(user.getPassword())) {
            HttpSession session = (HttpSession) currentInstance.getExternalContext().getSession(true);

            session.setAttribute("loggedUser", user);
            currentInstance.getExternalContext().redirect("index.xhtml?u=" + user.getRole());
        } else {
            currentInstance.getExternalContext().redirect("index.xhtml");
        }
    }
}
