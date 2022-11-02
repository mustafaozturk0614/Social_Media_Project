package com.bilgeadam.repository.entity;


import com.bilgeadam.repository.enums.Roles;
import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/**
 * Auth entitiy admin code ile status
 * status enum olacak
 * register dtoda admin code alacaðýz
 * controllermýzda ki register metodunu get mappingden postmappinge çekelim
 * ve bir kullanýcý kaydedelim
 *
 *2-username uniq olsun
 * exception pakateini ekleyelim
 * admin code belirleyip register metodumuzu ona göre güncelleyelim
 * (admin code doðruysa rolemuz admin olsun)
 * Mapper paketimizi yaratalým ve IuserMapper oluþturalým
 * exceptionlarýda gerekli yerlerde kullanalým
 *3- Validsyon için gerekli baðýmlýlýklarý ekleyelim ve register reguestdto ya
 * valýdasyon kosullaraý ekleyelým  pasword boyutunu belirleyeim username boyutu
 * swqggerý da ekleyelim
 *
 * 4- login metodu yazalým controller ve servicede
 * login için bir request dto oluþturalým
 * birde response dto olusturalým
 * bu oluþturdumuz dtolarýda metodumuzda kullanalým
 *
 */
public class Auth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    private  String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles role=Roles.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status=Status.PENDING;
    private String activatedCode;

}
