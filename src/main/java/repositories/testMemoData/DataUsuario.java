package repositories.testMemoData;

import db.EntidadPersistente;
import domain.Usuario;
import repositories.daos.DAOMemoria;
import repositories.daos.DAOMySQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataUsuario {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static List<EntidadPersistente> getList(){
        if(usuarios.size() == 0) {
            //RepositorioRol repoRol = RepositorioRol.getInstance(new DAOMemoria(DataRol.getList())); --> ESTO NO SE QUE SERIA...

            //aca se crean usuarios de prueba (seguro vamos a mandarlo directamente a la BD)
            //Usuario lucas = new Usuario("Fatima", 10);
            //lucas.setUsername("Lucas");
            //lucas.setMail("Saclier");
            //lucas.setId(1);
            //lucas.setTelefono(1156585936);
            //lucas.setLegajo(112233);
            //lucas.setRol(repoRol.buscar(1));

            /*suario eze = new Usuario();
            eze.setNombre("Eze");
            eze.setApellido("Escobar");
            eze.setId(2);
            eze.setTelefono(1156339837);
            eze.setLegajo(112244);
            eze.setRol(repoRol.buscar(2));

            Usuario gaston = new Usuario();
            gaston.setNombre("Gaston");
            gaston.setApellido("Prieto");
            gaston.setId(3);
            gaston.setTelefono(1156449831);
            gaston.setLegajo(112255);
            gaston.setRol(repoRol.buscar(2));

            Usuario ezeS = new Usuario();
            ezeS.setNombre("Eze");
            ezeS.setApellido("Sosa");
            ezeS.setId(4);
            ezeS.setTelefono(1156559832);
            ezeS.setLegajo(112266);
            ezeS.setRol(repoRol.buscar(2));

            addAll(lucas, eze, gaston, ezeS);*/
        }
        return (List<EntidadPersistente>)(List<?>) usuarios;
    }

    private static void addAll(Usuario ... usuarios){
        Collections.addAll(DataUsuario.usuarios, usuarios);
    }
}
