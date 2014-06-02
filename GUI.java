package project3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 300;
	private JComboBox country;
	private JLabel Area;
	private JLabel Population;
	
	public GUI(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		country = new JComboBox();
		country.addItem("Antarctica");
		country.addItem("Canada");
		country.addItem("Egypt");
		country.addItem("Fiji");
		country.addItem("Greece");
		country.addItem("Indonesia");
		country.addItem("Japan");
		country.addItem("Mexico");
		country.addItem("United States");
		country.addItem("Vietnam");
		
		
		JButton button= new JButton("Get Info");
		button.addActionListener(new ButtonListener());
		
		JLabel area = new JLabel();
		JLabel population= new JLabel();
		Area = new JLabel();
		Population= new JLabel();
		
		area.setText("Area");
		Area.setText("");
		population.setText("Population");
		Population.setText("");
		
		panel.add(country);
		panel.add(button);
		panel.add(area);
		panel.add(population);
		panel.add(Area);
		panel.add(Population);
		
		add(panel);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			try {
				GetInfo();
			} catch (IOException e) {
			}
		}
	}
	public void GetInfo() throws IOException{
		int index = country.getSelectedIndex();
		String[] ref = {"ay","ca","eg","fj","gr","id","ja","mx","us","vm"} ;
		String urlString;
	    String x = null;
	    String y= null;
	    int code=0;
	    urlString = "https://www.cia.gov/library/publications/the-world-factbook/geos/"+ref[index]+".html";

	    // Open connection

	    URL u = new URL(urlString);
	    URLConnection connection = u.openConnection();

	    // Check if response code is HTTP_OK (200)
	     
	    HttpURLConnection httpConnection = (HttpURLConnection) connection;
	    try{
	    	code = httpConnection.getResponseCode();
	    }
	    catch(UnknownHostException e){
	    	Area.setText("No Internet Connection");
	    	Population.setText("No Internet Connection");
	    }
	    if (code != HttpURLConnection.HTTP_OK){
	         return;
	    }
	    
	      
	    // Read server response
	      
	    InputStream instream = connection.getInputStream();
	    Scanner in = new Scanner(instream);
	    
	    while (true){
	    	  if (in.nextLine().contains("fields/2147")){
	    		  while (in.hasNextLine()){
	    			  y=in.nextLine();
	    			  if(y.contains("<span class")){
	    				  break;
	    			  }
	    		  }
	    		  break;
	    	  }
	      }
	      while (true){
	    	  if (in.nextLine().contains("fields/2119.html")){
	    		  while (in.hasNextLine()){
	    			  x=in.nextLine();
	    			  if(x.contains("<div class")){
	    				  break;
	    			  }
	    		  }
	    		  break;
	    	  }
	      }
	    int startx=x.indexOf('>');
	    int endx =x.indexOf("</div>");
	    Population.setText("<html>"+x.substring(startx+1, endx)+"</html>");
	    int starty=y.indexOf("m;");
	    int endy =y.indexOf("</span>");
	    Area.setText(y.substring(starty+4, endy));
	}
}