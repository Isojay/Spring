package com.login.LOGIN.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class User {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long userId;

	    private String userName;

	    private String userEmail;

	    private String userPassword;

	    private boolean isEnabled;

		public User() {
			super();
		}

		public User(Long userId, String userName, String userEmail, String userPassword, boolean isEnabled) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userEmail = userEmail;
			this.userPassword = userPassword;
			this.isEnabled = isEnabled;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}

		public boolean isEnabled() {
			return isEnabled;
		}

		public void setEnabled(boolean enabled) {
			this.isEnabled = enabled;
		}
	    
}
