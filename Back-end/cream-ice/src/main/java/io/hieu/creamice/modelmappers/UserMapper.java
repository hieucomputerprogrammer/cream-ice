package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.UserDTO;
import io.hieu.creamice.dto.UserInputDTO;

public class UserMapper {
    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO;
        userDTO = new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getGender(),
                user.getDate_of_birth(), user.getAddress(), user.getPhone_number(), user.getEmail(),
                user.getStatus(), user.getAvatar(), user.getRoles());
        return userDTO;
    }

    public static User userInputDTOToUser(UserInputDTO userInputDTO) {
        User user = new User();
        user.setUsername(userInputDTO.getUsername());
        user.setPassword(userInputDTO.getPassword());
        user.setName(userInputDTO.getName());
        user.setGender(userInputDTO.getGender());
        user.setDate_of_birth(userInputDTO.getDate_of_birth());
        user.setAddress(userInputDTO.getAddress());
        user.setPhone_number(userInputDTO.getPhone_number());
        user.setEmail(userInputDTO.getEmail());
        user.setAvatar(userInputDTO.getAvatarInBase64());
        user.setStatus(userInputDTO.getStatus());
        return user;
    }
}