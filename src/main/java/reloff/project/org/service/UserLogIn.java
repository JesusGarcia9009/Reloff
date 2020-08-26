package reloff.project.org.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import reloff.project.org.entity.AppUser;

@SuppressWarnings("serial")
public class UserLogIn extends User {

    private final AppUser appUser;

    public UserLogIn(AppUser appUser, Collection<? extends GrantedAuthority> authorities) {
        super(appUser.getUserName(), appUser.getEncrytedPassword(), true, true, true, true, authorities);
        this.appUser = appUser;
    }

    public AppUser getAppUser(){
        return this.appUser;
    }

}