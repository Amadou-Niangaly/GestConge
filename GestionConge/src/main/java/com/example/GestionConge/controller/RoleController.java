package com.example.GestionConge.controller;

import com.example.GestionConge.models.Role;
import com.example.GestionConge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //recuperer la liste
    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
    //par id
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //ajouter
    @PostMapping("/ajout")
    public Role addRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }
    //mise a jour
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        try{
            Role updatedRole = roleService.updateRole(id, role);
            return ResponseEntity.ok(updatedRole);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if(role.isPresent()){
            roleService.deleteRole(id);
            return ResponseEntity.ok(role.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
