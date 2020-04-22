package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.RoleDTO;
import io.hieu.creamice.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class RoleController {
    @Autowired
    private IRoleService IRoleService;

    @GetMapping(value = "/roles")
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        List<RoleDTO> roles = IRoleService.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

//    @RequestMapping(value = "/roles", method = RequestMethod.GET)
//    public Page<RoleDTO> findAllRoles(Pageable pageable) {
//        return IRoleService.findAll(pageable);
//    }

    @GetMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable("id") Long id) {
        Optional<RoleDTO> role = IRoleService.findById(id);
        if (role.isPresent()) {
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        roleDTO = IRoleService.create(roleDTO);
        return new ResponseEntity<>(roleDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) {
        roleDTO = IRoleService.update(roleDTO, id);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
        IRoleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}