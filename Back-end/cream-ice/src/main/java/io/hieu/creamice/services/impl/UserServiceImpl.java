package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Role;
import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.dto.UserInputDTO;
import io.hieu.creamice.modelmappers.UserMapper;
import io.hieu.creamice.repositories.IRoleRepository;
import io.hieu.creamice.repositories.IUserRepository;
import io.hieu.creamice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository IUserRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public UserServiceImpl(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = IUserRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : users) {
            userDTOs.add(UserMapper.userToUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> usersPage = IUserRepository.findAll(pageable);
        Page<UserDTO> userDTOsPage = usersPage.map(user -> UserMapper.userToUserDTO(user));
        return userDTOsPage;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return IUserRepository.findById(id)
                .map(user -> UserMapper.userToUserDTO(user));
    }

//    @Override
//    public UserDTO create(UserInputDTO userInputDTO) {
//        User user = UserMapper.userInputDTOToUser(userInputDTO);
//        List<Role> roles = new ArrayList<>();
//        for(Role r : user.getRoles()){
//            System.out.println(user.getRoles());
//            roles.add(roleRepository.findById(r.getId()).get());
//        }
//        user.setRoles(roles);
//        user = IUserRepository.save(user);
//        return UserMapper.userToUserDTO(user);
//    }

    @Override
    public UserDTO create(User user) {
        List<Role> roles = new ArrayList<>();
        for (Role r : user.getRoles()) {
            System.out.println(user.getRoles());
            roles.add(roleRepository.findById(r.getId()).get());
        }
        user.setRoles(roles);
        user = IUserRepository.save(user);
        return UserMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO update(UserInputDTO userInputDTO, Long id) {
        Optional<User> currentUser = IUserRepository.findById(id);
        List<Role> roles = new ArrayList<>();
        for (Role r : userInputDTO.getRoles()) {
            System.out.println(r.getRole());
            roles.add(roleRepository.findById(r.getId()).get());
        }
        if (!currentUser.isPresent()) {
            throw new RuntimeException("No user ID " + id + " found!");
        }
        User user = currentUser.get();
        user.setUsername(userInputDTO.getUsername());
        if (userInputDTO.getPassword() != null && !userInputDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userInputDTO.getPassword());
            user.setPassword(encodedPassword);
        }
        user.setRoles(roles);
        user.setName(userInputDTO.getName());
        user.setGender(userInputDTO.getGender());
        user.setAddress(userInputDTO.getAddress());
        user.setPhone_number(userInputDTO.getPhone_number());
        user.setEmail(userInputDTO.getEmail());
        user.setStatus(userInputDTO.getStatus());
        user.setAvatar(userInputDTO.getAvatarInBase64());

        return UserMapper.userToUserDTO(user);
    }

    @Override
    public void delete(Long id) {
        IUserRepository.deleteById(id);
    }
}