import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;

public class SimplexResolution extends JFrame {

	private JPanel contentPane;
	static MethodSimplexe ms;
	 private JTable table;
	 static int a,b,c;
	 private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimplexResolution frame = new SimplexResolution();
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
	public SimplexResolution() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		LectureFich l=new LectureFich();
		l.formerPl();
		l.mettreSousformeStandrad();
		Object [] con=l.mat.get(0).toArray();
	    ms=new MethodSimplexe(l.RetournerTableau());
		ms.RetournPositionVBS();ms.remplirRatio();
		 Object [][] data=ms.mat;
		DefaultTableModel model = new DefaultTableModel(data,con);		
		 JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35,145, 395, 110);
		contentPane.add(scrollPane);
		
		JTable table = new JTable(model);
		
		Object[][]tab=l.RetournerTableau();
		table = new JTable(model){
			public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
 	        Component comp = super.prepareRenderer(renderer,Index_row, Index_col);
 	       JComponent jcomp = (JComponent)comp;
 	          
 	            
 	            if (Index_col==c){
 	                comp.setBackground(Color.green);
 	            }
 	            return comp;
 	        }
			
		};
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    { 
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row == a ? Color.lightGray : Color.WHITE);
		        return c;
		    }
		});	scrollPane.setViewportView(table);
	
	JLabel lblNewLabel = new JLabel("R\u00E9solution d'un PL");
	lblNewLabel.setForeground(Color.BLUE);
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
	lblNewLabel.setBounds(79, 23, 470, 74);
	contentPane.add(lblNewLabel);
	
	textField = new JTextField();
	textField.setFont(new Font("Times New Roman", Font.BOLD, 18));
	textField.setBackground(new Color(255, 239, 213));
	textField.setForeground(new Color(0, 0, 0));
	textField.setBounds(450, 173, 173, 33);
	contentPane.add(textField);
	textField.setColumns(10);
	
	JSeparator separator = new JSeparator();
	separator.setBackground(new Color(0, 0, 0));
	separator.setBounds(19, 266, 411, 2);
	contentPane.add(separator);
	c=ms.RetournPositionVHBE();
	ms.remplirRatio();
		 a=ms.RetournPositionVBS();
		 
          
		boolean cond=true;int x=270;
	 while(cond)
		 {
			 
			
         	ms.calculTableauInitial();
         	ms.remplirRatio();
         	Object [][]data1=ms.mat;
			 model = new DefaultTableModel(data1,con);
			 JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(35, x, 395, 100);
				contentPane.add(scrollPane1);
				
				 table = new JTable(model){
						public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
			 	        Component comp = super.prepareRenderer(renderer,Index_row, Index_col);
			 	       JComponent jcomp = (JComponent)comp;
			 	     
			 	           
			 	            if (Index_col==b){
			 	                comp.setBackground(Color.green);
			 	            }
			 	            return comp;
			 	        }
						
					};
					table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
					{
					    @Override
					    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
					    { 			 	     // int a=ms.RetournPositionVBS();

					        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					        c.setBackground(row == a ? Color.lightGray : Color.WHITE);
					        return c;
					    }
					});
				 scrollPane1.setViewportView(table);
		          cond=ms.conditionCon(data1);
		    		ms=new MethodSimplexe(data1);
				      b= ms.RetournPositionVHBE();
		    if(cond!=true)
			 {
				 textField.setText(" Z = "+ms.mat[1][tab[0].length-1]);
			 }
			 x+=120;
		
		
		
		 }
		
	}
}
