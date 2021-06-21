package yuce.kerem.thesis.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 2:36 PM
 */

@Entity
@Table(name = "APP_USER")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Password")
    private String password;

    @Column(name = "Username")
    private String username;

    @Column(name = "Locked")
    private boolean locked;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "User")
    private User user;

    public AppUser(String password, String username, boolean locked, User user) {
        this.password = password;
        this.username = username;
        this.locked = locked;
        this.user = user;
    }

    public AppUser() {
        // Dummy constructor...
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

}
