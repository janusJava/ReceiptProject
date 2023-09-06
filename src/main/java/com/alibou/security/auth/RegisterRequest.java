package com.alibou.security.auth;

import com.alibou.security.project.baza.model.Receipt;
import com.alibou.security.project.baza.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Role role;
  private List<Receipt> receiptList;
}
