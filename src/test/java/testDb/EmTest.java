package testDb;
import db.EntityManagerHelper;
import domain.*;
import domain.events.Frecuencia;
import domain.events.Protocolo;
import domain.suggestions.Sugerencia;
import repositories.RepositorioMolde;
import repositories.factories.FactoryRepositorioMolde;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import domain.events.Evento;
import java.util.Date;


public class EmTest
{

	//Usuarios
	Sensibilidad sensibilidadJazul = new Sensibilidad(2.0, 3.2);
    Sensibilidad sensibilidadAroco = new Sensibilidad(4.0, 9.3);
    Usuario jazul = new Usuario("jazul", "123456", sensibilidadJazul, "Julieta", "Azul");
    Usuario aroco = new Usuario("aroco", "123456", sensibilidadAroco, "Alejandro", "Aroco");
    
    Guardarropa guardarropaAroco = new Guardarropa("Informal Aroco");
    List<Guardarropa> guardarropas1 = new ArrayList<>();
    Guardarropa guardarropaJazul = new Guardarropa("Informal Jazul");
    List<Guardarropa> guardarropas2 = new ArrayList<>();

    List<Prenda> listPrendas = new ArrayList<>();
    List<Prenda> listPrendas2 = new ArrayList<>();
	List<Prenda> listPrendas3 = new ArrayList<>();

	List<Atuendo> listaAtuendoJazul = new ArrayList<>();
	List<Atuendo> listaAtuendoAroco = new ArrayList<>();
	List<Atuendo> listaAtuendoAroco2 = new ArrayList<>();

	RepositorioMolde repo;
	Molde molde1;
	Molde molde2;
	Molde molde3;
	Molde molde4;

    @Test
    public void persistirTiposDePrendas() throws ParseException {
		molde1 = new Molde();
		molde2 = new Molde();
		molde3 = new Molde();
		molde4 = new Molde();
		repo = FactoryRepositorioMolde.get();

		//Telas
		List<Tela> telaRemeras = new ArrayList<Tela>();
		List<Tela> telaSueter = new ArrayList<Tela>();
		List<Tela> telaCamperas = new ArrayList<Tela>();
		List<Tela> telaPantalonYPollera = new ArrayList<Tela>();
		List<Tela> telaCalzayBuzo = new ArrayList<Tela>();
		List<Tela> telaMusculosa = new ArrayList<Tela>();
		List<Tela> telaZapatilla = new ArrayList<Tela>();
		List<Tela> telaZapatoYSandalias = new ArrayList<Tela>();

		//Tipos de prendas
		List<TipoDePrenda> combinacionesRemera = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesSueter = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesCampera = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesPantalonLargo = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesPantalonCorto = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesBuzo = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesMusculosa = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesZapatilla = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesZapato = new ArrayList<TipoDePrenda>();
		List<TipoDePrenda> combinacionesSandalia = new ArrayList<TipoDePrenda>();

		telaRemeras.add(Tela.ALGODON);
		telaRemeras.add(Tela.SEDA);
		telaRemeras.add(Tela.POLIESTER);
		telaRemeras.add(Tela.LYCRA);

		telaSueter.add(Tela.ALGODON);
		telaSueter.add(Tela.POLIESTER);
		telaSueter.add(Tela.SEDA);

		telaCamperas.add(Tela.ALGODON);
		telaCamperas.add(Tela.SEDA);
		telaCamperas.add(Tela.POLIESTER);
		telaCamperas.add(Tela.NYLON);
		telaCamperas.add(Tela.CUERO);

		telaPantalonYPollera.add(Tela.ALGODON);
		telaPantalonYPollera.add(Tela.POLIESTER);
		telaPantalonYPollera.add(Tela.JEAN);
		telaPantalonYPollera.add(Tela.NYLON);
		telaPantalonYPollera.add(Tela.SEDA);

		telaCalzayBuzo.add(Tela.ALGODON);
		telaCalzayBuzo.add(Tela.POLIESTER);
		telaCalzayBuzo.add(Tela.LYCRA);
		telaCalzayBuzo.add(Tela.NYLON);

		telaMusculosa.add(Tela.ALGODON);
		telaMusculosa.add(Tela.LYCRA);

		telaZapatilla.add(Tela.CUERO);
		telaZapatilla.add(Tela.NYLON);

		telaZapatoYSandalias.add(Tela.CUERO);

		TipoDePrenda t1 = new TipoDePrenda("Remera cuello redondo manga corta", Categoria.Superior, 1, 5, telaRemeras, combinacionesRemera);
		TipoDePrenda t2 = new TipoDePrenda("Remera cuello redondo manga larga", Categoria.Superior, 1, 8, telaRemeras, combinacionesRemera);
		TipoDePrenda t3 = new TipoDePrenda("Remera escote en v manga corta", Categoria.Superior, 1, 5, telaRemeras, combinacionesRemera);
		TipoDePrenda t4 = new TipoDePrenda("Remera escote en v manga larga", Categoria.Superior, 1, 8, telaRemeras, combinacionesRemera);
		TipoDePrenda t5 = new TipoDePrenda("Sueter", Categoria.Superior, 2, 15, telaSueter, combinacionesSueter);
		TipoDePrenda t6 = new TipoDePrenda("Campera", Categoria.Superior, 3, 13, telaCamperas, combinacionesCampera);
		TipoDePrenda t7 = new TipoDePrenda("Pantalon largo", Categoria.Inferior, 1, 8, telaPantalonYPollera, combinacionesPantalonLargo);
		TipoDePrenda t8 = new TipoDePrenda("Pantalon corto", Categoria.Inferior, 1, 3, telaPantalonYPollera, combinacionesPantalonCorto);
		TipoDePrenda t9 = new TipoDePrenda("Bermuda", Categoria.Inferior, 1, 3, telaPantalonYPollera, combinacionesPantalonCorto);
		TipoDePrenda t10 = new TipoDePrenda("Pollera", Categoria.Inferior, 1, 3, telaPantalonYPollera, combinacionesPantalonCorto);
		TipoDePrenda t11 = new TipoDePrenda("Calza", Categoria.Inferior, 1, 5, telaCalzayBuzo, combinacionesPantalonCorto);
		TipoDePrenda t12 = new TipoDePrenda("Buzo", Categoria.Superior, 2, 13, telaCalzayBuzo, combinacionesBuzo);
		TipoDePrenda t13 = new TipoDePrenda("Musculosa", Categoria.Superior, 1, 3, telaMusculosa, combinacionesMusculosa);
		TipoDePrenda t14 = new TipoDePrenda("Zapatilla", Categoria.Calzado, 1, 0, telaZapatilla, combinacionesZapatilla);
		TipoDePrenda t15 = new TipoDePrenda("Zapato", Categoria.Calzado, 1, 0, telaZapatoYSandalias, combinacionesZapato);
		TipoDePrenda t16 = new TipoDePrenda("Sandalias", Categoria.Calzado, 1, 0, telaZapatoYSandalias, combinacionesSandalia);

		t1.setImagenPredeterminada("prendas\\images\\remeraCortaRedondo.png");
		t2.setImagenPredeterminada("prendas\\images\\remeraLargaRedondo.png");
		t3.setImagenPredeterminada("prendas\\images\\remeraCortaEnV.png");
		t4.setImagenPredeterminada("prendas\\images\\remeraLargaEnV.png");
		t5.setImagenPredeterminada("prendas\\images\\sueter.png");
		t6.setImagenPredeterminada("prendas\\images\\campera.png");
		t7.setImagenPredeterminada("prendas\\images\\pantalonLargo.png");
		t8.setImagenPredeterminada("prendas\\images\\pantalonCorto.png");
		t9.setImagenPredeterminada("prendas\\images\\bermuda.png");
		t10.setImagenPredeterminada("prendas\\images\\pollera.png");
		t11.setImagenPredeterminada("prendas\\images\\calza.png");
		t12.setImagenPredeterminada("prendas\\images\\buzo.png");
		t13.setImagenPredeterminada("prendas\\images\\musculosa.png");
		t14.setImagenPredeterminada("prendas\\images\\zapatilla.png");
		t15.setImagenPredeterminada("prendas\\images\\zapato.png");
		t16.setImagenPredeterminada("prendas\\images\\sandalias.png");

		combinacionesZapatilla.add(t1);
		combinacionesZapatilla.add(t2);
		combinacionesZapatilla.add(t3);
		combinacionesZapatilla.add(t4);
		combinacionesZapatilla.add(t5);
		combinacionesZapatilla.add(t6);
		combinacionesZapatilla.add(t7);
		combinacionesZapatilla.add(t8);
		combinacionesZapatilla.add(t9);
		combinacionesZapatilla.add(t10);
		combinacionesZapatilla.add(t11);
		combinacionesZapatilla.add(t12);
		combinacionesZapatilla.add(t13);
		combinacionesZapatilla.add(t14);
		combinacionesZapatilla.add(t15);
		combinacionesZapatilla.add(t16);

		combinacionesZapato.add(t7);
		combinacionesZapato.add(t10);

		combinacionesSandalia.add(t2);
		combinacionesSandalia.add(t7);
		combinacionesSandalia.add(t8);
		combinacionesSandalia.add(t10);


		combinacionesMusculosa.add(t12);
		combinacionesMusculosa.add(t6);
		combinacionesMusculosa.add(t8);
		combinacionesMusculosa.add(t11);
		combinacionesMusculosa.add(t10);

		combinacionesBuzo.add(t1);
		combinacionesBuzo.add(t2);
		combinacionesBuzo.add(t3);
		combinacionesBuzo.add(t4);
		combinacionesBuzo.add(t13);
		combinacionesBuzo.add(t7);
		combinacionesBuzo.add(t8);
		combinacionesBuzo.add(t9);
		combinacionesBuzo.add(t10);

		combinacionesPantalonCorto.add(t1);
		combinacionesPantalonCorto.add(t2);
		combinacionesPantalonCorto.add(t3);
		combinacionesPantalonCorto.add(t4);
		combinacionesPantalonCorto.add(t6);
		combinacionesPantalonCorto.add(t13);

		combinacionesPantalonLargo.add(t1);
		combinacionesPantalonLargo.add(t2);
		combinacionesPantalonLargo.add(t3);
		combinacionesPantalonLargo.add(t4);
		combinacionesPantalonLargo.add(t5);
		combinacionesPantalonLargo.add(t6);
		combinacionesPantalonLargo.add(t13);


		combinacionesCampera.add(t1);
		combinacionesCampera.add(t2);
		combinacionesCampera.add(t3);
		combinacionesCampera.add(t4);
		combinacionesCampera.add(t7);
		combinacionesCampera.add(t8);
		combinacionesCampera.add(t9);
		combinacionesCampera.add(t10);
		combinacionesCampera.add(t11);

		combinacionesRemera.add(t5);
		combinacionesRemera.add(t6);
		combinacionesRemera.add(t7);
		combinacionesRemera.add(t8);
		combinacionesRemera.add(t10);
		combinacionesRemera.add(t9);
		combinacionesRemera.add(t11);
		combinacionesRemera.add(t12);

		combinacionesSueter.add(t1);
		combinacionesSueter.add(t2);
		combinacionesSueter.add(t3);
		combinacionesSueter.add(t4);
		combinacionesSueter.add(t6);
		combinacionesSueter.add(t7);


		//Prendas
// 		Imagen imgRemeraRedondoCorta = new Imagen("imagenes\\mangaCortaRedondo.png");
// 		Imagen imgRemeraRedondoLarga = new Imagen("imagenes\\remeraLargaRedondo.png");
// 		Imagen imgRemeraVCorta = new Imagen("imagenes\\remeraCortaEnV.png");
// 		Imagen imgRemeraVLarga = new Imagen("imagenes\\remeraLargaEnV.png");
// 		Imagen imgMusculosa = new Imagen("imagenes\\musculosa.png");
// 		Imagen imgCampera = new Imagen("imagenes\\campera.png");
// 		Imagen imgSueter = new Imagen("imagenes\\sueter.png");
// 		Imagen imgBermuda = new Imagen("imagenes\\bermuda.png");
// 		Imagen imgPantalonLargo = new Imagen("imagenes\\pantalonLargo.png");
// 		Imagen imgZapatilla = new Imagen("imagenes\\zapatilla.png");
// 		Imagen imgZapatos = new Imagen("imagenes\\zapato.png");
// 		Imagen imgPollera = new Imagen("imagenes\\pollera.png");
// 		Imagen imgCalza = new Imagen("imagenes\\calza.png");
// 		Imagen imgSandalia = new Imagen("imagenes\\sandalia.png");
// 		Imagen imgBuzo = new Imagen("imagenes\\buzo.png");


		Prenda remeraCuelloRedondoMangaCorta = new Prenda(t1, Tela.ALGODON, Color.NEGRO, Protocolo.Informal, null);
		Prenda remeraEscoteVMangaCorta = new Prenda(t3, Tela.LYCRA, Color.BLANCO, Protocolo.Informal, null);
		Prenda musculosa = new Prenda(t13, Tela.LYCRA, Color.AMARILLO, Protocolo.Informal, null);
		Prenda campera = new Prenda(t6, Tela.CUERO, Color.BLANCO, Protocolo.Informal, null);
		Prenda sueter = new Prenda(t5, Tela.POLIESTER, Color.BLANCO, Protocolo.Informal, null);
		Prenda bermuda = new Prenda(t9, Tela.JEAN, Color.CELESTE, Protocolo.Informal, null);
		Prenda pantalonLargo = new Prenda(t7, Tela.NYLON, Color.GRIS, Protocolo.Informal, null);
		Prenda zapatillas = new Prenda(t14, Tela.NYLON, Color.BORDO, Protocolo.Informal, null);
		Prenda zapatos = new Prenda(t15, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);

		Prenda remeraCuelloRedondoML = new Prenda(t2, Tela.LYCRA, Color.AMARILLO, Protocolo.Informal, null);
		Prenda remeraEscoteVML = new Prenda(t4, Tela.ALGODON, Color.BLANCO, Protocolo.Informal, null);
		Prenda musculosa2 = new Prenda(t13, Tela.LYCRA, Color.VERDE, Protocolo.Informal, null);
		Prenda sueter2 = new Prenda(t5, Tela.POLIESTER, Color.GRIS, Protocolo.Informal, null);
		Prenda pollera = new Prenda(t10, Tela.SEDA, Color.NEGRO, Protocolo.Informal, null);
		Prenda calza = new Prenda(t11, Tela.NYLON, Color.NEGRO, Protocolo.Informal, null);
		Prenda buzo = new Prenda(t12, Tela.ALGODON, Color.BLANCO, Protocolo.Informal, null);
		Prenda sandalias = new Prenda(t16, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);
		Prenda zapatos2 = new Prenda(t15, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);

		List<Prenda> prendasAroco = new ArrayList<>();
		prendasAroco.add(remeraCuelloRedondoMangaCorta);
		prendasAroco.add(remeraEscoteVMangaCorta);
		prendasAroco.add(musculosa);
		prendasAroco.add(campera);
		prendasAroco.add(sueter);
		prendasAroco.add(bermuda);
		prendasAroco.add(pantalonLargo);
		prendasAroco.add(zapatillas);
		prendasAroco.add(zapatos);

		//agrego mas prendas a aroco para pruebas del molde

		Prenda remeraCuelloRedondoMangaCorta2 = new Prenda(t1, Tela.ALGODON, Color.BLANCO, Protocolo.Informal, null);
		Prenda remeraCuelloRedondoMangaCorta3 = new Prenda(t1, Tela.ALGODON, Color.VERDE, Protocolo.Informal, null);
		Prenda remeraCuelloRedondoMangaCorta4 = new Prenda(t1, Tela.ALGODON, Color.BORDO, Protocolo.Informal, null);
		Prenda bermuda2 = new Prenda(t9, Tela.JEAN, Color.ROJO, Protocolo.Informal, null);
		Prenda bermuda3 = new Prenda(t9, Tela.JEAN, Color.GRIS, Protocolo.Informal, null);
		Prenda bermuda4 = new Prenda(t9, Tela.JEAN, Color.AZUL, Protocolo.Informal, null);
		Prenda zapatillas3 = new Prenda(t14, Tela.NYLON, Color.AMARILLO, Protocolo.Informal, null);
		Prenda zapatillas4 = new Prenda(t14, Tela.NYLON, Color.VERDE, Protocolo.Informal, null);
		Prenda zapatillas5 = new Prenda(t14, Tela.NYLON, Color.CELESTE, Protocolo.Informal, null);

		prendasAroco.add(remeraCuelloRedondoMangaCorta2);
		prendasAroco.add(remeraCuelloRedondoMangaCorta3);
		prendasAroco.add(remeraCuelloRedondoMangaCorta4);
		prendasAroco.add(bermuda2);
		prendasAroco.add(bermuda3);
		prendasAroco.add(bermuda4);
		prendasAroco.add(zapatillas3);
		prendasAroco.add(zapatillas4);
		prendasAroco.add(zapatillas5);

		//----------------------------------------------------



		guardarropas1.add(guardarropaAroco);
		guardarropaAroco.setPrendas(prendasAroco);
		aroco.setGuardarropa(guardarropas1);

		List<Prenda> prendasJazul = new ArrayList<>();
		prendasJazul.add(remeraCuelloRedondoML);
		prendasJazul.add(remeraEscoteVML);
		prendasJazul.add(musculosa2);
		prendasJazul.add(sueter2);
		prendasJazul.add(pollera);
		prendasJazul.add(calza);
		prendasJazul.add(buzo);
		prendasJazul.add(zapatos2);
		prendasJazul.add(sandalias);

		guardarropas2.add(guardarropaJazul);
		guardarropaJazul.setPrendas(prendasJazul);
		jazul.setGuardarropa(guardarropas2);

		jazul.cambiarEstado();

		//Lista de Prendas--------------------------------------------

		listPrendas.add(remeraCuelloRedondoMangaCorta);
		listPrendas.add(campera);
		listPrendas.add(pantalonLargo);
		listPrendas.add(zapatos);

		listPrendas2.add(remeraCuelloRedondoML);
		listPrendas2.add(sueter);
		listPrendas2.add(pollera);
		listPrendas2.add(sandalias);

		listPrendas3.add(remeraEscoteVMangaCorta);
		listPrendas3.add(bermuda);
		listPrendas3.add(zapatillas);


		//Atuendos-----------------------------------------------------------
		Atuendo unAtuendo1 = new Atuendo(listPrendas2, Protocolo.Informal);

		Atuendo unAtuendo3 = new Atuendo(listPrendas, Protocolo.Informal);

		Atuendo unAtuendo2 = new Atuendo(listPrendas3,Protocolo.Informal);

		unAtuendo1.setProtocolo(Protocolo.Informal);
		unAtuendo1.setGuardarropa(guardarropaJazul);

		unAtuendo2.setProtocolo(Protocolo.Informal);
		unAtuendo2.setGuardarropa(guardarropaAroco);

		unAtuendo3.setProtocolo(Protocolo.Informal);
		unAtuendo3.setGuardarropa(guardarropaAroco);


		listaAtuendoJazul.add(unAtuendo1);
		listaAtuendoAroco.add(unAtuendo3);
		listaAtuendoAroco2.add(unAtuendo2);


		//Eventos-----------------------------------------------------
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Evento eventoJazul = new Evento("EventoEntregaTp", new Date(), Protocolo.Informal, Frecuencia.UNICO, jazul);
		Evento casamientoAroco = new Evento("Cena especial", dateFormat.parse("2019-12-04"), Protocolo.Informal, Frecuencia.UNICO,aroco);
		Evento cumpleañosFranquito = new Evento("Cumpleaños Franquito", dateFormat.parse("2019-12-08"), Protocolo.Informal, Frecuencia.UNICO,aroco);

		//Sugerencias---------------------------------------------------
		Sugerencia unaSugerencia2 = new Sugerencia(listaAtuendoJazul, eventoJazul, 15.0);
		Sugerencia unaSugerencia3 = new Sugerencia(listaAtuendoAroco,casamientoAroco,20.0);
		Sugerencia unaSugerencia4 = new Sugerencia(listaAtuendoAroco2, cumpleañosFranquito,15.0);

		// Molde -------------------------------------------------------------
		molde1.agregarTipoDePrenda(t1);
		molde1.agregarTipoDePrenda(t9);
		molde1.agregarTipoDePrenda(t14);

		molde2.agregarTipoDePrenda(t3);
		molde2.agregarTipoDePrenda(t9);
		molde2.agregarTipoDePrenda(t15);

		molde3.agregarTipoDePrenda(t2);
		molde3.agregarTipoDePrenda(t10);
		molde3.agregarTipoDePrenda(t16);

		molde4.agregarTipoDePrenda(t2);
		molde4.agregarTipoDePrenda(t9);
		molde4.agregarTipoDePrenda(t14);

		EntityManagerHelper.beginTransaction();
		//Persisto tipos de prendas------------------------
		EntityManagerHelper.getEntityManager().persist(t1);
		EntityManagerHelper.getEntityManager().persist(t2);
		EntityManagerHelper.getEntityManager().persist(t3);
		EntityManagerHelper.getEntityManager().persist(t4);
		EntityManagerHelper.getEntityManager().persist(t5);
		EntityManagerHelper.getEntityManager().persist(t6);
		EntityManagerHelper.getEntityManager().persist(t7);
		EntityManagerHelper.getEntityManager().persist(t8);
		EntityManagerHelper.getEntityManager().persist(t9);
		EntityManagerHelper.getEntityManager().persist(t10);
		EntityManagerHelper.getEntityManager().persist(t11);
		EntityManagerHelper.getEntityManager().persist(t12);
		EntityManagerHelper.getEntityManager().persist(t13);
		EntityManagerHelper.getEntityManager().persist(t14);
		EntityManagerHelper.getEntityManager().persist(t15);
		EntityManagerHelper.getEntityManager().persist(t16);

		//Persisto prendas-----------------------------------------------------------
		EntityManagerHelper.getEntityManager().persist(remeraCuelloRedondoMangaCorta);
		EntityManagerHelper.getEntityManager().persist(remeraEscoteVMangaCorta);
		EntityManagerHelper.getEntityManager().persist(musculosa);
		EntityManagerHelper.getEntityManager().persist(campera);
		EntityManagerHelper.getEntityManager().persist(sueter);
		EntityManagerHelper.getEntityManager().persist(bermuda);
		EntityManagerHelper.getEntityManager().persist(pantalonLargo);
		EntityManagerHelper.getEntityManager().persist(zapatillas);
		EntityManagerHelper.getEntityManager().persist(zapatos);
		EntityManagerHelper.getEntityManager().persist(remeraCuelloRedondoML);
		EntityManagerHelper.getEntityManager().persist(remeraEscoteVML);
		EntityManagerHelper.getEntityManager().persist(musculosa2);
		EntityManagerHelper.getEntityManager().persist(sueter2);
		EntityManagerHelper.getEntityManager().persist(pollera);
		EntityManagerHelper.getEntityManager().persist(calza);
		EntityManagerHelper.getEntityManager().persist(buzo);
		EntityManagerHelper.getEntityManager().persist(sandalias);
		EntityManagerHelper.getEntityManager().persist(zapatos2);

		//PRENDAS EXTRAS PARA AROCO

		EntityManagerHelper.getEntityManager().persist(remeraCuelloRedondoMangaCorta2);
		EntityManagerHelper.getEntityManager().persist(remeraCuelloRedondoMangaCorta3);
		EntityManagerHelper.getEntityManager().persist(remeraCuelloRedondoMangaCorta4);
		EntityManagerHelper.getEntityManager().persist(bermuda2);
		EntityManagerHelper.getEntityManager().persist(bermuda3);
		EntityManagerHelper.getEntityManager().persist(bermuda4);
		EntityManagerHelper.getEntityManager().persist(zapatillas3);
		EntityManagerHelper.getEntityManager().persist(zapatillas4);
		EntityManagerHelper.getEntityManager().persist(zapatillas5);


		//Persisto guardarropas-----------------------------------------
		EntityManagerHelper.getEntityManager().persist(guardarropaJazul);
		EntityManagerHelper.getEntityManager().persist(guardarropaAroco);

		//Persisto usuarios------------------------------------
		EntityManagerHelper.getEntityManager().persist(jazul);
		EntityManagerHelper.getEntityManager().persist(aroco);

		EntityManagerHelper.getEntityManager().persist(unAtuendo1);
		EntityManagerHelper.getEntityManager().persist(unAtuendo2);
		EntityManagerHelper.getEntityManager().persist(unAtuendo3);

		EntityManagerHelper.getEntityManager().persist(eventoJazul);

		aroco.getEventos().forEach(evento -> EntityManagerHelper.getEntityManager().persist(evento));

		EntityManagerHelper.getEntityManager().persist(unaSugerencia2);
		EntityManagerHelper.getEntityManager().persist(unaSugerencia3);
		EntityManagerHelper.getEntityManager().persist(unaSugerencia4);


		EntityManagerHelper.commit();

		repo.agregar(molde1);
		repo.agregar(molde2);
		repo.agregar(molde3);
		repo.agregar(molde4);
	}


}
