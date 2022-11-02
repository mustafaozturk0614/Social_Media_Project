package com.bilgeadam.controller;


import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import static com.bilgeadam.constant.ApiUrls.*;


@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {


    private  final UserProfileService userProfileService;

@GetMapping(GETALL)
    public Iterable<UserProfile> findAll(){
    return  userProfileService.findAll();
    }

@PostMapping(CREATE)
public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDto dto){

    return ResponseEntity.ok(userProfileService.
            save(IUserMapper.INSTANCE.toUserProfile(dto)));

}

    @PutMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody  UserProfileResponseDto dto){

        return ResponseEntity.ok(userProfileService.
                save(IUserMapper.INSTANCE.toUserProfile(dto)));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        return  ResponseEntity.ok(userProfileService.deleteUser(id));
    }


}
