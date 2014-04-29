package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import record.misc.Album;

public class AlbumDialog extends JDialog implements ActionListener
{
	Album album;
	//TODO
//	JTextField idField = new JTextField();
	JTextField albumField = new JTextField();
	JTextField artistField = new JTextField();
	JTextField releaseField = new JTextField();
	private static final String OK = "ok";
	private static final String CANCEL = "cancel";
	
	public AlbumDialog()
	{
		JPanel mainPanel = new JPanel(new BorderLayout());
			setContentPane(mainPanel);
			mainPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		JPanel inputPanel = new JPanel(new GridLayout(3,2,5,5));
		JPanel buttonPanel = new JPanel(new FlowLayout());
			
		JLabel albumLabel = new JLabel("Album:");
		JLabel artistLabel = new JLabel("Artist:");
		JLabel releaseLabel = new JLabel("Release:");
		
		JButton okButton = new JButton("OK");
			okButton.addActionListener(this);
			okButton.setActionCommand(OK);
		JButton cancelButton = new JButton("Avbryt");
			cancelButton.addActionListener(this);
			cancelButton.setActionCommand(CANCEL);
		
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		inputPanel.add(albumLabel);
		inputPanel.add(albumField);
		inputPanel.add(artistLabel);
		inputPanel.add(artistField);
		inputPanel.add(releaseLabel);
		inputPanel.add(releaseField);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		setModal(true);
		
		setPreferredSize(new Dimension(250,150));
//		inputPanel.setPreferredSize(new Dimension(200,100));
//		buttonPanel.setPreferredSize(new Dimension(50,50));
		pack();
		setLocationRelativeTo(null);
	}
	public AlbumDialog(Album editAlbum)
	{
		this();
		album = editAlbum;
		albumField.setText(album.getAlbumName());
		artistField.setText(album.getArtistName());
		releaseField.setText(""+album.getReleaseYear());
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		// TODO Auto-generated method stub
		if(event.getActionCommand() == OK)
		{
//			int albumId = Integer.parseInt(idField.getText());
			
			
			String albumName = albumField.getText();
			String artistName = artistField.getText();
			int releaseYear = -1;
			
			albumName.trim();
			artistName.trim();
			
			String errorMessage = "";
			
			if (albumName.equals(""))
			{
				errorMessage += "Fyll i albumets namn \n";
			}
			else if (artistName.equals(""))
			{
				errorMessage += "Fyll i artistens namn \n";
			}
			else if (artistName.equals(""))
			{
				errorMessage += "Fyll i releaseåret \n";
			}
			
			try {
				releaseYear = Integer.parseInt(releaseField.getText());
			} catch (NumberFormatException e) {
				// TODO: handle exception
				errorMessage += "Fyll endast i SIFFROR för releaseåret \n";
			}
			
			JOptionPane.showMessageDialog(this, errorMessage);
			
			if (album == null)
			{
				album = new Album(albumName, artistName, releaseYear);
			}
			else
			{
				album.setAlbumName(albumName);
				album.setArtistName(artistName);
				album.setReleaseYear(releaseYear);
			}
			dispose();
		}
		else if(event.getActionCommand() == CANCEL)
		{
			album = null;
			dispose();
		}
	}
	public Album getAlbum()
	{
		return album;
	}
}
