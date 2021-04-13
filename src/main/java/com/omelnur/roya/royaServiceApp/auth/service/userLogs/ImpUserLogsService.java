package com.omelnur.roya.royaServiceApp.auth.service.userLogs;


import com.omelnur.roya.royaServiceApp.auth.models.UserLogsType;
import com.omelnur.roya.royaServiceApp.auth.models.UsersLogs;
import com.omelnur.roya.royaServiceApp.auth.repository.LogsTypeRepository;
import com.omelnur.roya.royaServiceApp.auth.repository.UserLogsRepository;
import com.omelnur.roya.royaServiceApp.auth.repository.UserRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author michael wagih
 */
@Service
public class ImpUserLogsService implements UserLogsService{

    @Autowired
    UserLogsRepository userLogsRepository;

    @Autowired
    LogsTypeRepository logsTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UsersLogs createLoginLogs(UsersLogs usersLogs, Long logsTypyId , String userName) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        usersLogs.setUser(userRepository.findByUsername(userName).orElseThrow(
                ()->new ResourceNotFoundException("user"+userName+"not found")));
        usersLogs.setUserLogsType(logsTypeRepository.findById(logsTypyId).orElseThrow(
                ()-> new ResourceNotFoundException("logsType id"+logsTypyId+" not found")));
        return userLogsRepository.save(usersLogs);
    }

    @Override
    public Page<UsersLogs> getActiveUserLogs(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<UsersLogs> pagedResult = userLogsRepository.findAllLogs(paging);
        return pagedResult;
    }

    @Override
    public Page<UsersLogs> getLogsByUser(Integer page, Integer size, String sortBy, Long userId) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<UsersLogs> pagedResult = userLogsRepository.getLogsByUser(paging,userId);
        return pagedResult;
    }

    @Override
    public Page<UsersLogs> getLogsByType(Integer page, Integer size, String sortBy, Long typeId) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<UsersLogs> pagedResult = userLogsRepository.findByUserLogsType(paging,typeId);
        return pagedResult;
    }

    @Override
    public Iterable<UserLogsType> getAllType() {
        return logsTypeRepository.findAll();
    }
}

