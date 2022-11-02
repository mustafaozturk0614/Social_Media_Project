package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.Roles;
import com.bilgeadam.repository.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth,Long> {

    @Query("select count(a.username)>0  from Auth as a  where a.username=?1")
    Boolean existUserName(String username);
    Optional<Auth> findOptionalByUsernameAndPassword(String username,String password);
///
    List<Auth> findAllByRole(Roles roles);

    @Query("select a from Auth a where a.status='ACTIVE' or a.status='PENDING'")
    Optional<List<Auth>> findAllActiveAndPendingAuth();

    List<Auth> findAllByStatusIn(List<Status> statusList);

}
