package io.hieu.creamice.services;

import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.dto.UserInputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> findAll();

    Optional<UserDTO> findById(Long id);

//    UserDTO create(UserInputDTO userInputDTO);

    UserDTO create(User user);

    UserDTO update(UserInputDTO userInputDTO, Long id);

    Page<UserDTO> findAll(Pageable pageable);

    void delete(Long id);
}