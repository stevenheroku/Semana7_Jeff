/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clsListas;

import BaseDatos.clsConexion;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author Jefferson Rueda
 */
public class CargarDatos {

    public String separar;
    public List<String> list;
    List<String> arraylist;
    //@FXML
    //private TextArea print;
    @FXML // fx:id="tx_datos"
    private TextField tx_datos; // Value injected by FXMLLoader

    public List<String> consultarNivel() {

        try {
            clsConexion conectar = new clsConexion();

            String query = "SELECT * FROM nombres";
            ResultSet sconectar = conectar.EjecutarSeleccion(query);
            ResultSetMetaData md = sconectar.getMetaData();
            int columns = md.getColumnCount();
            while (sconectar.next()) {

                ArrayList<Propiedades_Name> datos = new ArrayList<Propiedades_Name>();
                // aca tienes que crear un nuevo objeto
                Propiedades_Name prs = new Propiedades_Name();
                prs.setNombre_p(sconectar.getString("Nombre"));

                datos.add(prs);
                for (int x = 0; x < datos.size(); x++) {

                    list = new ArrayList<>(Arrays.asList(datos.get(x).getNombre_p()));

                    separar += list + ",";
                    separar = separar.replace("[", "").replace("]", "").replace("null", " ");
                }

            }//end while
            arraylist = new ArrayList(Arrays.asList(separar.split(",")));
            //Collections.shuffle(arraylist);
            
            Collections.sort(arraylist);
            // 

            Sortear10Personas();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return list;

    }

    //utilizo un metodo que hace el funcionamiento del sorteo donde esocogo solo 10 nombres de la lista
    public void Sortear10Personas() {

        Scanner r = new Scanner(System.in);
        int opcion;
        int eliminar;
        do {
            System.out.println("\n");
            // System.out.println(arraylist);
            System.out.println("Elija la Opción deseada");
            System.out.println("1. Ver todos los participantes de la Lista");
            System.out.println("2. Elejir 10 Nombres de la lista");
            System.out.println("3. Utilizar la Funcion Shuffle para desordenar nombres");
            System.out.println("4.Salir");

            opcion = r.nextInt();

            switch (opcion) {
                case 1:
                    //Collections.shuffle(arraylist);
                    //imprime todos los datos de la lista que tengo almacenados en la base de datos
                       Collections.sort(arraylist);
                    int n = 0;
                    for (String m : arraylist) {
                        n++;
                        System.out.print("\n  No. " + n + ". " + m);
                    }
                    System.out.println("\n");
                    
                    break;

                case 2:
                        int c = 0;

                    Collections.shuffle(arraylist);
                    System.out.println("10 NOMBRES PARA EL SORTEO SON LOS SIGUIENTES:");
                    for (int i = 0; i < 10; i++) {
                        String print = arraylist.get(i);
                        c++;
                        System.out.print("\n  No. " + c + ". " + print);
                           //elimino de mi lista las 10 personas que selecciono al azar para el sorteo,el cual despues solo tendre 40 de las 50 personas
                        arraylist.remove(i);
                        // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                    
                    break;

                case 3:

                    Collections.shuffle(arraylist);
                    int posiciones = 0;
                    for (String nombre : arraylist) {
                        posiciones++;
                        System.out.print("\n  Posicion: " + posiciones + " Nombre: " + nombre);
                    }
                    System.out.println("\n");

                    break;
                    
                case 4:
                    System.exit(0);
                    break;
                    
                    
                default:
                    System.out.println("Opcion incorrecta");
                    break; 
            }

        } while (opcion != 4);
    }

}

