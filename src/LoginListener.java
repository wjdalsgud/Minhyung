import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		Object source= e.getSource();
		new LoginNextMain();	
}
}