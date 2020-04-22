package io.hieu.creamice.controllers;

import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.dto.UserInputDTO;
import io.hieu.creamice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class UserController {
    @Autowired
    private IUserService IUserService;

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public ResponseEntity<List<UserDTO>> findAllUsers() {
//        List<UserDTO> users = IUserService.findAll();
//        if (users.isEmpty()) {
//            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping(value = "/users")
    public Page<UserDTO> findAllUsers(Pageable pageable) {
        return IUserService.findAll(pageable);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
        Optional<UserDTO> user = IUserService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping(value = "/users")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserInputDTO userInputDTO) {
//        UserDTO userDTO = IUserService.create(userInputDTO);
//        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
//    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<>(IUserService.create(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserInputDTO userInputDTO) {
        UserDTO userDTO = IUserService.update(userInputDTO, id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        IUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}