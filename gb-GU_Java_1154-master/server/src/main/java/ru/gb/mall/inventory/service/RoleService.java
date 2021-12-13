package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.UsersRole;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<UsersRole> findAll() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), true).toList();
    }

    public UsersRole findById(long id) {
        try {
            return roleRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Roles entity no found by id: " + id, e);
        }
    }

    public boolean deleteById(Long roleId) {
        try {
            roleRepository.deleteById(roleId);
            return true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Roles entity no found by id: " + roleId, e);
        }
    }

    public void saveOrUpdate(UsersRole role) {
        try {
            roleRepository.save(role);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Users role entity no found: " + role, e);
        }
    }

    public boolean saveOrUpdate(Long rolesId) {
        UsersRole role = findById(rolesId);
        if (role == null) {
            return false;
        }
        saveOrUpdate(role);
        return true;
    }
}
