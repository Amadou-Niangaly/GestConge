package com.example.GestionConge.service;

import com.example.GestionConge.models.Role;
import com.example.GestionConge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    //recuperer tous les roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    //Enregistrer un role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    //recuperer un role par id
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }
    //modifier un role
    public Role updateRole(Long id, Role role) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role roleToUpdate = roleOptional.get();
            roleToUpdate.setType(role.getType());
            roleToUpdate.setUtilisateurs(role.getUtilisateurs());
            return roleRepository.save(roleToUpdate);
        }
        else throw  new RuntimeException("Role n'ont trouve par id");
    }
    //supprimer un role
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
