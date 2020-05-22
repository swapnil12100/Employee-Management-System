import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblID;
JButton btnSave, btnBack;
JTextField txtID;
int id;

DeleteFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblID = new JLabel("Enter ID");
txtID = new JTextField(20);
btnSave = new JButton("Delete");
btnBack = new JButton("Back");
c.add(lblID);
c.add(txtID);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> 
{

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;

try
{
	t = session.beginTransaction();

	try {
	id = Integer.parseInt(txtID.getText());
	if (txtID.getText().isEmpty())
	JOptionPane.showMessageDialog(c,"Enter Id");
		
	}
	catch(NumberFormatException nfe) {
	JOptionPane.showMessageDialog(c,"Please Enter Valid Employee Id");
	txtID.setText("");
	txtID.requestFocus();
	}

	Employee e = (Employee)session.get(Employee.class, id);
	if(e != null)
	{
	session.delete(e);
	t.commit();
	txtID.setText("");
	txtID.requestFocus();
	JOptionPane.showMessageDialog(c, "record has been deleted.");
	}
	else
	{
	JOptionPane.showMessageDialog(c, "Record does not exist");
	}	
}
catch(Exception e)
{
t.rollback();
JOptionPane.showMessageDialog(c, "Some issue");
}
finally 
{
session.close();
}

};
btnSave.addActionListener(a2);

setSize(300,400);
setTitle("Delete employee details");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}