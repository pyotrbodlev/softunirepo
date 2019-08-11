package web.beans;

import domain.models.service.JobServiceModel;
import service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class JobsListBean {
    private JobService jobService;

    public JobsListBean() {
    }

    @Inject
    public JobsListBean(JobService jobService) {
        this.jobService = jobService;
    }

    public List<JobServiceModel> jobs(){
        return this.jobService.getAll();
    }
}
