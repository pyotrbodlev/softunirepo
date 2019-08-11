package web.beans;

import domain.models.binding.UserRegisterBindingModel;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.UserService;
import utils.ValidationUtil;

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
    private ValidationUtil validationUtil;

    public UserRegisterBean() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())
                && this.validationUtil.isValid(this.userRegisterBindingModel)) {
            UserServiceModel serviceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
            serviceModel.setPassword(DigestUtils.sha256Hex(this.userRegisterBindingModel.getPassword()));

            this.userService.saveUser(serviceModel);
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("register.xhtml");
        }
    }
}
