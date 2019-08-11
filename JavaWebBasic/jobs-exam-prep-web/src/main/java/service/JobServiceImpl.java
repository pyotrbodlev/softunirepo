package service;

import domain.entities.JobApplication;
import domain.models.service.JobServiceModel;
import org.modelmapper.ModelMapper;
import repository.JobRepository;
import utils.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveJob(JobServiceModel jobServiceModel) {
        JobApplication jobApplication = this.modelMapper.map(jobServiceModel, JobApplication.class);

        if (this.validationUtil.isValid(jobServiceModel)) {
            this.jobRepository.persist(jobApplication);
        }
    }

    @Override
    public List<JobServiceModel> getAll() {
        return this.jobRepository.findAll().stream().map(j -> this.modelMapper.map(j, JobServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public JobServiceModel findById(String id) {
        JobApplication byId = this.jobRepository.findById(id);
        return this.modelMapper.map(byId, JobServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        JobApplication byId = this.jobRepository.findById(id);
        this.jobRepository.remove(byId);
    }

}
