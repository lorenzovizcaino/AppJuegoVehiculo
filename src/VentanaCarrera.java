import javax.swing.*;

public class VentanaCarrera extends JFrame {

    public VentanaCarrera(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setTitle("Carrera de Vehiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LaminaCarrera laminaCarrera=new LaminaCarrera();
        add(laminaCarrera);
        setVisible(true);
    }

}
