package com.app.moda.americana.service;

import com.app.moda.americana.domain.Role;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.repository.IRoleRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getOne(Long id) {
        return roleRepository.findById(id).get();
    }

    public void updateRole(Role role){
        roleRepository.save(role);
    }


}
