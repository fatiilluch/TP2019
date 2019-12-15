package domain;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.persistence.*;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import db.EntidadPersistente;

@Entity
@Table(name = "Imagen")
public class Imagen extends EntidadPersistente{
	
	@Transient
	private File imagen;
	
	@Transient
	private int altura;
	
	@Transient
	private int ancho;

	@Column(name = "directorio")
	private String directorioDeAlojamiento;
	
	@Column(name = "pathGenerado")
	private String pathGenerado;
	
	@Column(name = "pathOriginal")
	private String pathOriginal;

	public Imagen(){}

	public Imagen(String pathImagen) {
		this.pathOriginal = pathImagen;
		this.imagen = new File(pathImagen);
		this.altura =500;
		this.ancho = 500;
		this.directorioDeAlojamiento = "prendas\\images";
		this.pathGenerado = this.directorioDeAlojamiento+ nombreDeImagen() +"." +extension();
	}
	
	
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setDirectorioDeAlojamiento(String directorioDeAlojamiento) {
		this.directorioDeAlojamiento = directorioDeAlojamiento;
	}

	public String getDirectorioDeAlojamiento(){return directorioDeAlojamiento;}

	public String getPathGenerado(){return pathGenerado;}

	public String getImagen(){ return imagen.getPath();}
	
	public void cargarImagen() {
		String nombreImg = nombreDeImagen();
		BufferedImage imagenOrigen = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);

		try {
			imagenOrigen = ImageIO.read(this.imagen);
			BufferedImage imagenDestino = new BufferedImage( ancho, altura, BufferedImage.TYPE_INT_RGB);
			Graphics2D modeloImagen = imagenDestino.createGraphics();
			
			modeloImagen.setComposite(AlphaComposite.Src);
		    modeloImagen.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    modeloImagen.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		    modeloImagen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		    modeloImagen.drawImage(imagenOrigen, 0, 0,ancho, altura, null);
		    modeloImagen.dispose();

			ImageIO.write(imagenDestino, "PNG", new File(this.directorioDeAlojamiento+ nombreImg +"." +extension()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String nombreDeImagen() {
		String nombreImg =  UUID.randomUUID().toString();
		return nombreImg;
	}
	
	private String extension() {
		String extension = FilenameUtils.getExtension(imagen.getPath());
		return extension;
	}	
}