package app.web.manage_beans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.sevice.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void registerUser() throws IOException {
        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        UserServiceModel user = this.userService.saveUser(userServiceModel);

        FacesContext currentInstance = FacesContext.getCurrentInstance();

        currentInstance.getExternalContext().redirect("login.xhtml");
    }
}
