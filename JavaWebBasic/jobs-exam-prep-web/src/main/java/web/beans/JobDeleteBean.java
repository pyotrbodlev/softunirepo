package web.beans;

import service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class JobDeleteBean {
    private JobService jobService;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService) {
        this.jobService = jobService;
    }

    public void deleteJob() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String jobId = (String) request.getSession().getAttribute("jobId");

        this.jobService.deleteById(jobId);
        externalContext.redirect("home.xhtml");
    }
}
