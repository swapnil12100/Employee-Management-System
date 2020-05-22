import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblID, lblName, lblSal;
JTextField txtID, txtName, txtSal;
JButton btnSave, btnBack;
int id;
String name;
double salary;

UpdateFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblID = new JLabel("Enter Id");
lblName = new JLabel("Enter name");
lblSal = new JLabel("Enter salary");
txtID = new JTextField(20);
txtName = new JTextField(20);
txtSal = new JTextField(20);
btnSave = new JButton("Update");
btnBack = new JButton("Back");

c.add(lblID);
c.add(txtID);
c.add(lblName);
c.add(txtName);
c.add(lblSal);
c.add(txtSal);
c.add(btnSave);
c.add(btnBack);

ActionListener a1 = (ae) -> 
{
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
	if (txtID.getText().isEmpty())
	JOptionPane.showMessageDialog(c,"Enter Id");
	if (txtName.getText().isEmpty())
	JOptionPane.showMessageDialog(c,"Enter Name");
	if (txtSal.getText().isEmpty())
	JOptionPane.showMessageDialog(c,"Enter salary");

try {
id = Integer.parseInt(txtID.getText());
}
catch(NumberFormatException nfe) 
{
JOptionPane.showMessageDialog(c,"Please Enter Valid Employee Id");
txtID.setText("");
txtID.requestFocus();
}

Employee e = (Employee)session.get(Employee.class, id);

if(e != null)

name = txtName.getText();
if(name.length() < 2)
JOptionPane.showMessageDialog(c,"Name should have 2 or more letters");
txtName.setText("");
txtName.requestFocus();

e.setName(name);

try {
salary = Double.parseDouble(txtSal.getText());
if (salary < 8000)
JOptionPane.showMessageDialog(c,"Minimum Salary Should Be 8000");
txtSal.setText("");
txtSal.requestFocus();
}
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(c,"Please Enter Valid Salary");
txtSal.setText("");
txtSal.requestFocus();
}

e.setSalary(salary);
session.save(e);		
t.commit();
JOptionPane.showMessageDialog(c, "Record Updated");

txtID.setText("");
txtID.requestFocus();
txtName.setText("");
txtName.requestFocus();
txtSal.setText("");
txtSal.requestFocus();
}
catch(Exception e)
{
JOptionPane.showMessageDialog(c, "Some issue");
}

finally
{
session.close();
}

};
btnSave.addActionListener(a2);

setSize(300,200);
setTitle("Update Employee Details");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}
}