package web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserCheckBean {
    public void checkAlreadyLoggedIn() throws IOException {
        if (isLoggedIn()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("home.xhtml");
        }
    }

    public void checkForHomePage() throws IOException {
        if (!isLoggedIn()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("index.xhtml");
        }
    }

    public void checkForLoggedUser() throws IOException {
        if (!isLoggedIn()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect("index.xhtml");
        }
    }

    private boolean isLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session == null) {
            return false;
        }

        Object username = session.getAttribute("username");

        return username != null;
    }
}
