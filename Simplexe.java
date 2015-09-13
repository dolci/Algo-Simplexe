import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Simplexe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	  static String nomFich;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simplexe frame = new Simplexe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 public boolean verifierFichier()
	    {
	    	StringTokenizer st=new StringTokenizer(nomFich,".");
	    	if(st.countTokens()==2)
	    	{
	    	     String ext=st.nextToken();
	    	    	ext=st.nextToken();
	    		if(ext.equals("txt")) return true;
	    		
	    	}
	    	return false;
	    }
	public Simplexe() {
		setTitle("Simplexe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		
		textField.setBounds(10, 39, 148, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Parcourir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser chooser = new JFileChooser();
			        int returnVal = chooser.showOpenDialog(getParent());
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			         //  System.out.println(chooser.getSelectedFile().getName());
			           textField.setText(chooser.getSelectedFile().getName());
			           nomFich=chooser.getSelectedFile().getPath();
			         
			         if(! verifierFichier()) 
			         { JOptionPane.showMessageDialog(null,"choisir fichier .txt ");System.out.println(".txt ou bien ex");}
			        
			        	
			         }
			         
			    
				
				
			}
		});
		btnNewButton.setBounds(168, 39, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Resolution");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String as = new String ("\\"); 
				String das = new String("\\\\"); 
				nomFich=nomFich.replace(as,das);
				System.out.println(nomFich);
				SimplexResolution f=new SimplexResolution();
	        	 f.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(168, 71, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
