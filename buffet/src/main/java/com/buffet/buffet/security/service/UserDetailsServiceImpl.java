package com.buffet.buffet.security.service;

import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.worker.Worker;
import com.buffet.buffet.model.worker.WorkerRepository;
import com.buffet.buffet.security.entity.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepository repository;

    private final WorkerRepository workerRepository;
    @Autowired
    public UserDetailsServiceImpl(UserAccountRepository repository, WorkerRepository workerRepository) {
        this.repository = repository;
        this.workerRepository = workerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount userDetail = repository.findByEmail(username);
        Worker workerDetail = workerRepository.findByNumWorker(username);
        if (userDetail != null) {
            return UserInfoDetails.build(userDetail);
        } else if (workerDetail != null) {
            return UserInfoDetails.build(workerDetail);
        } else {
            throw new UsernameNotFoundException("UserNotFound");
        }
    }
}
