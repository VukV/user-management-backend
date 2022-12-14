package com.raf.cloudproviderbackend.controllers;

import com.raf.cloudproviderbackend.dto.MessageDto;
import com.raf.cloudproviderbackend.dto.user.UserCreateDto;
import com.raf.cloudproviderbackend.dto.user.UserDto;
import com.raf.cloudproviderbackend.dto.user.UserUpdateDto;
import com.raf.cloudproviderbackend.model.user.RoleEnum;
import com.raf.cloudproviderbackend.security.CheckRole;
import com.raf.cloudproviderbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @CheckRole(roles = {RoleEnum.READ, RoleEnum.DELETE, RoleEnum.UPDATE})
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @CheckRole(roles = {RoleEnum.CREATE, RoleEnum.UPDATE, RoleEnum.DELETE})
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @CheckRole(roles = RoleEnum.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new MessageDto("User deleted successfully."));
    }

    @PostMapping
    @CheckRole(roles = RoleEnum.CREATE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        userService.createNewUser(userCreateDto);
        return ResponseEntity.ok(new MessageDto("User created successfully."));
    }

    @PutMapping
    @CheckRole(roles = RoleEnum.UPDATE)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto){
        userService.updateUser(userUpdateDto);
        return ResponseEntity.ok(new MessageDto("User updated successfully."));
    }
}
