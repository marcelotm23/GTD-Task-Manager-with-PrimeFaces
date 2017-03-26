package com.sdi.tests.Tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sdi.tests.pageobjects.PO_AltaForm;
import com.sdi.tests.pageobjects.PO_Login;
import com.sdi.tests.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlantillaSDI2_Tests1617 {

	WebDriver driver;
	List<WebElement> elementos = null;

	public PlantillaSDI2_Tests1617() {
	}

	@Before
	public void run() {
		// Este código es para ejecutar con la versión portale de Firefox 46.0
		File pathToBinary = new File("S:\\firefox\\FirefoxPortable.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("http://localhost:8280/sdi2-28");// CAMBIAR A EXTERNO
		// Este código es para ejecutar con una versión instalada de Firex 46.0
		// driver = new FirefoxDriver();
		// driver.get("http://localhost:8180/sdi2-n");
	}

	@After
	public void end() {
		// Cerramos el navegador
		// driver.quit();
	}

	// PRUEBAS
	// ADMINISTRADOR
	// PR01: Autentificar correctamente al administrador.
	@Test
	public void prueba01() {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

	}

	// PR02: Fallo en la autenticación del administrador por introducir mal el
	// login.
	@Test
	public void prueba02() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "administrador1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);

		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");

		comprobarMensajeGrowl("Error", "Usuario o contraseña incorrectos");
	}

	// PR03: Fallo en la autenticación del administrador por introducir mal la
	// password.
	@Test
	public void prueba03() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "administrador1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);

		SeleniumUtils.textoPresentePagina(driver, "Usuario");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");

		comprobarMensajeGrowl("Error", "Usuario o contraseña incorrectos");
	}

	// PR04: Probar que la base de datos contiene los datos insertados con
	// conexión correcta a la base de datos.
	@Test
	public void prueba04() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("reiniciarBaseDeDatos")).click();

		Thread.sleep(500);

		comprobarMensajeGrowl("Info", "Se ha reiniciado la base de datos");

		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user2");
		SeleniumUtils.textoPresentePagina(driver, "user3");
		SeleniumUtils.textoPresentePagina(driver, "user1@user.com");
		SeleniumUtils.textoPresentePagina(driver, "user2@user.com");
		SeleniumUtils.textoPresentePagina(driver, "user3@user.com");
		SeleniumUtils.textoPresentePagina(driver, "ENABLED");
		SeleniumUtils.textoNoPresentePagina(driver, "DISABLED");

		driver.findElement(By.id("form-pie:j_idt32")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		new PO_Login().rellenaFormulario(driver, "user1", "user1");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		comprobarTareasInbox();

		driver.get("http://localhost:8280/sdi2-28"); // CAMBIAR A EXTERNO
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		comprobarTareasInbox();

		driver.get("http://localhost:8280/sdi2-28"); // CAMBIAR A EXTERNO
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		new PO_Login().rellenaFormulario(driver, "user3", "user3");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		comprobarTareasInbox();
	}

	// PR05: Visualizar correctamente la lista de usuarios normales.
	@Test
	public void prueba05() {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user2");
		SeleniumUtils.textoPresentePagina(driver, "user3");

		SeleniumUtils.textoPresentePagina(driver, "user1@user.com");
		SeleniumUtils.textoPresentePagina(driver, "user2@user.com");
		SeleniumUtils.textoPresentePagina(driver, "user3@user.com");
	}

	// PR06: Cambiar el estado de un usuario de ENABLED a DISABLED. Y tratar de
	// entrar con el usuario que se desactivado.
	@Test
	public void prueba06() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("tablalistado:2:cambiarStatus")).click();

		comprobarMensajeGrowl("Info",
				"Se ha modificado el estado de un usuario");

		driver.get("http://localhost:8280/sdi2-28");// CAMBIAR A EXTERNO
		new PO_Login().rellenaFormulario(driver, "user3", "user3");

		comprobarMensajeGrowl("Error", "Usuario o contraseña incorrectos");
	}

	// PR07: Cambiar el estado de un usuario a DISABLED a ENABLED. Y Y tratar de
	// entrar con el usuario que se ha activado.
	@Test
	public void prueba07() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("tablalistado:2:cambiarStatus")).click();

		comprobarMensajeGrowl("Info",
				"Se ha modificado el estado de un usuario");

		driver.get("http://localhost:8280/sdi2-28");// CAMBIAR A EXTERNO
		new PO_Login().rellenaFormulario(driver, "user3", "user3");

		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");
	}

	// PR08: Ordenar por Login
	@Test
	public void prueba08() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("tablalistado:Login")).click();

		String login = "user1user2user3";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			String u = driver
					.findElement(By.id("tablalistado:" + i + ":login"))
					.getText();
			sb.append(u);
		}
		assertEquals(login, sb.toString());

		driver.findElement(By.id("tablalistado:Login")).click();

		Thread.sleep(150);

		login = "user3user2user1";
		sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			String u = driver
					.findElement(By.id("tablalistado:" + i + ":login"))
					.getText();
			sb.append(u);
		}

		assertEquals(login, sb.toString());
	}

	// PR09: Ordenar por Email
	@Test
	public void prueba09() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("tablalistado:Email")).click();

		String email = "user1@user.comuser2@user.comuser3@user.com";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			String u = driver
					.findElement(By.id("tablalistado:" + i + ":email"))
					.getText();
			sb.append(u);
		}

		assertEquals(email, sb.toString());

		driver.findElement(By.id("tablalistado:Email")).click();

		Thread.sleep(150);

		email = "user3@user.comuser2@user.comuser1@user.com";
		sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			String u = driver
					.findElement(By.id("tablalistado:" + i + ":email"))
					.getText();
			sb.append(u);
		}

		assertEquals(email, sb.toString());
	}

	// PR10: Ordenar por Status
	@Test
	public void prueba10() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");
		SeleniumUtils.textoNoPresentePagina(driver, "DISABLED");

		String login = driver.findElement(By.id("tablalistado:0:login"))
				.getText();
		assertEquals("user1", login);

		driver.findElement(By.id("tablalistado:0:cambiarStatus")).click();
		Thread.sleep(100);
		driver.findElement(By.id("tablalistado:Status")).click();
		Thread.sleep(200);

		String u = driver.findElement(By.id("tablalistado:2:login")).getText();

		assertEquals(login, u);

		driver.findElement(By.id("tablalistado:2:cambiarStatus")).click();
	}

	// PR11: Borrar una cuenta de usuario normal y datos relacionados.
	@Test
	public void prueba11() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		// Vamos a eliminar el user3
		driver.findElement(By.id("tablalistado:2:eliminar")).click();
		Thread.sleep(500);

		// Boton de SÍ de la confirmación
		driver.findElement(By.id("tablalistado:2:j_idt26")).click();

		Thread.sleep(200);
		comprobarMensajeGrowl("Info", "Se ha eliminado un usuario");
		SeleniumUtils.textoNoPresentePagina(driver, "user3");

		driver.get("http://localhost:8280/sdi2-28");// CAMBIAR A EXTERNO
		new PO_Login().rellenaFormulario(driver, "user3", "user3");

		comprobarMensajeGrowl("Error", "Usuario o contraseña incorrectos");
	}

	// PR12: Crear una cuenta de usuario normal con datos válidos.
	@Test
	public void prueba12() {

		driver.findElement(By.id("form-principal:linkRegistrarse")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Crear nueva cuenta");
		// Vamos a rellenar el formulario
		String nombreUsuario = creaNombreUsuarioRandom();
		new PO_AltaForm().rellenaFormulario(driver, nombreUsuario
				+ "@prueba12.com", nombreUsuario, "prueba12", "prueba12");

		// Esperamos a que se cargue la pagina de login
		// concretamente los campos de login
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginFields", 10);

		// Comprobamos que aparezca la pantalla de login
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
	}

	// PR13: Crear una cuenta de usuario normal con login repetido.
	@Test
	public void prueba13() throws InterruptedException {
		driver.findElement(By.id("form-principal:linkRegistrarse")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Crear nueva cuenta");
		// Vamos a rellenar el formulario
		String nombreUsuario = "usuario1";
		new PO_AltaForm().rellenaFormulario(driver, nombreUsuario
				+ "@prueba13.com", nombreUsuario, "prueba13", "prueba13");

		// Esperamos a que se cargue la pagina de registro
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				20);

		// Comprobamos que aparezca la pantalla de registro con el error

		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Login");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
		SeleniumUtils.textoPresentePagina(driver, "Repita la contraseña");

		// SeleniumUtils
		// .EsperaCargaPagina(driver, "id", "form-cabecera:growl", 10);
		// SeleniumUtils.textoPresentePagina(driver, "Error");
		// SeleniumUtils.textoPresentePagina(driver,
		// "El login del usuario ya está registrado");
		comprobarMensajeGrowl("Error",
				"El login del usuario ya está registrado");
	}

	// PR14: Crear una cuenta de usuario normal con Email incorrecto.
	@Test
	public void prueba14() {
		driver.findElement(By.id("form-principal:linkRegistrarse")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Crear nueva cuenta");
		// Vamos a rellenar el formulario
		String nombreUsuario = creaNombreUsuarioRandom();
		new PO_AltaForm().rellenaFormulario(driver, nombreUsuario
				+ "prueba14.com", nombreUsuario, "prueba14", "prueba14");

		// Esperamos a que se cargue la pagina de registro
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);

		// Comprobamos que aparezca la pantalla de registro con el mensaje de
		// error
		SeleniumUtils.textoPresentePagina(driver,
				"El campo \"Correo\" presenta "
						+ "formato inválido (usuario@servidor.dominio)");
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Login");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
		SeleniumUtils.textoPresentePagina(driver, "Repita la contraseña");
	}

	// PR15: Crear una cuenta de usuario normal con Password incorrecta.
	@Test
	public void prueba15() {
		driver.findElement(By.id("form-principal:linkRegistrarse")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Crear nueva cuenta");
		// Vamos a rellenar el formulario
		String nombreUsuario = creaNombreUsuarioRandom();
		new PO_AltaForm().rellenaFormulario(driver, nombreUsuario
				+ "@prueba15.com", nombreUsuario, "15", "15");

		// Esperamos a que se cargue la pagina de registro
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Crear nueva cuenta",
				10);

		// Comprobamos que aparezca la pantalla de registro con el mensaje de
		// error
		SeleniumUtils
				.textoPresentePagina(
						driver,
						"El campo \"Password\" debe tener números, letras y una longitud miníma de 8 caracteres");
		SeleniumUtils
				.textoPresentePagina(
						driver,
						"El campo \"Repita la password\" "
								+ "debe tener números, letras, una longitud miníma de 8 caracteres "
								+ "y ser equivalente a la anterior");

		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Login");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña");
		SeleniumUtils.textoPresentePagina(driver, "Repita la contraseña");
	}

	// USUARIO
	// PR16: Comprobar que en Inbox sólo aparecen listadas las tareas sin
	// categoría y que son las que tienen que. Usar paginación navegando por las
	// tres páginas.
	@Test
	public void prueba16() throws InterruptedException {

		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		comprobarTareasInbox();
	}

	// PR17: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba17() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos en el criterio de ordenacion
		Thread.sleep(1000); // Esta espera es para poder el efecto de ordenación
		// Ordenación por fecha planeada ascendente
		elementos.get(2).click();
		SeleniumUtils.textoPresentePagina(driver, "tarea1");
		SeleniumUtils.textoNoPresentePagina(driver, "tarea19");
		Thread.sleep(1000); // Esta espera es para poder el efecto de ordenación
		// Ordenación por fecha planeada descendente
		elementos.get(2).click();
		Thread.sleep(1000);
		// Presente una tarea de las más lejanas
		SeleniumUtils.textoPresentePagina(driver, "tarea19");
		// No presente una tarea de las más cercanas
		SeleniumUtils.textoNoPresentePagina(driver, "tarea3");
	}

	// PR18: Funcionamiento correcto del filtrado.
	@Test
	public void prueba18() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		driver.findElement(By.id("formlistado:tablalistado:filtro:filter"))
				.click();
		driver.findElement(By.id("formlistado:tablalistado:filtro:filter"))
				.clear();
		driver.findElement(By.id("formlistado:tablalistado:filtro:filter"))
				.sendKeys("tarea20");
		Thread.sleep(1000);
		By busqueda = By.xpath("//tr[contains(@class, 'ui-widget-content')"
				+ " and contains(@role, 'row')]");
		List<WebElement> campos = driver.findElements(busqueda);
		assertEquals(1, campos.size());
		assertTrue("Filtrado incorrecto", campos.size() == 1);
		Thread.sleep(1000);
		driver.findElement(By.id("formlistado:tablalistado:filtro:filter"))
				.clear();
		driver.findElement(By.id("formlistado:tablalistado:filtro:filter"))
				.sendKeys("tarea");
		Thread.sleep(1000);
		campos = driver.findElements(busqueda);
		assertEquals(8, campos.size());
		assertTrue("Filtrado incorrecto", campos.size() == 8);
	}

	// PR19: Funcionamiento correcto de la ordenación por categoría.
	@Test
	public void prueba19() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		driver.findElement(By.linkText("Hoy")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoNoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos en el criterio de ordenacion
		Thread.sleep(1000); // Esta espera es para poder el efecto de ordenación
		// Ordenación por categoría ascendente
		elementos.get(0).click();
		WebElement primeraCategoriaTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:categoria_tarea"));

		assertTrue("La categoría no es vacía", primeraCategoriaTarea.getText()
				.isEmpty());
		Thread.sleep(1000); // Esta espera es para poder el efecto de ordenación
		// Ordenación por categoría descendente
		elementos.get(0).click();
		Thread.sleep(1000);
		primeraCategoriaTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:categoria_tarea"));
		assertTrue("La categoría no es categoria3", primeraCategoriaTarea
				.getText().equals("categoria3"));

	}

	// PR20: Funcionamiento correcto de la ordenación por fecha planeada.
	@Test
	public void prueba20() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		driver.findElement(By.linkText("Hoy")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoNoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos en el criterio de ordenacion
		// Ordenación por fecha planeada ascendente
		elementos.get(3).click();
		Thread.sleep(500);
		WebElement primeraCategoriaTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:categoria_tarea"));
		WebElement primerTituloTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:titulo_tarea"));
		// La primera categoría que debería salir sería categoria1
		assertFalse("La categoría es vacía", primeraCategoriaTarea.getText()
				.isEmpty());
		assertEquals("categoria1", primeraCategoriaTarea.getText());
		// La primera tarea debería ser tarea21
		assertFalse("La tarea no es la esperada", primerTituloTarea.getText()
				.equals("tarea1"));
		assertEquals("tarea21", primerTituloTarea.getText());
		// Ordenación por fecha planeada descendente
		elementos.get(3).click();
		Thread.sleep(500);
		primeraCategoriaTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:categoria_tarea"));
		primerTituloTarea = driver.findElement(By
				.id("formlistado:tablalistado:0:titulo_tarea"));
		// La primera categoría que debería salir vacía
		assertTrue("La categoría no es vacía", primeraCategoriaTarea.getText()
				.isEmpty());
		assertEquals("", primeraCategoriaTarea.getText());
		// La primera tarea debería ser tarea1
		assertFalse("La tarea no es la esperada", primerTituloTarea.getText()
				.equals("tarea21"));
		assertEquals("tarea1", primerTituloTarea.getText());
	}

	// PR21: Comprobar que las tareas que no están en rojo son las de hoy y
	// además las que deben ser.
	@Test
	public void prueba21() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		driver.findElement(By.linkText("Hoy")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoNoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos en el criterio de ordenacion
		// Ordenación por fecha planeada ascendente
		elementos.get(3).click();
		Thread.sleep(500);
		// Ordenación por fecha planeada descendente
		elementos.get(3).click();
		Thread.sleep(500);
		By busqueda = By.xpath("//tr[contains(@class, 'ui-widget-content')"
				+ " and contains(@role, 'row')]");
		List<WebElement> rows = driver.findElements(busqueda);

		// Comprobación del color rojo
		WebElement fechaPlaneadaTarea;
		By tituloTareaBusq;
		By fechaPlaneadaBusq = By.className("columnColorDelayed");
		List<String> titulos = new ArrayList<String>();
		for (int i = 0; i < rows.size(); i++) {
			try {
				fechaPlaneadaTarea = rows.get(i).findElement(fechaPlaneadaBusq);
			} catch (NoSuchElementException e) {
				fechaPlaneadaTarea = null;
			}
			// Las diez primeras tareas debe ser nula la busqueda
			// ya que como no son retrasadas no tendrán asociada la clase
			// para cambiarle el color rojo, en caso contrario no será nula
			if (i < 10) {
				assertNull("Tiene la clase de estilo rojo asociada(fila: " + i
						+ ")", fechaPlaneadaTarea);
				tituloTareaBusq = By.id("formlistado:tablalistado:" + i
						+ ":titulo_tarea");
				titulos.add(rows.get(i).findElement(tituloTareaBusq).getText());
			} else {
				assertNotNull(
						"No tiene la clase de estilo rojo asociada(fila: " + i
								+ ")", fechaPlaneadaTarea);
			}
		}
		// Comprobación de que las tareas no retrasadas son las de hoy y
		// las que tienen que ser
		// Que son 10 (de la tarea 1 a la 10)
		assertEquals(10, titulos.size());
		for (int i = 1; i < 11; i++) {
			assertTrue("La tarea no es la que tenia que ser",
					titulos.contains("tarea" + i));
		}

	}

	// PR22: Comprobar que las tareas retrasadas están en rojo y son las que
	// deben ser.
	@Test
	public void prueba22() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		driver.findElement(By.linkText("Hoy")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoNoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"class", "sortable-column-icon", 2);

		// Pinchamos en el criterio de ordenacion
		// Ordenación por fecha planeada ascendente
		elementos.get(3).click();
		Thread.sleep(500);

		By busqueda = By.xpath("//tr[contains(@class, 'ui-widget-content')"
				+ " and contains(@role, 'row')]");
		List<WebElement> rows = driver.findElements(busqueda);

		// Comprobación del color rojo
		WebElement fechaPlaneadaTarea;
		By tituloTareaBusq;
		By fechaPlaneadaBusq = By.className("columnColorDelayed");
		List<String> titulos = new ArrayList<String>();
		for (int i = 0; i < rows.size(); i++) {
			try {
				fechaPlaneadaTarea = rows.get(i).findElement(fechaPlaneadaBusq);
			} catch (NoSuchElementException e) {
				fechaPlaneadaTarea = null;
			}
			// Las diez primeras tareas no debe ser nula la busqueda
			// ya que como son retrasadas tendrán asociada la clase
			// para cambiarle el color rojo, en caso contrario si será nula
			if (i < 10) {
				assertNotNull(
						"No tiene la clase de estilo rojo asociada(fila: " + i
								+ ")", fechaPlaneadaTarea);
				tituloTareaBusq = By.id("formlistado:tablalistado:" + i
						+ ":titulo_tarea");
				titulos.add(rows.get(i).findElement(tituloTareaBusq).getText());
			} else {
				assertNull("Tiene la clase de estilo rojo asociada(fila: " + i
						+ ")", fechaPlaneadaTarea);
			}
		}
		// Comprobación de que las tareas retrasadas son las que tienen que ser
		// Que son 10 (de la tarea 21 a la 30)
		assertEquals(10, titulos.size());
		for (int i = 21; i < 31; i++) {
			assertTrue("La tarea no es la que tenia que ser",
					titulos.contains("tarea" + i));
		}

	}

	// PR23: Comprobar que las tareas de hoy y futuras no están en rojo y que
	// son las que deben ser.
	@Test
	public void prueba23() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		driver.findElement(By.linkText("Semana")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoNoPresentePagina(driver, "Ocultar finalizadas");
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		By busqueda = By.xpath("//tr[contains(@class, 'ui-widget-content')"
				+ " and contains(@role, 'row')]");
		List<WebElement> rows = driver.findElements(busqueda);

		// Comprobación del color rojo
		WebElement categoriaTarea;
		By tituloTareaBusq;
		By categoriaBusq = By.className("columnColorDelayed");
		List<String> titulos = new ArrayList<String>();
		for (int i = 0; i < rows.size(); i++) {
			try {
				categoriaTarea = rows.get(i).findElement(categoriaBusq);
			} catch (NoSuchElementException e) {
				categoriaTarea = null;
			}
			// Las veinte primeras tareas debe ser nula la busqueda
			// ya que como no son retrasadas no tendrán asociada la clase
			// para cambiarle el color rojo, en caso contrario no será nula
			if (i < 20) {
				assertNull("Tiene la clase de estilo rojo asociada(fila: " + i
						+ ")", categoriaTarea);
				tituloTareaBusq = By.id("formlistado:tablalistado:" + i
						+ ":titulo_tarea");
				titulos.add(rows.get(i).findElement(tituloTareaBusq).getText());
			} else {
				assertNotNull(
						"No tiene la clase de estilo rojo asociada(fila: " + i
								+ ")", categoriaTarea);
			}
		}
		// Comprobación de que las tareas no retrasadas son las de
		// esta semana y las que tienen que ser
		// Que son 10 (de la tarea 1 a la 10)
		assertEquals(20, titulos.size());
		for (int i = 1; i < 21; i++) {
			assertTrue("La tarea no es la que tenia que ser",
					titulos.contains("tarea" + i));
		}
	}

	// PR24: Funcionamiento correcto de la ordenación por día.
	@Test
	public void prueba24() {
		assertTrue(false);
	}

	// PR25: Funcionamiento correcto de la ordenación por nombre.
	@Test
	public void prueba25() {
		assertTrue(false);
	}

	// PR26: Confirmar una tarea, inhabilitar el filtro de tareas terminadas, ir
	// a la pagina donde está la tarea terminada y comprobar que se muestra.
	@Test
	public void prueba26() {
		assertTrue(false);
	}

	// PR27: Crear una tarea sin categoría y comprobar que se muestra en la
	// lista Inbox.
	@Test
	public void prueba27() {
		assertTrue(false);
	}

	// PR28: Crear una tarea con categoría categoria1 y fecha planeada Hoy y
	// comprobar que se muestra en la lista Hoy.
	@Test
	public void prueba28() {
		assertTrue(false);
	}

	// PR29: Crear una tarea con categoría categoria1 y fecha planeada posterior
	// a Hoy y comprobar que se muestra en la lista Semana.
	@Test
	public void prueba29() {
		assertTrue(false);
	}

	// PR30: Editar el nombre, y categoría de una tarea (se le cambia a
	// categoría1) de la lista Inbox y comprobar que las tres pseudolista se
	// refresca correctamente.
	@Test
	public void prueba30() {
		assertTrue(false);
	}

	// PR31: Editar el nombre, y categoría (Se cambia a sin categoría) de una
	// tarea de la lista Hoy y comprobar que las tres pseudolistas se refrescan
	// correctamente.
	@Test
	public void prueba31() {
		assertTrue(false);
	}

	// PR32: Marcar una tarea como finalizada. Comprobar que desaparece de las
	// tres pseudolistas.
	@Test
	public void prueba32() {
		assertTrue(false);
	}

	// PR33: Salir de sesión desde cuenta de administrador.
	@Test
	public void prueba33() {
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Listado de usuarios",
				10);
		SeleniumUtils.textoPresentePagina(driver, "Correo");
		SeleniumUtils.textoPresentePagina(driver, "Nombre de usuario");
		SeleniumUtils.textoPresentePagina(driver, "Estado");
		SeleniumUtils.textoPresentePagina(driver, "Cambiar estado");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar usuario");
		SeleniumUtils.textoPresentePagina(driver, "Reiniciar base de datos");

		driver.findElement(By.id("form-pie:j_idt29")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");

	}

	// PR34: Salir de sesión desde cuenta de usuario normal.
	@Test
	public void prueba34() throws InterruptedException {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		driver.findElement(By.id("form-cabecera:opcionesUsuarios")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:cerrarSesion")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
	}

	// PR35: Cambio del idioma por defecto a un segundo idioma. (Probar algunas
	// vistas)
	@Test
	public void prueba35() throws InterruptedException {
		// Probamos al vista del login
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
		SeleniumUtils.textoPresentePagina(driver,
				"¿No tienes cuenta? Crea una.");
		SeleniumUtils.textoPresentePagina(driver, "Registrarse");

		Thread.sleep(500);
		// Ponemos el idioma secundario
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:english")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoPresentePagina(driver, "User:");
		SeleniumUtils.textoPresentePagina(driver, "Password:");
		SeleniumUtils.textoPresentePagina(driver, "Register");

		// Probamos la vista de usuarios normales
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "List of tasks", 10);
		SeleniumUtils.textoPresentePagina(driver, "Title");
		SeleniumUtils.textoPresentePagina(driver, "Comments");
		SeleniumUtils.textoPresentePagina(driver, "Planned date");
		SeleniumUtils.textoPresentePagina(driver, "Ended date");
		SeleniumUtils.textoPresentePagina(driver, "Edit");
		SeleniumUtils.textoPresentePagina(driver, "Finalize");
		
		driver.get("http://localhost:8280/sdi2-28");// CAMBIAR A EXTERNO
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Login", 10);
		new PO_Login().rellenaFormulario(driver, "admin1", "admin1");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "User list", 10);
		SeleniumUtils.textoPresentePagina(driver, "Email");
		SeleniumUtils.textoPresentePagina(driver, "Login");
		SeleniumUtils.textoPresentePagina(driver, "Status");
		SeleniumUtils.textoPresentePagina(driver, "Change Status");
		SeleniumUtils.textoPresentePagina(driver, "Delete user");
		
	}

	// PR36: Cambio del idioma por defecto a un segundo idioma y vuelta al
	// idioma por defecto. (Probar algunas vistas)
	@Test
	public void prueba36() throws InterruptedException {
		// Probamos al vista del login
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
		SeleniumUtils.textoPresentePagina(driver,
				"¿No tienes cuenta? Crea una.");
		SeleniumUtils.textoPresentePagina(driver, "Registrarse");

		Thread.sleep(500);
		// Ponemos el idioma secundario
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:english")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoPresentePagina(driver, "User:");
		SeleniumUtils.textoPresentePagina(driver, "Password:");
		SeleniumUtils.textoPresentePagina(driver, "Register");

		// Volvamos al idioma por defecto
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:spanish")).click();
		Thread.sleep(500);
		SeleniumUtils.textoPresentePagina(driver, "Usuario:");
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
		SeleniumUtils.textoPresentePagina(driver,
				"¿No tienes cuenta? Crea una.");
		SeleniumUtils.textoPresentePagina(driver, "Registrarse");

		// Probamos la vista de usuarios normales
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		// Ponemos el idioma secundario
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:english")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoPresentePagina(driver, "Title");
		SeleniumUtils.textoPresentePagina(driver, "Comments");
		SeleniumUtils.textoPresentePagina(driver, "Planned date");
		SeleniumUtils.textoPresentePagina(driver, "Ended date");
		SeleniumUtils.textoPresentePagina(driver, "Edit");
		SeleniumUtils.textoPresentePagina(driver, "Finalize");

		// Volvamos al idioma por defecto
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		driver.findElement(By.id("form-cabecera:idiomas")).click();
		Thread.sleep(500);
		driver.findElement(By.id("form-cabecera:spanish")).click();
		Thread.sleep(1000);
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");
	}

	// PR37: Intento de acceso a un URL privado de administrador con un usuario
	// autenticado como usuario normal.
	@Test
	public void prueba37() {
		new PO_Login().rellenaFormulario(driver, "user2", "user2");
		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

		driver.get("http://localhost:8280/sdi2-28/restrictedAdmin/listadoUsuarios.xhtml");

		SeleniumUtils
				.EsperaCargaPagina(driver, "text", "Listado de tareas", 10);
		SeleniumUtils.textoPresentePagina(driver, "Categoría");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Fecha planeada");
		SeleniumUtils.textoPresentePagina(driver, "Fecha finalizada");
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Finalizar");

	}

	// PR38: Intento de acceso a un URL privado de usuario normal con un usuario
	// no autenticado.
	@Test
	public void prueba38() {
		driver.get("http://localhost:8280/sdi2-28/restricted/listadoTareasInbox.xhtml");

		SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario:", 10);
		SeleniumUtils.textoPresentePagina(driver, "Contraseña:");
		SeleniumUtils.textoPresentePagina(driver,
				"¿No tienes cuenta? Crea una.");
	}

	public static String creaNombreUsuarioRandom() {
		char[] letras = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			char c = letras[random.nextInt(letras.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	private void comprobarMensajeGrowl(String titulo, String mensaje)
			throws InterruptedException {
		Thread.sleep(500);
		By contenidoGrowl = By
				.xpath("//div[contains(@class, 'ui-growl-message')]");
		WebElement growl = driver.findElement(contenidoGrowl);
		WebElement growlTitle = growl.findElement(By
				.xpath("//span[contains(@class, 'ui-growl-title')]"));
		WebElement growlMessage = growl.findElement(By
				.xpath("//div[contains(@class, 'ui-growl-message')]/p"));
		assertEquals(titulo, growlTitle.getText());
		assertEquals(mensaje, growlMessage.getText());
	}

	private void comprobarTareasInbox() throws InterruptedException {
		// Ordeno por título
		WebElement tarea = null;
		ArrayList<String> titulosTareas = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			tarea = driver.findElement(By.id("formlistado:tablalistado:" + i
					+ ":titulo_tarea"));
			titulosTareas.add(tarea.getText());
		}
		// 2ºpágina
		driver.findElement(
				By.xpath("//div[@id='formlistado:tablalistado_paginator_top']/span[3]/span[2]"))
				.click();
		Thread.sleep(1000);
		for (int i = 8; i < 16; i++) {
			tarea = driver.findElement(By.id("formlistado:tablalistado:" + i
					+ ":titulo_tarea"));
			titulosTareas.add(tarea.getText());
		}
		// 3ºpágina
		driver.findElement(
				By.xpath("//div[@id='formlistado:tablalistado_paginator_top']/span[3]/span[3]"))
				.click();
		Thread.sleep(1000);
		for (int i = 16; i < 20; i++) {
			tarea = driver.findElement(By.id("formlistado:tablalistado:" + i
					+ ":titulo_tarea"));
			titulosTareas.add(tarea.getText());
		}
		assertEquals(20, titulosTareas.size());
		for (int i = 0; i < titulosTareas.size(); i++) {
			assertTrue(titulosTareas.contains("tarea" + (i + 1)));
		}
	}

}