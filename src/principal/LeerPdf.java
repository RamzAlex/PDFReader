
package principal;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeerPdf extends JFrame implements ActionListener{
    
    ImageIcon pdf = new ImageIcon(getClass().getResource("/imagenes/pdf.png"));// CONVERTIMOS LA IMAGEN A OBJETO
    JLabel iconoPDF = new JLabel(pdf);// ETIQUETA DE PDF
    static String a;// VARIABLE QUE SE USA PARA OBTENER LA DIRECCION DEL ARCHIVO
    JButton agregar = new JButton("Agregar Archivo...");
    static JTextField CampoTexto = new JTextField("");
    JLabel L_Ingresa = new JLabel("Número de registros validos...");
    
    public LeerPdf(){
        configuracion();
        addComponentes();
        oyentes();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/Java.png")).getImage());// ICONO DE LA VENTANA
    }
    
    public void configuracion(){
        this.setTitle("Extracción de información de PDF");
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
    }
    
    private void addComponentes() {
        this.add(agregar);
        agregar.setBounds(450, 300, 150, 30);
        agregar.setToolTipText("Primero ingresa el número de registros\n"
                                + " y despues pulsa aquí");
        this.add(iconoPDF);
        iconoPDF.setBounds(150, 100, 200, 300);
        this.add(CampoTexto);
        CampoTexto.setBounds(450, 215, 60, 30);
        CampoTexto.setToolTipText("Ingresa aquí");
        this.add(L_Ingresa);
        L_Ingresa.setFont(new Font(null, 1, 15));
        L_Ingresa.setBounds(450, 150, 250, 50);
    }
    
    private void oyentes() {
        agregar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = 0;// ENTERO PARA SABER SI PULSO ABRIR EN EL EXPLORADOR DE ARCHIVOS
        if(e.getSource()==agregar){

            JFileChooser n = new JFileChooser(); // n SERA EL EXPLORADOR DE ARCHIVOS

            // CREA EL FILTRO PARA SOLO MOSTRAR ARCHIVOS PDF 
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo archivos pdf", "pdf");
            n.setFileFilter(filter);// APLICAR EL FILTRO
            n.showOpenDialog(this);// PARA MOSTRAR EL CUADRO DE DIALOGO
            if ( x == JFileChooser.APPROVE_OPTION ) {
                String selectPath = n.getSelectedFile().getPath();
                a=selectPath;// LE PASAMOS A "a" LA RUTA DEL ARCHIVO SELECCIONADO
                
                funcion1();// LLAMADA A LA FUNCION 1
            }
        }
    }


    public static void funcion1() {
        try {
            int contador = 0;// ENTERO QUE NOS VA DICIENDO EN QUE LINEA DEL PDF NOS ENCONTRAMOS

            PdfReader reader = new PdfReader(a);// CREAMOS UN LECTOR PARA EL ARCHIVO SELECCIONADO
            //System.out.println("Este documento tiene "+reader.getNumberOfPages()+" paginas.");
            System.out.println("ID,Clasificación,Tipo,Descripción,Valor,Resguardante,Origen,Status");// ENCABEZADOS DE LAS COLUMNAS
            String Pagina1 = PdfTextExtractor.getTextFromPage(reader, 1);// EXTRACCION DE LA INFORMACION POR PAGINAS
            String Pagina2 = PdfTextExtractor.getTextFromPage(reader, 2);
            String Pagina3 = PdfTextExtractor.getTextFromPage(reader, 3);
            String Pagina4 = PdfTextExtractor.getTextFromPage(reader, 4);
            String Pagina5 = PdfTextExtractor.getTextFromPage(reader, 5);
                        
            //JUNTAMOS TODO EN UN STRING
            String Todo = Pagina1+"\n"+Pagina2+"\n"+Pagina3+"\n"+Pagina4+"\n"+Pagina5;

            String TextoCompleto = Todo.replace("NO BREAK", "NO_BREAK").replace("e:\n", "e: ")
            //quitando todo lo que no necesitamos
           .replace("UNIVERSIDAD DE GUADALAJARA\n" +
            "SECRETARÍA GENERAL\n" +
            "COORDINACIÓN GENERAL DE PATRIMONIO\n" +
            "UNIDAD DE CONTROL PATRIMONIAL\n" +
            "INVENTARIO FÍSICO ANUAL\n" +
            "FECHA DE IMPRESIÓN:  23 de Octubre del 2019 12:23:31\n" +
            "DEPENDENCIA:   3.7.5.2.5 - LABORATORIO  DE DESARROLLO DE SOFTWARE CUVALLES\n" +
            "TITULAR:  2730642 - JOSE ROBERTO LOMELI HUERTA\n" +
            "LISTADO DE BIENES NO LOCALIZADOS 2018:\n" +
            "Localizado\n" +
            "Valor Resguardante Origen Estatus\n" +
            "ID Clasificación Tipo Descripción\n" +
            "SI / NO\n" +
            "Total de registros:  0 \n" +
            "LISTADO DE BIENES 2019:\n" +
            "Localizado\n" +
            "Valor Resguardante Origen Estatus\n" +
            "ID Clasificación Tipo Descripción\n" +
            "SI / NO\n", "")
                
            .replace("UNIVERSIDAD DE GUADALAJARA\n" +
            "SECRETARÍA GENERAL\n" +
            "COORDINACIÓN GENERAL DE PATRIMONIO\n" +
            "UNIDAD DE CONTROL PATRIMONIAL\n" +
            "INVENTARIO FÍSICO ANUAL\n" +
            "FECHA DE IMPRESIÓN:  23 de Octubre del 2019 12:23:31\n" +
            "DEPENDENCIA:   3.7.5.2.5 - LABORATORIO  DE DESARROLLO DE SOFTWARE CUVALLES\n" +
            "TITULAR:  2730642 - JOSE ROBERTO LOMELI HUERTA\n" +
            "LISTADO DE BIENES 2019:\n" +
            "Localizado\n" +
            "Valor Resguardante Origen Estatus\n" +
            "ID Clasificación Tipo Descripción\n" +
            "SI / NO\n", "")
                
           .replace("UNIVERSIDAD DE GUADALAJARA\n" +
            "SECRETARÍA GENERAL\n" +
            "COORDINACIÓN GENERAL DE PATRIMONIO\n" +
            "UNIDAD DE CONTROL PATRIMONIAL\n" +
            "INVENTARIO FÍSICO ANUAL\n" +
            "FECHA DE IMPRESIÓN:  23 de Octubre del 2019 12:23:31\n" +
            "DEPENDENCIA:   3.7.5.2.5 - LABORATORIO  DE DESARROLLO DE SOFTWARE CUVALLES\n" +
            "TITULAR:  2730642 - JOSE ROBERTO LOMELI HUERTA\n" +
            "LISTADO DE BIENES 2019:\n" +
            "Localizado\n" +
            "ID Clasificación Tipo Descripción Valor Resguardante Origen Estatus\n" +
            "SI / NO\n", "")
                
            .replace("Página 1 de 6.\n","")
            .replace("Página 2 de 6.\n","")
            .replace("Página 3 de 6.\n","")
            .replace("Página 4 de 6.\n","")
            .replace("Página 5 de 6.\n","");// SE ELIMINA TODO LO QUE NO NECESITAMOS
        
            String Linea[] = TextoCompleto.split("\n");// DIVIDIMOS EL STRING ORIGINAL EN LOS SALTOS DE LINEA 

            //System.out.println(TextoCompleto);
            
            char a;// VARIABLE PARA SABER SI EL REGISTRO EN EL PDF COMIENZA EN ID O EN CLASIFICACION
        
            String num = CampoTexto.getText();// CAPTURAMOS EL NUMERO DE REGISTROS VALIDOS
            int numReg = Integer.parseInt(num);// LO PASAMOS A UN ENTERO
            
            for (int i = 0; i < numReg; i++) {// NUMERO TOTAL DE REGISTROS VALIDOS PARA ESTE CASO 42
                if ( i > 0 ) {
                contador += 4;
                }
                
                a = Linea[contador].charAt(0);// PARA SABER SI EL PRIMER CARACTER COMIENZA EN NUMERO
            
                if (Character.isDigit(a)) {// SI COMIENZA CON UN NUMERO ENTRA.
                    
                    
//---------------- INICIO QUITANDO LOS REGISTROS NO VALIDOS --------------------
                    
                for (int j = 0; j < 3; j++) {
                    if (Linea[contador].contains("MESA")) {
                        contador += 7;
                    }
                }
                
                for (int j = 0; j < 3; j++) {
                    if (Linea[contador].contains("DECODIFICADOR")) {
                        contador += 3;
                    }
                }

//----------------- FIN QUITANDO LOS REGISTROS NO VALIDOS ----------------------
     
                
                   // AGREGAMOS "=" A LAS LINEAS PARA DESPUES PODER HACER CORTES QUE DICTA EL CONTADOR
                   String Cambio1 = Linea[contador].replace("Desc", "=Desc").replace("2730", "=2730").replace("- JOSE ROBERTO", "= JOSE ROBERTO=");

                    // CORTES A LA LINEA
                    String ArregloLinea19[] = Cambio1.split("=");

                    // CORTES AL SUBSTRING EN LA POSICION CERO DE LAS LINEAS
                    String Cortes[] = ArregloLinea19[0].split(" ");

                    // MODIFICACION DE LA LINEA 
                    String DeleteApellidos = Linea[contador+2].replace("LOMELI HUERTA", "");

                    // SEPARANDO ID, VALOR Y ESTADO EN LA LINEA QUE DICTA EL CONTADOR
                    String IdValorEstado[] = Linea[contador+1].split(" ");//20

                    // ESTABLECEMOS LOS DATOS DEL PROFESOR
                    String Profe = ("2730642 - JOSE ROBERTO LOMELI HUERTA");

                    String CSV = Cortes[0] + "," + Cortes[1] + "," + Cortes[2] + "," 
                    + ArregloLinea19[1] + DeleteApellidos + Linea[contador + 3] + "," 
                    + IdValorEstado[1] + "," + Profe + "," + ArregloLinea19[4] + ","
                    + IdValorEstado[2];
                  
                    System.out.println(CSV);
                
                }// CIERRE DEL if
          
                
                
//--------------SEGUNDO FOR PARA LA SEGUNDA FUNCION-----------------------------
                
                if (Character.isLetter(a)) {// SI COMIENZA EN LETRA INGRESA
                

//---------------- INICIO QUITANDO LOS REGISTROS NO VALIDOS --------------------
                   
                for (int j = 0; j < 3; j++) {
                    if (Linea[contador].contains("MESA")) {
                        contador += 7;
                    }
                }
                
                for (int j = 0; j < 3; j++) {
                    if (Linea[contador].contains("DECODIFICADOR")) {
                        contador += 3;
                    }
                }
                
//----------------- FIN QUITANDO LOS REGISTROS NO VALIDOS ----------------------

                
                    // AGREGAMOS "=" A LA LINEAS PARA DESPUES PODER HACER CORTES DONDE DICTA EL CONTADOR
                    String Cambio1 = Linea[contador].replace("Desc", "=Desc").replace("2730", "=2730").replace("- JOSE ROBERTO", "= JOSE ROBERTO=");

                    // CORTES A LA LINEA
                    String ArregloLinea19[] = Cambio1.split("=");

                    // CORTES AL SUBSTRING EN LA POSICION CERO DE LA LINEA QUE DICTA EL CONTADOR
                    String Cortes[] = ArregloLinea19[0].split(" ");

                    // MODIFICACION DE LA LINEA QUE DICTA EL CONTADOR
                    String DeleteApellidos = Linea[contador+2].replace("LOMELI HUERTA", "");

                    // SEPARANDO ID, VALOR Y ESTADO EN LA LINEA QUE DICTA EL CONTADOR
                    String IdValorEstado[] = Linea[contador+1].split(" ");//20

                    // ESTABLECEMOS LOS DATOS DEL PROFESOR
                    String Profe = ("2730652 - JOSE ROBERTO LOMELI HUERTA");
                    
                    String CSV = IdValorEstado[0] + "," + Cortes[0] + "," + Cortes[1] + ","
                    + ArregloLinea19[1] + DeleteApellidos + Linea[contador+3] + ","
                    + IdValorEstado[1] + "," + Profe + "," + ArregloLinea19[4] + ","
                    + IdValorEstado[2];
                    
                    System.out.println(CSV);
                    
                }// CIERRE DEL SEGUNDO IF
            }// CIERRE DEL SEGUNDO FOR
            reader.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }   
}