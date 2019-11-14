package br.com.codigojava.cjswing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

public class PanelTab extends JPanel {
	private static final long serialVersionUID = -5544834344999424233L;
	private static JTabbedPane tabbedPane;
    
    public static void closeTab(String tabLabel){        
        if (tabbedPane.getTabCount() > 0) {
            for(int i=0; i <= tabbedPane.getTabCount(); i++){
                if(tabbedPane.getTitleAt(i).equalsIgnoreCase(tabLabel))
                    tabbedPane.remove(i);
            }
        }
    }
    public static void closeTab(int indexTab){        
        if (tabbedPane.getTabCount() > 0) {
            tabbedPane.remove(indexTab);
        }
    }

    public static int getSelectedIndex(){
        return tabbedPane.getSelectedIndex();
    }

    public static int getPositionPanel(String tabLabel){
        if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
            for(int i=0; i<tabbedPane.getTabCount(); i++){
                if(tabbedPane.getTitleAt(i).equalsIgnoreCase(tabLabel)){
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static boolean existeTabPanel(String tabLabel){
        if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
            for(int i=0; i<tabbedPane.getTabCount(); i++){
                if(tabbedPane.getTitleAt(i).equalsIgnoreCase(tabLabel)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public PanelTab(final JTabbedPane pane) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        PanelTab.tabbedPane = pane;
        super.setOpaque(false);
        //faz a JLabel ler o título do JTabbedPane
        JLabel label = new JLabel() {
			private static final long serialVersionUID = -8446465530509848551L;

			@Override
            public String getText() {
                int i = pane.indexOfTabComponent(PanelTab.this);
                if (i != -1) {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };
        super.add(label);
        //adiciona mais espaço entre a label e o botão
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        JButton button = new TabButton();
        add(button);
        //adiciona mais espaço para o topo do componente
        super.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
    
    //cria o botão para fechar o panel
    private class TabButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1363712628560052032L;
		public TabButton() {
            int size = 17;
            super.setPreferredSize(new Dimension(size, size));
            super.setToolTipText("Fechar "+tabbedPane.getTitleAt(getSelectedIndex()));
            //Faz o botão ser igual para todas as Laf's
            super.setUI(new BasicButtonUI());
            //Torna-o transparente
            super.setContentAreaFilled(false);
            //Não necessidade de estar com focusable
            super.setFocusable(false);
            super.setBorder(BorderFactory.createEtchedBorder());
            super.setBorderPainted(false);
            //Fazendo um efeito de rolagem
            //usamos o mesmo listener para todos os botões
            super.addMouseListener(buttonMouseListener);
            super.setRolloverEnabled(true);
            //Fecha a guia apropriada, clicando no botão
            super.setIcon(new ImageIcon(getClass().getResource("/img/close-button.png")));
            addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = tabbedPane.indexOfTabComponent(PanelTab.this);
            if (i != -1) {
                tabbedPane.remove(i);
            }
        }
        //pinta o X
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //mudança na imagem para botões pressionados
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }
    
    private final MouseListener buttonMouseListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}