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

public class FrmEstadistica extends JFrame {
    private JTextField txtDato;
    private JList lstMuestra;
    //metodo constructor
    public FrmEstadistica() {
        setSize(500, 300);
        setTitle("Estadística");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblDato=new JLabel("Dato: ");
        lblDato.setBounds(10, 10, 100, 25);
        getContentPane().add(lblDato);

        txtDato=new JTextField();
        txtDato.setBounds(110, 10, 100, 25);
        getContentPane().add(txtDato);

        JButton btnAgregar=new JButton("Agregar");
        btnAgregar.setBounds(110, 40, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar=new JButton("Quitar");
        btnQuitar.setBounds(110, 70, 100, 25);
        getContentPane().add(btnQuitar);

        JLabel lblMuestra=new JLabel("Muestra: ");
        lblMuestra.setBounds(220, 10, 100, 25);
        lblMuestra.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblMuestra);

        lstMuestra=new JList<>();
        JScrollPane spMuestra=new JScrollPane(lstMuestra);
        spMuestra.setBounds(220, 40, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnEstadistica=new JButton("Estadistica");
        btnEstadistica.setBounds(10, 200, 100, 25);
        getContentPane().add(btnEstadistica);

        JComboBox cmbEstadistica=new JComboBox<>();
        String[] opciones=new String[] {"Sumatoria", "Promedio", "Desviación Estándar", "Máximo", "Mínimo", "Moda"};
        DefaultComboBoxModel dcm=new DefaultComboBoxModel<>(opciones);
        cmbEstadistica.setModel(dcm);
        cmbEstadistica.setBounds(110, 200, 100, 25);
        getContentPane().add(cmbEstadistica);

        JTextField txtEstadistica=new JTextField();
        txtEstadistica.setBounds(220, 200, 100, 25);
        txtEstadistica.setEnabled(false);
        getContentPane().add(txtEstadistica);

        // crear eventos
        btnAgregar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
            
        });
        btnQuitar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                quitarDato();
            }
        });

        btnEstadistica.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularEstadistica();
            }
        });
    }

    private int MAXIMO_DATOS=1000;
    private double[] muestra=new double[MAXIMO_DATOS];
    private int totalDatos=0;


    private void agregarDato(){
        if (totalDatos<MAXIMO_DATOS) {
            muestra[totalDatos]= Double.parseDouble(txtDato.getText());
            totalDatos++;
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pueden agregar más datos");
        }

    }

    private void mostrarDatos(){
        String[] strMuestra=new String[totalDatos];
        for(int i=0;i<totalDatos;i++) {
            strMuestra[i]=String.valueOf(muestra[i]);
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato(){
        JOptionPane.showMessageDialog(null, "Hizo clic en QUITAR");
    }
    private void calcularEstadistica(){
        JOptionPane.showMessageDialog(null, "Hizo clic en CALCULAR ESTADÍSTICA");
    }

}