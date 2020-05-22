import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd, btnView, btnUpdate, btnDelete;

MainFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());
btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

ActionListener a1 = (ae) ->
{
AddFrame a = new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2 = (ae) ->
{
ViewFrame a = new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3 = (ae) ->
{
UpdateFrame a = new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4 = (ae) ->
{
DeleteFrame a = new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

setSize(300,400);
setTitle("Employee Management System");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);

}

public static void main(String args[])
{
MainFrame mf = new MainFrame();
}
}