package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ActivateReguestDto;
import com.bilgeadam.dto.request.FindByToken;
import com.bilgeadam.dto.request.NewCreateUserDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.RoleResponseDto;
import com.bilgeadam.dto.response.UserProfilePostResponseDto;
import com.bilgeadam.dto.response.UserProfileRedisResponseDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@CrossOrigin(origins = "*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto) {

        try {
            System.out.println(dto);
            userProfileService.createUser(dto);
            System.out.println(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateReguestDto dto) {

        return ResponseEntity.ok(userProfileService.activateStatus(dto));

    }

    @PostMapping(ACTIVATESTATUSBYID)
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid) {

        return ResponseEntity.ok(userProfileService.activateStatus(authid));

    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UpdateRequestDto dto) {


        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }

    @PutMapping("/updateredis")
    public ResponseEntity<Boolean> updateProfileForRedis(@RequestBody @Valid UpdateRequestDto dto) {


        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }

    @PutMapping("/updatewithrabbitmq")
    public ResponseEntity<Boolean> updateProfileWithRabbitmq(@RequestBody UpdateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.updateUserWithRabbitMq(dto));
    }

    @GetMapping(GETALL)

    public ResponseEntity<List<UserProfileResponseDto>> findAll() {

        return ResponseEntity.ok(IUserMapper.INSTANCE.toUserProfileResponseDtoList(userProfileService.findAll()));
    }

    @GetMapping("/findbyusername/{username}")
    public ResponseEntity<UserProfileRedisResponseDto> findbyUsername(@PathVariable String username) {


        return ResponseEntity.ok(userProfileService.findByUsername(username));

    }

    @GetMapping("/findallactiveprofile")
    public ResponseEntity<List<UserProfile>> findAllActiveProfile() {

        return ResponseEntity.ok(userProfileService.findAllActiveProfile());

    }


    @GetMapping("/findbyrole/{roles}")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(@PathVariable String roles) {

        return ResponseEntity.ok(userProfileService.findByRole(roles));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.deleteUser(id));
    }


    @GetMapping("/findbypagable")
    public ResponseEntity<Page<UserProfile>> findAllPAge(int pageSize, int pageNumber, String direction, String sortParameter) {
        return ResponseEntity.ok(userProfileService.findallPage(pageSize, pageNumber, direction, sortParameter));
    }

    @GetMapping("/findbyslice")
    public ResponseEntity<Slice<UserProfile>> findAllSlice(int pageSize, int pageNumber, String direction, String sortParameter) {
        return ResponseEntity.ok(userProfileService.findallSlice(pageSize, pageNumber, direction, sortParameter));
    }


    @GetMapping("/findbyautid/{id}")
    public ResponseEntity<UserProfilePostResponseDto> findbyAuthId(@PathVariable Long id) {

        UserProfilePostResponseDto userProfilePostResponseDto =
                IUserMapper.INSTANCE.toUserProfilePostResponseDto(userProfileService.findByAuthId(id).get());
        return ResponseEntity.ok(userProfilePostResponseDto);
    }

    @PostMapping("/findbyid/{id}")
    public ResponseEntity<UserProfile> findbyId(@PathVariable String id, @RequestBody FindByToken token) {

        return ResponseEntity.ok(userProfileService.findById(id, token));
    }

    @PostMapping("/finduserbyid/{id}")
    public ResponseEntity<UserProfile> findbyId(@PathVariable String id) {

        return ResponseEntity.ok(userProfileService.findById(id).get());
    }

    @PostMapping("/findbytoken")
    public ResponseEntity<UserProfile> findbyToken(@RequestBody FindByToken token) {
       
        return ResponseEntity.ok(userProfileService.findByToken(token.getToken()));
    }
}
