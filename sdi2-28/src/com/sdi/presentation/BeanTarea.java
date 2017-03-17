package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.model.Task;

@ManagedBean(name = "tarea")
@SessionScoped
public class BeanTarea extends Task implements Serializable {
	private static final long serialVersionUID = 55556L;

	public BeanTarea() {
	}

	// Este método es necesario para copiar la tarea a editar cuando
	// se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse
	// por un método editar en BeanUsuarios.
	public void setTarea(Task tarea) {
		if (tarea != null) {
			setId(tarea.getId());
			setCategoryId(tarea.getCategoryId());
			setTitle(tarea.getTitle());
			setComments(tarea.getComments());
			setPlanned(tarea.getPlanned());
			setUserId(tarea.getUserId());
			setFinished(tarea.getFinished());
			setCreated(tarea.getCreated());
		}
	}

}
