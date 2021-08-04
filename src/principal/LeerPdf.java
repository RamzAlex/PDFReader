
package principal;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeerPdf extends JFrame implements ActionListener{
    
    ImageIcon pdf = new ImageIcon(getClass().getResource("/imagenes/pdf.png"));// CONVERTIMOS LA IMAGEN A OBJETO
    JLabel iconoPDF = new JLabel(pdf);// ETIQUETA DE PDF
    JLabel L_Ingresa = new JLabel("Número de registros validos");
    JLabel nota = new JLabel("Nota: Podras extraer y guardar datos de archivos PDF mas de una vez mientras no salgas del sistema.");
    JLabel xela = new JLabel("Xela_1");
    
    static String a;// VARIABLE QUE SE USA PARA OBTENER LA DIRECCION DEL ARCHIVO
    
    JButton agregar = new JButton("Agregar Archivo");
    JButton guardar = new JButton("Guardar Archivo");
    JButton salir = new JButton("Salir");
    
    static JTextField CampoTexto = new JTextField("42");
    
    static String CSV_Final[];
    static String paginas[];
    static String Todo="";
    
    public LeerPdf(){
        configuracion();
        addComponentes();
        oyentes();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/Java.png")).getImage());// ICONO DE LA VENTANA
    }
    
    public void configuracion(){
        this.setTitle("Xela_1");
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.lightGray);
        //this.
    }
    
    private void addComponentes() {
        this.add(xela);
        xela.setBounds(150, 100, 200, 50);
        xela.setFont(new Font(null, 1, 40));
        xela.setForeground(Color.blue);
        
        this.add(nota);
        nota.setBounds(25, 450, 750, 50);
        nota.setFont(new Font(null, 1, 15));
        //nota.setForeground(Color.);

        
        this.add(agregar);//3
        agregar.setBounds(450, 225, 135, 30);
        agregar.setToolTipText("Primero ingresa el número de registros\n"
                                + " y despues pulsa aquí");
        agregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        agregar.setForeground(Color.blue);
        
        this.add(guardar);//4
        guardar.setBounds(450, 290, 130, 30);
        guardar.setToolTipText("Guardar archivo en formato CSV");
        guardar.setEnabled(false);
        guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guardar.setForeground(Color.blue);
        
        this.add(salir);//5
        salir.setBounds(450, 355, 130, 30);
        salir.setToolTipText("Salir");
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //salir.setBackground(Color.red);
        salir.setForeground(Color.red);
        
        this.add(iconoPDF);
        iconoPDF.setBounds(50, 200, 350, 150);
        
        this.add(CampoTexto);//2
        CampoTexto.setBounds(450, 165, 120, 40);
        CampoTexto.setToolTipText("Ingresa aquí");
        CampoTexto.setFont(new Font(null, 4, 30));
        CampoTexto.setForeground(Color.blue);
        
        this.add(L_Ingresa);//1
        L_Ingresa.setFont(new Font(null, 1, 15));
        L_Ingresa.setBounds(450, 100, 250, 50);
    }
    
    private void oyentes() {
        agregar.addActionListener(this);
        guardar.addActionListener(this);
        salir.addActionListener(this);
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
            CampoTexto.setEnabled(false);
            guardar.setEnabled(true);
            agregar.setEnabled(false);
            
        }
                            Splash h = new Splash();

        if ( e.getSource() == guardar ) {
            JFileChooser n = new JFileChooser(); // n SERA EL EXPLORADOR DE ARCHIVOS
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo archivos csv", "csv");
            n.setFileFilter(filter);// APLICAR EL FILTRO
            n.showSaveDialog(null);// PARA MOSTRAR EL CUADRO DE DIALOGO
            File archivo = new File(n.getSelectedFile()+".csv");
            try{
                BufferedWriter salida = new BufferedWriter(new FileWriter(archivo));
                salida.write("ID,Clasificación,Tipo,Descripción,Valor,Resguardante,Origen,Status");// ENCABEZADOS DE LAS COLUMNAS
                for (int i = 0; i < CSV_Final.length; i++) {
                    salida.newLine();
                    salida.append(CSV_Final[i]);
                }
                salida.close();
                JOptionPane.showMessageDialog(null, "Se guardo el archivo en "+n.getSelectedFile());
            }catch(Exception i){
            }
            guardar.setEnabled(false);
            agregar.setEnabled(true);
            CampoTexto.setEnabled(true);
            CampoTexto.setText("");
        }
        if ( e.getSource() == salir ) {
            JOptionPane.showMessageDialog(null, "confirmar salida del sistema");
            this.dispose();
        }
    }


    public static void funcion1() {
        try {
            int contador = 0;// ENTERO QUE NOS VA DICIENDO EN QUE LINEA DEL PDF NOS ENCONTRAMOS

            PdfReader reader = new PdfReader(a);// CREAMOS UN LECTOR PARA EL ARCHIVO SELECCIONADO
            //System.out.println("Este documento tiene "+reader.getNumberOfPages()+" paginas.");
            paginas = new String[reader.getNumberOfPages()-1];
            
            for (int i = 0; i < reader.getNumberOfPages()-1; i++) {
                paginas[i] = PdfTextExtractor.getTextFromPage(reader, i+1);// EXTRACCION DE LA INFORMACION POR PAGINAS
                Todo += paginas[i];// JUNTAMOS TODO EN UN STRING
            }
                        
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
                
            .replace("Página 1 de 6.","")
            .replace("Página 2 de 6.","")
            .replace("Página 3 de 6.","")
            .replace("Página 4 de 6.","")
            .replace("Página 5 de 6.","")// SE ELIMINA TODO LO QUE NO NECESITAMOS
            .replace("Total de registros: 48","");// SE ELIMINA TODO LO QUE NO NECESITAMOS
            
            String Linea[] = TextoCompleto.split("\n");// DIVIDIMOS EL STRING ORIGINAL EN LOS SALTOS DE LINEA 
            
            char a;// VARIABLE PARA SABER SI EL REGISTRO EN EL PDF COMIENZA EN ID O EN CLASIFICACION
        
            String num = CampoTexto.getText();// CAPTURAMOS EL NUMERO DE REGISTROS VALIDOS
            int numReg = Integer.parseInt(num);// LO PASAMOS A UN ENTERO
            
            CSV_Final = new String[numReg];
            
            for (int i = 0; i < numReg; i++) {// NUMERO TOTAL DE REGISTROS VALIDOS PARA ESTE CASO 42
                if ( i > 0 ) {
                contador += 4;
                }
                
                
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
                
                
            a = Linea[contador].charAt(0);// PARA SABER SI EL PRIMER CARACTER COMIENZA EN NUMERO

            if (Character.isDigit(a)) {// SI COMIENZA CON UN NUMERO ENTRA.
                    
                    
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
                  
                    CSV_Final[i] = CSV;
                    
                }// CIERRE DEL if

                
                
//--------------SEGUNDO FOR PARA LA SEGUNDA FUNCION-----------------------------
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
                
                
                if (Character.isLetter(a)) {// SI COMIENZA EN LETRA INGRESA
                
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
                    
                    CSV_Final[i] = CSV;
                    
                }// CIERRE DEL SEGUNDO IF
            }// CIERRE DEL SEGUNDO FOR
            
            reader.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}