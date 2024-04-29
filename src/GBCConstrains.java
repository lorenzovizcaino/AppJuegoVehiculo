import java.awt.*;

public class GBCConstrains {


    private GridBagConstraints gbc=new GridBagConstraints();



    public GridBagConstraints Constrains(int gridx,int gridy,int gridwidth,int gridheight, double weightx, double weighty,int fill,int anchor, Insets insets){
        gbc.gridx=gridx;
        gbc.gridy=gridy;
        gbc.gridwidth=gridwidth;
        gbc.gridheight=gridheight;
        gbc.weightx=weightx;
        gbc.weighty=weighty;
        gbc.anchor=anchor;
        gbc.fill=fill;
        gbc.insets=insets;
        return gbc;
    }




}