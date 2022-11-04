package com.bilgeadam.service;


import com.bilgeadam.exception.ElasticManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.rabbitmq.model.UpdateUsernameEmailModel;
import com.bilgeadam.repository.IUserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 1-Status de�i�di�i zaman bizim active status cache temizlensin
 * 2-Userprofilecontrollerda bir endpoint
 * buda bize d��ar�dan girdi�imiz role g�re bize user profilelar� dons�n
 * ve bu metodu cachleyelim
 * bu metod ne zaman de�i�ecek yani bu cache bir metodun i�inde yeri geldi�i zaman silelim
 */
@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {

    private final IUserProfileRepository userProfileRepository;


    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;

    }


    public List<UserProfile> findAllContainingUsername(String username) {


        return userProfileRepository.findByUsernameContainingIgnoreCase(username);
    }

    public List<UserProfile> findAllByStatus(String status) {

        return userProfileRepository.findAllByStatus(Status.valueOf(status));
    }

    public List<UserProfile> findAllContainingEmail(String email) {

        return userProfileRepository.findByEmailContainingIgnoreCase(email);
    }

    @Transactional
    public boolean deleteUser(Long id) {

        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(id);

        if (userProfile.isPresent()) {
            userProfile.get().setStatus(Status.DELETED);
            save(userProfile.get());
            return true;
        } else {
            throw new ElasticManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

    public boolean updateUser(UpdateUsernameEmailModel model) {

        Optional<UserProfile> auth = userProfileRepository.findOptionalByAuthid(model.getId());

        if (auth.isPresent()) {
            auth.get().setEmail(model.getEmail());
            auth.get().setUsername(model.getUsername());
            save(auth.get());
            return true;
        } else {
            throw new ElasticManagerException(ErrorType.USER_NOT_FOUND);
        }
    }
}
