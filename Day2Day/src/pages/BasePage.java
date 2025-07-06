// Java
package pages;

import javax.swing.*;
import java.awt.*;

public abstract class BasePage {
    public JFrame frame;

    public BasePage(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout());
    }

    protected void addComponent(Component component, int x, int y, int width, int height, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        frame.add(component, gbc);
    }

    public abstract void initializeComponents();

    public void showPage() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
