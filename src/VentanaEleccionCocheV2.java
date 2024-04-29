import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VentanaEleccionCocheV2 extends JFrame {
    private LaminaCarrera laminaCarrera;
    private JPanel panelPrincipal, panelElementos;
    private Image image;


    public VentanaEleccionCocheV2(LaminaCarrera laminaCarrera, Vehiculo car) {
        //File bicicleta=new File("image/bicicleta.png");

        getContentPane().setLayout(new GridLayout(5, 1));
        setTitle("Elegir Vehiculo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.laminaCarrera = laminaCarrera;

        ButtonGroup buttonGroup = new ButtonGroup();

        ImageIcon[] icons = new ImageIcon[]{
                new ImageIcon("image/bicicleta.png"),
                new ImageIcon("image/carrito_Bebe.png"),
                new ImageIcon("image/ferrari6.png"),
                new ImageIcon("image/moto.png"),
                new ImageIcon("image/nave.png")
        };

        for (ImageIcon icon : icons) {
            JRadioButton radioButton = new JRadioButton();


            ImageIcon iconRedimensionado=redimensionarIcono(icon,280,150);
            radioButton.setIcon(iconRedimensionado);

            radioButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (radioButton.isSelected()) {

                        car.setImageUrl(icon.toString());

                        image=iconRedimensionado.getImage();
                        laminaCarrera.updatePanelImage(image,car);
                    }
                }
            });

            buttonGroup.add(radioButton);
            getContentPane().add(radioButton);

        }


        setVisible(true);
        setSize(295,820);
        //pack(); //ajusta la ventana al tamaño necesario
        setLocationRelativeTo(laminaCarrera);
    }


    public static ImageIcon redimensionarIcono(ImageIcon iconoOriginal, int ancho, int alto) {
        Image imagenOriginal = iconoOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenRedimensionada);
    }
}
