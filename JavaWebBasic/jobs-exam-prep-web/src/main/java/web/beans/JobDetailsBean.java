package web.beans;

import domain.models.service.JobServiceModel;
import org.hibernate.Session;
import service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class JobDetailsBean {
    private JobServiceModel jobServiceModel;
    private JobService jobService;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService) {
        this.jobService = jobService;
        this.findJob();
    }

    private void findJob() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String id = request.getQueryString().split("=")[1];
        this.jobServiceModel = this.jobService.findById(id);
        request.getSession().setAttribute("jobId", id);
    }

    public JobServiceModel getJobServiceModel() {
        return jobServiceModel;
    }

    public void setJobServiceModel(JobServiceModel jobServiceModel) {
        this.jobServiceModel = jobServiceModel;
    }
}
