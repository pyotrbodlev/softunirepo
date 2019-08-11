package web.beans;

import domain.entities.User;
import domain.models.binding.UserLoginBindingModel;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import repository.UserRepository;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserLoginBindingModel userLoginBindingModel;
    private UserService userService;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel userByUsername = this.userService.findByUsername(this.userLoginBindingModel.getUsername());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (userByUsername != null
                && userByUsername.getPassword().equals(this.userLoginBindingModel.getPassword())) {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.getSession().setAttribute("loggedIn", true);
            request.getSession().setAttribute("username", userByUsername.getUsername());
            externalContext.redirect("home.xhtml");
        } else {
            externalContext.redirect("login.xhtml");
        }
    }
}
