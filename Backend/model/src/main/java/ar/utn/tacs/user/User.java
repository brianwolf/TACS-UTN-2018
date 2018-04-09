package ar.utn.tacs.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

//ESTO ES SOLO PARA PROBAR HACER UN SELECT
@NamedNativeQueries({
		@NamedNativeQuery(name = "findUsers", query = "select * from usuario", resultClass = User.class) })
@Entity
@Table(name = "USUARIO")
public class User {

	@Id
	@Column(name = "id")
	private Integer id;

	private String nick;
	private String pass;

	@Column(name = "intentos_login")
	private Integer intentosLogin;
	private Boolean activo;
	private String nombre;
	private String apellido;

	@Transient
	private TipoDocumento tipoDocumento;

	//DEJO PROPERTIES CON ESTE TAG PARA QUE HIBERNATE NO LAS MAPEE
	@Transient
	private Date fechaNacimiento;
	
	private String direccion;
	private String telefono;
	private String email;

	@Transient
	private char sexo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Integer getIntentosLogin() {
		return intentosLogin;
	}

	public void setIntentosLogin(Integer intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
