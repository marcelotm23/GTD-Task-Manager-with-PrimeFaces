package com.sdi.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sdi.model.User;
import com.sdi.model.types.UserStatus;

@ManagedBean(name="usuario")
@SessionScoped
public class BeanUsuario extends User implements Serializable {
  private static final long serialVersionUID = 55556L;
  
  public BeanUsuario() {
    iniciaAlumno(null);
  }
//Este método es necesario para copiar el alumno a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanAlumnos.
  public void setUsuario(User usuario) {
    setId(usuario.getId());
    setLogin(usuario.getLogin());
    setEmail(usuario.getEmail());
    setIsAdmin(usuario.getIsAdmin());
    setPassword(usuario.getPassword());
    setStatus(usuario.getStatus());
  }
  
  public void iniciaAlumno(ActionEvent event) {
          setId(null);
          setLogin("");
          setEmail("");
          setIsAdmin(false);
          setPassword("");
          setStatus(UserStatus.ENABLED);
    }       
        
} 
