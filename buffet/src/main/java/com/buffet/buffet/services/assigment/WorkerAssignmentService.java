package com.buffet.buffet.services.assigment;

import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.assigment.WorkerAssignmentRepository;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkerAssignmentService {
    @Autowired
    WorkerAssignmentRepository workerAssignmentRepository;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    PackageRepository packageRepository;


}
