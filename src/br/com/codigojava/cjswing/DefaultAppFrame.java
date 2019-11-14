package br.com.codigojava.cjswing;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class DefaultAppFrame extends CJFrame {
	
	private static final long serialVersionUID = 8672344578618578144L;
	
	protected static JTabbedPane tabbedPane;
	protected static JPanel contentPane;
	protected static JMenuBar menuBar;

	public static void addInternalFrame(JInternalFrame frame){
		tabbedPane.addTab(frame.getTitle(), frame.getFrameIcon(), frame, frame.getToolTipText());
		frame.setVisible(true);
		PanelTab pa = new PanelTab(tabbedPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, pa);	
	}
	
	public static void addInternalFrame(JInternalFrame frame, int position){
		tabbedPane.addTab(frame.getTitle(), frame.getFrameIcon(), frame, frame.getToolTipText());
		frame.setVisible(true);
		PanelTab pa = new PanelTab(tabbedPane);
		tabbedPane.setTabComponentAt(position, pa);	
	}

	public static void addInternalFrame(JInternalFrame frame, int position, Icon icon){
		tabbedPane.addTab(frame.getTitle(), frame.getFrameIcon(), frame, frame.getToolTipText());
		frame.setVisible(true);
		PanelTab pa = new PanelTab(tabbedPane);
		tabbedPane.setTabComponentAt(position, pa);	
	}
    
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

	public DefaultAppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setMinimumSize(new Dimension(1024, 768));
		setExtendedState(MAXIMIZED_BOTH);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 2816, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
					.addGap(1))
		);
		contentPane.setLayout(gl_contentPane);
	}
}