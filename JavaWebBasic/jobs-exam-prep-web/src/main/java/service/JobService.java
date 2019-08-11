package service;

import domain.models.service.JobServiceModel;

import java.util.Arrays;
import java.util.List;

public interface JobService {
    void saveJob(JobServiceModel jobServiceModel);

    List<JobServiceModel> getAll();

    JobServiceModel findById(String id);

    void deleteById(String id);

}
