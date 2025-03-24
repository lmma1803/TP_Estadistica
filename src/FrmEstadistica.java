import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FrmEstadistica extends JFrame { // JFrame es otro método que me sirve para ventanas y formularios
    private JTextField txtDato; // debo declarar esto acá para que sea una variable global y no local
    private JList lstMuestra; // declaro globalmente porque necesito este lstMuestra en los métodos de
                              // agregar, mostrar de abajo
    private JComboBox cmbEstadistica;
    private JTextField txtEstadistica;

    // metodo constructor
    public FrmEstadistica() { // de aqui hacia abajo es donde voy a poner todas las caracterísiticas de mi
                              // ventana, le estoy dando la responsabilidad de la interfaz gráfica, no puedo
                              // poner lógica acá porque me estaría desviando de esa responsabilidad que le di
        setSize(500, 300); // tamaño del formulario en pixeles
        setTitle("Estadística"); // titulo de la ventana
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // para que la app se cierre verdaderamente cuando le
                                                                 // de en la cruz
        setLayout(null); // las ventanas por defecto tienen una distribución automática, esto me hace
                         // quitarla

        JLabel lblDato = new JLabel("Dato: "); // es una etiqueta. con lo que está en verde declaro, con lo amarillo
                                               // instancio
        lblDato.setBounds(10, 10, 100, 25); // ubico el label en la pantalla con la setbounds
        getContentPane().add(lblDato); // con esta instrucción verdaderamente hago aparecer el labell

        txtDato = new JTextField(); // es la caja de texto
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);

        JButton btnAgregar = new JButton("Agregar"); // boton
        btnAgregar.setBounds(110, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar"); // boton
        btnQuitar.setBounds(110, 70, 100, 25);
        getContentPane().add(btnQuitar);

        JLabel lblMuestra = new JLabel("Muestra: "); // etiqueta
        lblMuestra.setBounds(220, 10, 100, 25);
        lblMuestra.setHorizontalAlignment(SwingConstants.CENTER); // me ayuda a centrar el texto no en toda la pantalla
                                                                  // sino en las medidas que le di
        getContentPane().add(lblMuestra);

        lstMuestra = new JList(); // JList es lista, este JList siempre se tiene que combinar con un scrollpane
                                  // que es la barra de desplazamiento
        JScrollPane spMuestra = new JScrollPane(lstMuestra); // JScrollPane siempre se debe instaciar pegado al objeto
                                                             // que va afectar
        spMuestra.setBounds(220, 40, 100, 150); // el bounds se hace ya del scroll
        getContentPane().add(spMuestra); // la agregada se hace ya del scroll

        JButton btnEstadistica = new JButton("Estadistica"); // boton
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        cmbEstadistica = new JComboBox(); // barra desplegable
        String[] opciones = new String[] { "Sumatoria", "Promedio", "Desviación Estándar", "Máximo", "Mínimo", "Moda" }; // declaro
                                                                                                                         // un
                                                                                                                         // vector
                                                                                                                         // de
                                                                                                                         // string
                                                                                                                         // llamado
                                                                                                                         // opciones,
                                                                                                                         // lo
                                                                                                                         // instancio
                                                                                                                         // y
                                                                                                                         // le
                                                                                                                         // pongo
                                                                                                                         // las
                                                                                                                         // opciones
        DefaultComboBoxModel dcm = new DefaultComboBoxModel<>(opciones); // este es el modelo de datos, lo llamo dcm, su
                                                                         // papel es crear la fuente de datos del
                                                                         // Combobox
        cmbEstadistica.setModel(dcm); // se le asigna el modelo de datos al combo box
        cmbEstadistica.setBounds(110, 200, 100, 25);
        getContentPane().add(cmbEstadistica);

        txtEstadistica = new JTextField(); // caja de texto
        txtEstadistica.setBounds(220, 200, 100, 25);
        txtEstadistica.setEnabled(false); // esto no me deja poner nada en la caja de texto como usuario, sino que
                                          // simplemente sea un valor que el sistema me entrega
        getContentPane().add(txtEstadistica);

        // crear eventos
        btnAgregar.addActionListener(new ActionListener() { // el addactionlistener es como un escuchador de acciones,
                                                            // creo la instancia de un objeto escuchador de eventos y
                                                            // este es ActionListener

            @Override // el escuchador de eventos tiene que tener un subprograma que es este y que se
                      // ejecute cuando se de cuenta que sucedió el evento
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }

        });
        btnQuitar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDato();
            }
        });

        btnEstadistica.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadistica();
            }
        });
    }

    private int MAXIMO_DATOS = 1000;
    private double[] muestra = new double[MAXIMO_DATOS]; // declaro una variable tipo vector. es una variable global
                                                         // porque no está metida dentro de ninguna subrutina
    private int totalDatos = 0;

    private void agregarDato() {
        if (totalDatos < MAXIMO_DATOS) {
            muestra[totalDatos] = Double.parseDouble(txtDato.getText()); // tengo que tener en cuenta que txtDato es un
                                                                         // objeto, necesito es el valor que tiene ese
                                                                         // objeto, para eso uso, txtDato.getText() pero
                                                                         // getText() me devuelve un string, asi que lo
                                                                         // convierto a número con parse
            totalDatos++;
            mostrarDatos();
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más datos");
        }

    }

    private void mostrarDatos() {
        String[] strMuestra = new String[totalDatos];
        for (int i = 0; i < totalDatos; i++) { // recorre todod los datos del vector
            strMuestra[i] = String.valueOf(muestra[i]); // convierto todos los daatos del vector muestra que son string
                                                        // en un vector numerico
        }
        lstMuestra.setListData(strMuestra); // con esto le asigno una fuente de datos
    }

    private void quitarDato() {
        if (lstMuestra.getSelectedIndex() >= 0) { // lo que hace getselectedindex es que verifica que el usuario haya
                                                  // seleccionado una opción del JList
            for (int i = lstMuestra.getSelectedIndex(); i < totalDatos - 1; i++) {
                muestra[i] = muestra[i + 1];
            }
            totalDatos--;
            mostrarDatos();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el dato a quitar");
        }

    }

    private double Sumatoria() {
        double suma = 0;
        for (int i = 0; i < totalDatos; i++) {
            suma += muestra[i];
        }
        return suma;
    }

    private double desviacion() {
        if (totalDatos > 1) {
            double suma = 0;
            double promedioActual = promedio();
            for (int i = 0; i < totalDatos; i++) {
                suma += Math.abs(promedioActual - muestra[i]);
            }
            return suma / (totalDatos - 1);
        } else {
            return 0;
        }

    }

    private double promedio() {
        return totalDatos > 0 ? Sumatoria() / totalDatos : 0; // es un if de una sola linea
    }

    private double maximo() {
        if (totalDatos > 0) {
            double actualMaximo = muestra[0];
            for (int i = 1; i < totalDatos; i++) {
                if (muestra[i] > actualMaximo) {
                    actualMaximo = muestra[i];
                }
            }
            return actualMaximo;
        } else {
            return 0;
        }
    }

    private void calcularEstadistica() {
        switch (cmbEstadistica.getSelectedIndex()) {
            case 0:
                txtEstadistica.setText(String.valueOf(Sumatoria())); // le asigno al cuadro txtEstadistica el valor de
                                                                     // la sumatoria
                break;

            case 1:
                txtEstadistica.setText(String.valueOf(promedio()));
                break;

            case 2:
                txtEstadistica.setText(String.valueOf(desviacion()));
                break;

            case 3:
                txtEstadistica.setText(String.valueOf(maximo()));
                break;
        }
    }

}