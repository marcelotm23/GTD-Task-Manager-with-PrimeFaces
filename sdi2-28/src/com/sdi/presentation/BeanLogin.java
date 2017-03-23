package com.sdi.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sdi.business.UserService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.presentation.util.MessageToUser;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 6L;
	private String username = "";
	private String password = "";
	private String result = "login_form_result_valid";

	public BeanLogin() {
		System.out.println("BeanLogin - No existia");
	}

	public String verify() {
		UserService login = Factories.services.createUserService();
		User user = login.findLoggableUser(username, password);
		if (user != null) {
			putUserInSession(user);
			if(user.getIsAdmin())
				return "admin";
			else
				return "user";
		}
		MessageToUser.writeGrowlMessageERROR("login_form_result_error");
		return "fallo";
	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(false);
		if(session!=null){
			session.invalidate();
			return "exito";
		}
		return "error";
		
	}
	
	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}