package br.com.codigojava.cjswing;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class CJDialogMessageDetail extends JDialog implements WindowListener{

	private static final long serialVersionUID = 9216349891745125965L;
	
	private static CJDialogMessageDetail dialogMessage;
	private JTextArea textArea, textAreaDetail;
	private JButton okButton;
	private static boolean detail;

	public static CJDialogMessageDetail create(String title, String resume, String detail){
		if(dialogMessage == null){
			dialogMessage = new CJDialogMessageDetail(title, resume, detail);
			dialogMessage.setLocationRelativeTo(null);
			dialogMessage.setVisible(true);
			//optionPane.setModal(true);
		}else{
			dialogMessage.toFront();
		}
		return dialogMessage;
	}
	public static CJDialogMessageDetail create(String title, String resume, String detail, Image imageIcon){
		if(dialogMessage == null){
			dialogMessage = new CJDialogMessageDetail(title, resume, detail);
			dialogMessage.setIconImage(imageIcon);
			dialogMessage.setLocationRelativeTo(null);
			dialogMessage.setVisible(true);
			//optionPane.setModal(true);
		}else{
			dialogMessage.toFront();
		}
		return dialogMessage;
	}
	//METODO QUE DESTROI UMA INSTANCIA DESTA CLASSE
	public static CJDialogMessageDetail destroy(){
		if(dialogMessage != null){
			dialogMessage.setVisible(false);
			dialogMessage = null;
		}
		return dialogMessage;
	}
	/**
	 * Create the dialog.
	 */
	public CJDialogMessageDetail(String title, String resume, String detalhe) {
		detail = false;
		setModal(true);
		setTitle(title);
		addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CJDialogMessageDetail.class.getResource("/img/icone.png")));
		setSize(450, 230);
		//insere no InputMap o comando FECHA para a tecla ESC
        this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true),"FECHA");  
        //no ActionMap insere o comando FECHA do InputMap e cria uma acao para ele
        this.getRootPane().getActionMap().put("FECHA", new AbstractAction() {  
			private static final long serialVersionUID = -7898802343052218682L;
			@Override
			public void actionPerformed(ActionEvent e) {  
        		destroy(); 
	        }  
        });
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent paramActionEvent) {
					CJDialogMessageDetail.destroy();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPaneDetail = new JScrollPane();
		scrollPaneDetail.setSize(450, 150);
		scrollPaneDetail.setVisible(false);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int lF = getWidth();
				if(!detail){
					button.setIcon(new ImageIcon(CJDialogMessageDetail.class.getResource("/img/up-16.png")));
					scrollPaneDetail.setVisible(true);
					setSize(lF, 380);
					detail = true;
				}else{
					button.setIcon(new ImageIcon(CJDialogMessageDetail.class.getResource("/img/down-16.png")));
					scrollPaneDetail.setVisible(false);
					setSize(lF, 230);
					detail = false;
				}
			}
		});
		button.setIcon(new ImageIcon(CJDialogMessageDetail.class.getResource("/img/down-16.png")));
		button.setToolTipText("Detalhes");
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		JLabel lblDetalhe = new JLabel("Detalhes");
		lblDetalhe.setFont(new Font("Monospaced", Font.PLAIN, 13));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneDetail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDetalhe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDetalhe, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(5))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(5)))
					.addComponent(scrollPaneDetail, GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(okButton)
					.addContainerGap())
		);
		
		textAreaDetail = new JTextArea();
		textAreaDetail.setWrapStyleWord(true);
		textAreaDetail.setLineWrap(true);
		textAreaDetail.setEditable(false);
		textAreaDetail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textAreaDetail.setBackground(SystemColor.menu);
		textAreaDetail.setText(detalhe);
		scrollPaneDetail.setViewportView(textAreaDetail);
		
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		{
			textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			textArea.setBackground(UIManager.getColor("Panel.background"));
			textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			textArea.setText(resume);
		}
		getContentPane().setLayout(groupLayout);
	}
	
	@Override
	public void windowOpened(WindowEvent paramWindowEvent) {}
	@Override
	public void windowClosing(WindowEvent paramWindowEvent) {
		CJDialogMessageDetail.destroy();		
	}
	@Override
	public void windowClosed(WindowEvent paramWindowEvent) {
		CJDialogMessageDetail.destroy();
	}
	@Override
	public void windowIconified(WindowEvent paramWindowEvent) {}
	@Override
	public void windowDeiconified(WindowEvent paramWindowEvent) {}
	@Override
	public void windowActivated(WindowEvent paramWindowEvent) {}
	@Override
	public void windowDeactivated(WindowEvent paramWindowEvent) {}
}
