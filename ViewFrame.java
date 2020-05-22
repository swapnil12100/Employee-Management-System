import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame
{
Container c;
TextArea ta;
JButton btnBack;

ViewFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

ta = new TextArea(5,30);
btnBack = new JButton("Back");

c.add(ta);
c.add(btnBack);

ActionListener a1 = (ae) -> 
{
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

try
{
java.util.List<Employee> emp = new ArrayList<Employee>();
emp = session.createQuery("from Employee").list();

for(Employee e : emp)
ta.append(e.getId() + " " + e.getName() + " " + e.getSalary() + "\n");
}
catch(Exception e)
{
JOptionPane.showMessageDialog(c, "Some issue");
}
finally 
{
session.close();
}

setSize(300,400);
setTitle("Employee Details");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}