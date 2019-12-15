package domain;

import java.security.Guard;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.security.Guard;
import java.text.ParseException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.EntidadPersistente;
import domain.events.Evento;
import domain.events.Frecuencia;
import domain.events.GestorDeEventos;
import domain.events.Protocolo;
import domain.suggestions.GestorDeSugerencias;

@SuppressWarnings("unused")
@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente
{
	@Column(name = "dni")
	private int dni;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private List<Guardarropa> guardarropas;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id")
	private EstadoUsuario estado;
	
	@Column(name ="mail")
	private String mail;
	
	@Column(name = "telefono")
	private String telefono;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGuardarropaCompartido", referencedColumnName = "id")
	private GuardarropaCompartido guardarropaCompartido;
	
	@Transient
	private GestorDeAcciones gestor;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private Sensibilidad sensibilidad;

	public Usuario(String username, String password, Sensibilidad sensibilidad, String nombre, String apellido)
	{
		this.username = username;
		this.sensibilidad = sensibilidad;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.inicializarGuardarropas();
		this.estado = new UsuarioNormal();
	}
	
	public Usuario(){}

	private void inicializarGuardarropas() {
		this.guardarropas = new ArrayList<Guardarropa>();
	}

	public String getUsername() {
		return username;
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

	
	// hay que ver como encriptar
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public  int getDni(){ return dni; }

	public  void setDni(int dni){this.dni = dni;}


	public Sensibilidad getSensibilidad(){
		return this.sensibilidad;
	}

	public void setAumentarSensibilidad(Categoria categoria,double valor){
		this.sensibilidad.aumentarSensibilidad(categoria,valor);
	}

	public void setDisminuirSensibilidad(Categoria categoria,double valor){
		this.sensibilidad.disminuirSensibilidad(categoria,valor);
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public GestorDeAcciones getGestor() {
		return gestor;
	}

	public void setGestor(GestorDeAcciones gestor) {
		this.gestor = gestor;
	}

	public List<Guardarropa> getGuardarropas() {
		return guardarropas;
	}

	public void setGuardarropa(List<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}

	public void agregarGuardarropa(Guardarropa nuevoGuardarropa) {
		this.guardarropas.add(nuevoGuardarropa);
	}
	
	public int cantidadDeGuardarropas()
	{
		return this.guardarropas.size();
	}
	
	public boolean estaEnLista(Guardarropa guardarropas)
	{
		return this.guardarropas.contains(guardarropas);
	}
	
	//M�todos de Estado 	

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	public EstadoUsuario getEstado() {
		return estado;
	}

	public void agregarPrenda(Guardarropa guardarropa,Prenda prenda) {
		estado.agregarPrenda(guardarropa,prenda);
	}

	public void cambiarEstado() {
		estado.cambiarEstado(this);
	}

	public int cuantosGuardarropas() {
		return this.guardarropas.size();
	}

	public int cuantasPrendasTiene(Guardarropa guardarropa) {
		return guardarropa.cantidadDePrendas();
	}

	public Atuendo pedirAtuendoAGuardarropa(Guardarropa unGuardarropa,Protocolo protocolo) {
		return estado.pedirAtuendoAGuardarropa(unGuardarropa,protocolo, this);
	}

	public Atuendo pedirAtuendoEnUnDia(Guardarropa unGuardarropa, Protocolo protocolo, int dia){
		return estado.pedirAtuendoEnUnDia(unGuardarropa,protocolo,dia, this);
	}
	
	public int getMaximasPrendas(Guardarropa guardarropa) {
		return this.estado.getMaximasPrendas(guardarropa);
	}

	//---------metodos de los eventos------//

	public void setEvento(String nombre,String fecha,Protocolo tipoProtocolo,Frecuencia frecuencia) throws ParseException {
		GestorDeEventos.getInstance().crearEvento(nombre,fecha,tipoProtocolo,frecuencia,this);
	}

	public List<Evento> getEventos() {
		return GestorDeEventos.getInstance().getEventos(this);
	}

	public List<Evento> eventosProximos() {
		return GestorDeEventos.getInstance().eventosProximos(this);
	}

	public List<Evento> eventosParaSugerir(){
		return GestorDeEventos.getInstance().eventosParaSugerir(this);
	}

	public Evento getEventoPorNombre(String nombre) {
		return GestorDeEventos.getInstance().getEventoPorNombre(nombre,this);
	}

	//no deber�a existir xd
	public void actualizacionEventosManual(){
		GestorDeEventos.getInstance().actualizarEventos();
	}

	//-----------metodos de las sugerencias/notificaciones ----------//
	
	
	public void getSugerenciaDe(Evento evento) {
		GestorDeSugerencias.getInstance().mostrarAtuendos(
				GestorDeSugerencias.getInstance().getSugerenciaDe(evento)
		);
	}

	public Guardarropa getRandomGuardarropa()
	{
			Random rand = new Random();
			return guardarropas.get(rand.nextInt(guardarropas.size()));
	}

	public Atuendo pedirAtuendoGuardarropaRandom(){
		Guardarropa guardarropaRandom = getRandomGuardarropa();
		int intentos = 5;
		while(intentos != 0) {
			Atuendo atuendoADevolver = this.pedirAtuendoAGuardarropa(guardarropaRandom,Protocolo.Informal);
			if (guardarropaRandom.noTieneRechazado(atuendoADevolver)) {
				return atuendoADevolver;
			}
			System.out.println("ACA HAY UN INTENTO QUE ESTABA RECHAZADO");
		intentos=intentos-1;
		}
		System.out.println("ME QUEDE SIN INTENTOS");
		return null;
	}

	//Getters y Setters del mail y telefono
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void comparteGuardarropas(List<Usuario> otroUsuarios, Guardarropa unGuardarropa)
	{
		this.guardaRegistro(otroUsuarios, unGuardarropa);
		otroUsuarios.forEach(usuario -> usuario.agregarGuardarropa(unGuardarropa));
	}

	private void guardaRegistro(List<Usuario> otroUsuario, Guardarropa unGuardarropa) 
	{
		this.guardarropaCompartido = new GuardarropaCompartido(otroUsuario, unGuardarropa);
		// tendriamos que considerar que haya una lista de guardarropas compartidos?
	}
}