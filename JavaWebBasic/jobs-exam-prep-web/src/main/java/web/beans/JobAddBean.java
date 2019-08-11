package web.beans;

import domain.entities.Sector;
import domain.models.binding.JobRegisterBindingModel;
import domain.models.service.JobServiceModel;
import org.modelmapper.ModelMapper;
import service.JobService;
import utils.ValidationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobAddBean {
    private JobRegisterBindingModel jobRegisterBindingModel;

    private JobService jobService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public JobAddBean() {
    }

    @Inject
    public JobAddBean(JobService jobService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.initModel();
    }

    private void initModel() {
        this.jobRegisterBindingModel = new JobRegisterBindingModel();
    }

    public JobRegisterBindingModel getJobRegisterBindingModel() {
        return jobRegisterBindingModel;
    }

    public void setJobRegisterBindingModel(JobRegisterBindingModel jobRegisterBindingModel) {
        this.jobRegisterBindingModel = jobRegisterBindingModel;
    }

    public void addJob() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (this.validationUtil.isValid(this.jobRegisterBindingModel)) {
            JobServiceModel jobServiceModel = this.modelMapper.map(this.jobRegisterBindingModel, JobServiceModel.class);
            jobServiceModel.setSector(Sector.valueOf(this.jobRegisterBindingModel.getSector()));
            this.jobService.saveJob(jobServiceModel);

            externalContext.redirect("home.xhtml");
        } else {
            externalContext.redirect("add-job.xhtml");
        }
    }
}
