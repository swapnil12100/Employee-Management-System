import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblid,lblname,lblsalary;
JTextField txtid,txtname,txtsalary;
JButton btnSave,btnBack;

AddFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

lblid = new JLabel("Enter Id");
lblname = new JLabel("Enter name");
lblsalary = new JLabel("Enter salary");
txtid = new JTextField(15);
txtname = new JTextField(15);
txtsalary = new JTextField(15);
btnSave = new JButton("Save");
btnBack = new JButton("Back");
c.add(lblid);
c.add(txtid);
c.add(lblname);
c.add(txtname);
c.add(lblsalary);
c.add(txtsalary);
c.add(btnSave);
c.add(btnBack);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();
Transaction t = null;
try
{
t = session.beginTransaction();

String a1 = txtid.getText();
String a2 = txtname.getText();
String a3 = txtsalary.getText();
if(a1.isEmpty())
{
JOptionPane.showMessageDialog(c,"enter id");
}

if(a2.isEmpty())
{
JOptionPane.showMessageDialog(c,"enter name");
}

if(a3.isEmpty())
{
JOptionPane.showMessageDialog(c,"enter salary");
}

try
{
Integer.parseInt(a1);
}
catch(NumberFormatException ne)
{
JOptionPane.showMessageDialog(c,"please enter integer values");
txtid.setText("");
txtid.requestFocus();
}

if(Integer.parseInt(a1) < 0)
{
JOptionPane.showMessageDialog(c,"please enter positive integer");
txtid.setText("");
txtid.requestFocus();
}

try
{
Double.parseDouble(a3);
}
catch(NumberFormatException ne)
{
JOptionPane.showMessageDialog(c,"Invalid salary");
txtsalary.setText("");
txtsalary.requestFocus();
}

if(Double.parseDouble(a3) < 8000)
{
JOptionPane.showMessageDialog(c,"salary must be greater than 8000");
txtsalary.setText("");
txtsalary.requestFocus();
}

if(txtname.getText().matches("[A-z][a-z]*"))
{
}
else
{
JOptionPane.showMessageDialog(c,"invalid name format");
txtname.setText("");
txtname.requestFocus();
}

if(txtname.getText().length() < 2)
{
JOptionPane.showMessageDialog(c,"invalid name");
txtname.setText("");
txtname.requestFocus();
}

Employee e = new Employee();
int id = Integer.parseInt(txtid.getText());
String name = txtname.getText();
double salary = Double.parseDouble(txtsalary.getText());

e.setId(id);
e.setName(name);
e.setSalary(salary);
session.save(e);
t.commit();
JOptionPane.showMessageDialog(c,"record added");
txtname.setText("");
txtname.requestFocus();
txtsalary.setText("");
txtsalary.requestFocus();
txtid.setText("");
txtid.requestFocus();
}
catch(Exception e)
{
t.rollback();
JOptionPane.showMessageDialog(c,"some issue");
txtid.setText("");
txtid.requestFocus();
}
finally{
session.close();
}

}
});

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a = new MainFrame();
dispose();
}
});

setTitle("Add");
setSize(250,400);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}