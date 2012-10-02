package USTC_Clotho_App;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
class LinkLabel extends JLabel {  
    private String text, url;  
    private boolean isSupported;  
  
    public LinkLabel(String text, String url) {  
        this.text = text;  
        this.url = url;  
        try {  
            this.isSupported = Desktop.isDesktopSupported()  
            && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);  
        } catch (Exception e) {  
            this.isSupported = false;  
        }  
        setText(false);  
        addMouseListener(new MouseAdapter() {  
            public void mouseEntered(MouseEvent e) {  
                setText(isSupported);  
                if (isSupported)  
                    setCursor(new Cursor(Cursor.HAND_CURSOR));  
            }  
  
            public void mouseExited(MouseEvent e) {  
                setText(false);  
            }  
  
            public void mouseClicked(MouseEvent e) {  
                try {  
                    Desktop.getDesktop().browse(  
                            new java.net.URI(LinkLabel.this.url));  
                } catch (Exception ex) {  
                }  
            }  
        });  
    }  
  
    private void setText(boolean b) {  
        if (!b)  
            setText("<html><font color=blue><u>" + text);  
        else  
            setText("<html><font color=red><u>" + text);  
    }  
  
       
}  
