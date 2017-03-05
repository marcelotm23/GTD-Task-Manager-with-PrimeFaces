package com.sdi.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent; 

import com.sdi.model.Alumno;
import com.sdi.model.User;

@ManagedBean(name="usuario")
@SessionScoped
public class BeanUsuario extends User implements Serializable {
  private static final long serialVersionUID = 55556L;
  
  public BeanUsuario() {
    
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
  
        
} 
