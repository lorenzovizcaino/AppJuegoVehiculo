import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class LaminaCarrera extends JPanel {
    private JPanel panelTiempos, panelbotones, panelElegirVehiculos, panelApuestas;
    private PonerImagenPanel panelCarrera;
    private JSeparator separador;
    private JButton btnIniciar, btnParar,btnReanudar, btnAcelerarFrenar;
    private JButton btnElegir1, btnElegir2, btnElegir3;
    private JButton btnMostrarTiempos;
    private JButton btnReiniciar;
    private JButton btnApostar;
    private Vehiculo car1, car2, car3;
    private JLabel labelTiempo1, labelTiempo2, labelTiempo3;
    private JTextField tfTiempo1,tfTiempo2,tfTiempo3;
    private JTextField tfDineroDisponible, tfMiApuesta;

    private JLabel labelWinner1, labelWinner2, labelWinner3, labelGameOver;
    private JLabel labelDineroDisponble, labelMiApuesta;
    private JLabel labelVictorias, labelDerrotas;
    private JComboBox comboapostar;

    private GBCConstrains gbc=new GBCConstrains();
    private int x1,y1,x2,y2,x3,y3,anchoPanel;
    private Image image;
    private ImageIcon icon=new ImageIcon("image/winner.png");
    private File imagenCarretera=new File("image/carretera4.png");
    private ImageIcon imagenGameOver=new ImageIcon("image/gameOver.PNG");
    private String ruta;
    private int ganador=0, victorias=0, derrotas=0;
    private int dineroDisponible=500;
    private int apuestaRegistrada=0;
    private Font fuente=new Font("Verdana",Font.PLAIN,14);

    public LaminaCarrera(){
      //  EstablecerFuenteComponentes(); //Con este metodo se le puede cambiar la fuente a todos los componentes a la vez.
        x1=100;
        y1=340;
        x2=100;
        y2=570;
        x3=100;
        y3=790;
        Icon iconWinnerredimensionado=redimensionarIcono(icon,120,100);
        Icon iconGameOverRedimensionado=redimensionarIcono(imagenGameOver,700,600);
        try {
            image= ImageIO.read(imagenCarretera).getScaledInstance(1600,1050,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(new GridBagLayout());
        panelElegirVehiculos =new JPanel(new GridLayout(3,1,10,10));
        btnElegir1 =new JButton("Elegir Vehiculo 1");
        btnElegir2 =new JButton("Elegir Vehiculo 2");
        btnElegir3 =new JButton("Elegir Vehiculo 3");
        panelElegirVehiculos.add(btnElegir1);
        panelElegirVehiculos.add(btnElegir2);
        panelElegirVehiculos.add(btnElegir3);

        panelApuestas=new JPanel(new GridLayout(8,1));
        comboapostar=new JComboBox<>();
        comboapostar.addItem("Vehiculo 1");
        comboapostar.addItem("Vehiculo 2");
        comboapostar.addItem("Vehiculo 3");

        labelDineroDisponble=new JLabel("Dinero Disponible");
        tfDineroDisponible=new JTextField(String.valueOf(dineroDisponible));
        tfDineroDisponible.setEnabled(false);
        labelMiApuesta=new JLabel("Apostamos");
        tfMiApuesta=new JTextField(String.valueOf(0));
        btnApostar=new JButton("Apostar");
        labelVictorias=new JLabel("Victorias: 0");
        labelDerrotas=new JLabel("Derrotas: 0");
        panelApuestas.add(comboapostar);
        panelApuestas.add(labelDineroDisponble);
        panelApuestas.add(tfDineroDisponible);
        panelApuestas.add(labelMiApuesta);
        panelApuestas.add(tfMiApuesta);
        panelApuestas.add(btnApostar);
        panelApuestas.add(labelVictorias);
        panelApuestas.add(labelDerrotas);




        btnIniciar =new JButton("Iniciar");
        btnParar =new JButton("Parar");
        btnReanudar =new JButton("Reanudar");
        btnAcelerarFrenar =new JButton("Acelerar-Frenar?");

        btnReiniciar =new JButton("Reiniciar");
        btnIniciar.setEnabled(false);
        btnParar.setEnabled(false);
        btnReanudar.setEnabled(false);
        btnReiniciar.setEnabled(false);
        btnAcelerarFrenar.setEnabled(false);

        btnMostrarTiempos =new JButton("Mostrar Tiempos y Apuestas");
        btnMostrarTiempos.setEnabled(false);

        labelWinner1=new JLabel(iconWinnerredimensionado);
        labelWinner2=new JLabel(iconWinnerredimensionado);
        labelWinner3=new JLabel(iconWinnerredimensionado);
        labelWinner1.setBounds(x1,y1,120,100);
        labelWinner2.setBounds(x2,y2,120,100);
        labelWinner3.setBounds(x3,y3,120,100);
        labelWinner1.setVisible(false);
        labelWinner2.setVisible(false);
        labelWinner3.setVisible(false);

        labelGameOver=new JLabel(iconGameOverRedimensionado);
        labelGameOver.setBounds(500,300,700,600);
        labelGameOver.setVisible(false);



        car1=new Vehiculo();
        car1.addPropertyChangeListener(new VehiculoPropertyChangeListener ());
        car1.setPosicionX(x1);
        car1.setPosicionY(y1);

        car2=new Vehiculo();
        car2.addPropertyChangeListener(new VehiculoPropertyChangeListener ());
        car2.setPosicionX(x2);
        car2.setPosicionY(y2);

        car3=new Vehiculo();
        car3.addPropertyChangeListener(new VehiculoPropertyChangeListener ());
        car3.setPosicionX(x3);
        car3.setPosicionY(y3);

        car1.setBounds(car1.getPosicionX(),car1.getPosicionY(),car1.getAncho(),car1.getAlto());
        car2.setBounds(car2.getPosicionX(),car2.getPosicionY(),car2.getAncho(),car2.getAlto());
        car3.setBounds(car3.getPosicionX(),car3.getPosicionY(),car3.getAncho(),car3.getAlto());



        panelbotones=new JPanel();
        panelbotones.setLayout(new GridBagLayout());
        panelCarrera=new PonerImagenPanel(image);
        panelCarrera.setLayout(null);
        panelCarrera.add(car1);
        panelCarrera.add(car2);
        panelCarrera.add(car3);
        panelCarrera.add(labelWinner1);
        panelCarrera.add(labelWinner2);
        panelCarrera.add(labelWinner3);
        panelCarrera.add(labelGameOver);

        panelTiempos=new JPanel(new GridLayout(7,1));
        labelTiempo1=new JLabel("Tiempo Vehiculo 1");
        labelTiempo2=new JLabel("Tiempo Vehiculo 2");
        labelTiempo3=new JLabel("Tiempo Vehiculo 3");
        tfTiempo1=new JTextField();
        tfTiempo2=new JTextField();
        tfTiempo3=new JTextField();
        tfTiempo1.setEnabled(false);
        tfTiempo2.setEnabled(false);
        tfTiempo3.setEnabled(false);
        panelTiempos.add(btnMostrarTiempos);
        panelTiempos.add(labelTiempo1);
        panelTiempos.add(tfTiempo1);
        panelTiempos.add(labelTiempo2);
        panelTiempos.add(tfTiempo2);
        panelTiempos.add(labelTiempo3);
        panelTiempos.add(tfTiempo3);


        panelbotones.setBorder(BorderFactory.createTitledBorder("Botones"));
        panelCarrera.setBorder(BorderFactory.createTitledBorder("Carrera"));
        panelTiempos.setBorder(BorderFactory.createTitledBorder("Tiempos"));
        panelElegirVehiculos.setBorder(BorderFactory.createTitledBorder("Elegir Vehículos"));
        panelApuestas.setBorder(BorderFactory.createTitledBorder("Apostar"));


        panelbotones.add(panelElegirVehiculos,gbc.Constrains(0,0,1,3,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));
        panelbotones.add(panelApuestas,gbc.Constrains(0,3,1,8,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));


        panelbotones.add(btnIniciar,gbc.Constrains(0,11,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));
        panelbotones.add(btnParar,gbc.Constrains(0,12,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));
        panelbotones.add(btnReanudar,gbc.Constrains(0,13,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));
        panelbotones.add(btnAcelerarFrenar,gbc.Constrains(0,14,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));
        panelbotones.add(btnReiniciar,gbc.Constrains(0,15,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER,new Insets(5,5,5,5)));

        panelbotones.add(panelTiempos,gbc.Constrains(0,16,1,1,1.0,0.0,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, new Insets(5,5,5,5)));

        add(panelbotones,gbc.Constrains(0,0,1,1,0.0,0.0,GridBagConstraints.BOTH,GridBagConstraints.WEST,new Insets(5,5,5,5)));
        add(panelCarrera,gbc.Constrains(1,0,4,1,1.0,1.0,GridBagConstraints.BOTH,GridBagConstraints.WEST,new Insets(5,5,5,5)));




        btnIniciar.addActionListener(e->{
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
            labelWinner1.setVisible(false);
            labelWinner2.setVisible(false);
            labelWinner3.setVisible(false);
            tfTiempo1.setText("");
            tfTiempo2.setText("");
            tfTiempo3.setText("");
            anchoPanel=panelCarrera.getWidth();
            car1.setEjecuciones(0);
            car2.setEjecuciones(0);
            car3.setEjecuciones(0);

            System.out.println("vehiculo1: "+car1.isPreparado());
            if(car1.isPreparado()){
                System.out.println("Hola");
                car1.Arrancar(x1,y1,anchoPanel,true);
            }else{
                car1.setCarreraTerminada(true);
                car1.setTiempoCarrera(5000000);
            }
            if(car2.isPreparado()){
                car2.Arrancar(x2,y2,anchoPanel,true);
            }else{
                car2.setCarreraTerminada(true);
                car2.setTiempoCarrera(5000000);
            }
            if(car3.isPreparado()){
                car3.Arrancar(x3,y3,anchoPanel,true);

            }else{
                car3.setCarreraTerminada(true);
                car3.setTiempoCarrera(5000000);
            }





        });

        btnParar.addActionListener(e->{
            car1.Parar();

            car2.Parar();
            car3.Parar();
        });

        btnReanudar.addActionListener(e->{
            anchoPanel=panelCarrera.getWidth();
            car1.ReanudarMarcha(anchoPanel);
            car2.ReanudarMarcha(anchoPanel);
            car3.ReanudarMarcha(anchoPanel);
        });

        btnAcelerarFrenar.addActionListener(e->{
            anchoPanel=panelCarrera.getWidth();
            car1.AcelerarFrenar(anchoPanel);
            car2.AcelerarFrenar(anchoPanel);
            car3.AcelerarFrenar(anchoPanel);
        });

        btnReiniciar.addActionListener(e->{
            labelWinner1.setVisible(false);
            labelWinner2.setVisible(false);
            labelWinner3.setVisible(false);
            car1.IniciarPosicion(x1,y1);
            car2.IniciarPosicion(x2,y2);
            car3.IniciarPosicion(x3,y3);


            car1.setImageUrl(car1.getImageUrl());
            car2.setImageUrl(car2.getImageUrl());
            car3.setImageUrl(car3.getImageUrl());

            tfTiempo1.setText("");
            tfTiempo2.setText("");
            tfTiempo3.setText("");
            car1.setEjecuciones(0);
            car2.setEjecuciones(0);
            car3.setEjecuciones(0);

            btnReanudar.setEnabled(false);
            btnReiniciar.setEnabled(false);
            btnAcelerarFrenar.setEnabled(false);
            btnParar.setEnabled(false);
            btnIniciar.setEnabled(false);

            btnMostrarTiempos.setEnabled(false);






        });

        btnElegir1.addActionListener(e->{
            VentanaEleccionCocheV2 ventanaEleccionCocheV2=new VentanaEleccionCocheV2(this,car1);
            ventanaEleccionCocheV2.setVisible(true);


        });

        btnElegir2.addActionListener(e->{
            VentanaEleccionCocheV2 ventanaEleccionCocheV2=new VentanaEleccionCocheV2(this,car2);
            ventanaEleccionCocheV2.setVisible(true);


        });

        btnElegir3.addActionListener(e->{
            VentanaEleccionCocheV2 ventanaEleccionCocheV2=new VentanaEleccionCocheV2(this,car3);
            ventanaEleccionCocheV2.setVisible(true);


        });
        btnMostrarTiempos.addActionListener(e->{
            if(car1.isCarreraTerminada() && car2.isCarreraTerminada() && car3.isCarreraTerminada()){

                if(car1.getTiempoCarrera()==5000000.0){
                    tfTiempo1.setText("Averiado");
                }else{
                    tfTiempo1.setText(String.valueOf(car1.getTiempoCarrera()/1000)+" Seg.");
                }
                if(car2.getTiempoCarrera()==5000000.0){
                    tfTiempo2.setText("Averiado");
                }else{
                    tfTiempo2.setText(String.valueOf(car2.getTiempoCarrera()/1000)+" Seg.");
                }
                if(car3.getTiempoCarrera()==5000000.0){
                    tfTiempo3.setText("Averiado");
                }else{
                    tfTiempo3.setText(String.valueOf(car3.getTiempoCarrera()/1000)+" Seg.");
                }


                car1.setCarreraTerminada(false);
                car2.setCarreraTerminada(false);
                car3.setCarreraTerminada(false);
                if(car1.getTiempoCarrera() < car2.getTiempoCarrera() && car1.getTiempoCarrera() < car3.getTiempoCarrera()){
                    labelWinner1.setVisible(true);
                    ganador=1;
                }
                if (car2.getTiempoCarrera() < car3.getTiempoCarrera()&&car2.getTiempoCarrera() < car1.getTiempoCarrera()){
                    labelWinner2.setVisible(true);
                    ganador=2;
                }
                if (car3.getTiempoCarrera() < car1.getTiempoCarrera()&&car3.getTiempoCarrera()< car2.getTiempoCarrera()){
                    labelWinner3.setVisible(true);
                    ganador=3;
                }
                if(comboapostar.getSelectedItem().equals("Vehiculo 1")){
                    if(ganador==1){
                        CarreraGanada();

                    }else{
                        CarreraPerdida();
                    }
                }
                if(comboapostar.getSelectedItem().equals("Vehiculo 2")){
                    if(ganador==2){
                        CarreraGanada();
                    }else{
                        CarreraPerdida();
                    }
                }
                if(comboapostar.getSelectedItem().equals("Vehiculo 3")){
                    if(ganador==3){
                        CarreraGanada();
                    }else{
                        CarreraPerdida();
                    }
                }





            } else{
                tfTiempo1.setText("Vehículos en  carrera");
                tfTiempo2.setText("Vehículos en  carrera");
                tfTiempo3.setText("Vehículos en  carrera");
            }




        });

        btnApostar.addActionListener(e->{
            if(Integer.parseInt(tfMiApuesta.getText())>dineroDisponible){
                JOptionPane.showMessageDialog(null,"No hay fondos suficientes para cubrir esa apuesta");
            }else{
                JOptionPane.showMessageDialog(null,"Apuesta registrada");
                apuestaRegistrada=Integer.parseInt(tfMiApuesta.getText());
                btnIniciar.setEnabled(true);
                btnParar.setEnabled(true);
                btnReanudar.setEnabled(true);
                btnReiniciar.setEnabled(true);
                btnAcelerarFrenar.setEnabled(true);

                btnMostrarTiempos.setEnabled(true);
            }
        });











    }

    private void EstablecerFuenteComponentes() {
        // Obtener el UIManager actual
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            if ("Nimbus".equals(look.getName())) {
                try {
                    UIManager.setLookAndFeel(look.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        // Establecer la nueva fuente para todos los componentes
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fuente);
            }
        }
    }

    private void CarreraPerdida() {
        JOptionPane.showMessageDialog(null,"Has Perdido :(, sigue intentandolo");
        ComprobarApuesta(false);
        derrotas++;
        labelDerrotas.setText("Derrotas: "+String.valueOf(derrotas));
        if(Integer.parseInt(tfDineroDisponible.getText())==0){
            GameOver();

        }
    }

    private void GameOver() {
        labelGameOver.setVisible(true);
        btnApostar.setEnabled(false);
        btnReanudar.setEnabled(false);
        btnReiniciar.setEnabled(false);
        btnAcelerarFrenar.setEnabled(false);
        btnParar.setEnabled(false);
        btnIniciar.setEnabled(false);
        btnElegir1.setEnabled(false);
        btnElegir2.setEnabled(false);
        btnElegir3.setEnabled(false);
        btnMostrarTiempos.setEnabled(false);
        comboapostar.setEnabled(false);
        tfMiApuesta.setEnabled(false);
    }

    private void CarreraGanada() {
        JOptionPane.showMessageDialog(null,"Has Ganado :), enhorabuena");
        ComprobarApuesta(true);
        victorias++;
        labelVictorias.setText("Victorias: "+String.valueOf(victorias));
    }

    private void ComprobarApuesta(boolean ganada) {
        if(ganada){
            tfDineroDisponible.setText(String.valueOf(dineroDisponible+apuestaRegistrada));
            dineroDisponible+=apuestaRegistrada;
        }else{
            tfDineroDisponible.setText(String.valueOf(dineroDisponible-apuestaRegistrada));
            dineroDisponible-=apuestaRegistrada;
        }
    }

    public void updatePanelImage(Image image, Vehiculo car) {
        car.setImagenSeleccionada(image);
    }

    public static ImageIcon redimensionarIcono(ImageIcon iconoOriginal, int ancho, int alto) {
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
