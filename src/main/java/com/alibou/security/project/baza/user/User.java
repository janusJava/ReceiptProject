package com.alibou.security.project.baza.user;

import com.alibou.security.project.baza.model.Receipt;
import com.alibou.security.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column()
  private String firstName;

  @Column()
  private String lastName;

  @Column()
  private String email;

  @Column()
  @JsonIgnore
  private String password;

  @Enumerated(EnumType.STRING)
  @JsonIgnore
  private Role role;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Token> tokens;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  List<Receipt> receiptList;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
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
}
