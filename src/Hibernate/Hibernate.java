/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import controller.Controlador;
import entitats.AcabatCotxe;
import entitats.Marca;
import entitats.Model;
import model.ClasseDAO;
import view.Vista;

/**
 *
 * @author roger
 */
public class Hibernate {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClasseDAO<Marca> modelMarca = new ClasseDAO<>(Marca.class);
        ClasseDAO<Model> modelModel = new ClasseDAO<>(Model.class);
        ClasseDAO<AcabatCotxe> modelAcabatCotxe = new ClasseDAO<>(AcabatCotxe.class);
        Vista vista = new Vista();
    
        new Controlador(modelMarca, modelModel, modelAcabatCotxe, vista);
        
        //Comprovació
        // TODO code application logic here
//                ClasseDAO dao = new ClasseDAO(Marca.class);
//                Marca marcaRecuperada = null;
//                Long idAEliminar;
//                
//                Model esFabricat = null;
                
                //Creem tres instàncies de Marca
//                Marca marca1 = new Marca("Nom 1", "Madrid", null, esFabricat);
//                Marca marca2 = new Marca("Nom 2", "Barcelona", marca1, esFabricat);
//                Marca marca3 = new Marca("Nom 3", "Valencia", marca2, esFabricat);
//                Marca marca4 = new Marca("Nom 4", "Tortosa", marca3, esFabricat);
//        
//                //Guardem les tres instàncies, i copiem l'id del contacte1 per usar-lo posteriorment 
//                idAEliminar = dao.guarda(marca1);
//                dao.guarda(marca2);
//                dao.guarda(marca3);
//                dao.guarda(marca4);
        
                //Modifiquem la marca 2 i l'actualitzem 
//                marca2.setNom("Marca nova 2");
//                marca2.setSeuCentral("Nova Seu Tortosa");
//                dao.actualitza(marca2);
//        
//                //Recuperar
//                marcaRecuperada = (Marca) dao.obte(Integer.valueOf(String.valueOf(idAEliminar)));
//                System.out.println("Recuperada"+ marcaRecuperada.getNom());
//                
//                //Eliminem al contacteRecuperat (que és el contacte3) 
//                dao.elimina(marcaRecuperada);
//        
//                //Obtenim la llista de contactes que queden a la base de dades i la mostrem 
//                List<Marca> llistaMarques = dao.obtenLlista();
//                System.out.println("Hi ha " + llistaMarques.size() + "marques a la base de dades.");
//        
//                for (Marca m : llistaMarques) {
//                    System.out.println("-> " + m.getNom());
//                }

//    System.exit(0);
    }
}
