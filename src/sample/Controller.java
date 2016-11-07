package sample;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sun.text.resources.cldr.uz.FormatData_uz_Latn;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.io.*;

import static java.awt.SystemColor.text;

public class Controller extends Component {


    public GridPane Formulario;
    public MenuItem Men_item_close;
    public MenuItem Men_item_copy;
    public MenuItem Men_item_cut;
    public MenuItem Men_item_paste;
    public MenuItem Men_item_undo;
    public MenuItem Men_item_size_12;
    public MenuItem Men_item_size_16;
    public MenuItem Men_item_size_20;
    public MenuItem Men_item_font_cmr;
    public MenuItem Men_item_font_liberation;

    public MenuItem Men_item_aboutEditer;

    public TextArea txt_input;

    MenuItem ItemActivate;

    /*
    button.setgrafich(new ImageView("nom de la imagen"))

    PER cambiar el titol de la finestra :
    desde qualquer funcion :
    Stage stage = (Stage)text.getScene().getText().getWindow();
    stage.setTitle("titolo nuevo")
     */


    public void Close(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void Copy(ActionEvent actionEvent) {

        String contenido = txt_input.getSelectedText();
        if(!contenido.isEmpty())
        {
            txt_input.copy();
        }

    }

    public void Cut(ActionEvent actionEvent) {

        String contenido = txt_input.getSelectedText();
        if(!contenido.isEmpty())
        {
            txt_input.cut();
        }

    }

    public void Paste(ActionEvent actionEvent) {

        txt_input.paste();

    }

    public void Undo(ActionEvent actionEvent) {

        txt_input.undo();
    }

    public void Editar_size(ActionEvent actionEvent) {

        String font = txt_input.getFont().toString();

        ItemActivate = (MenuItem)actionEvent.getSource();

        if(ItemActivate.equals(Men_item_size_12))
        {
            txt_input.setFont(new Font(font, 12));
        }
        if(ItemActivate.equals(Men_item_size_16))
        {
            txt_input.setFont(new Font(font, 16));
        }
        if(ItemActivate.equals(Men_item_size_20))
        {
            txt_input.setFont(new Font(font, 20));
        }

    }

    public void Editar_font(ActionEvent actionEvent) {

        ItemActivate = (MenuItem)actionEvent.getSource();
        double size = txt_input.getFont().getSize();

        if(ItemActivate.equals(Men_item_font_cmr))
        {
            txt_input.setFont(new Font("cmr10", size));
        }

        if(ItemActivate.equals(Men_item_font_liberation))
        {
            txt_input.setFont(new Font("Liberation Sans Narrow", size));
        }
    }


    public void AboutEditer(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("informaciones : ");
        alert.setContentText("informacions generals sobre el programa \n se puede cambiar el tamaño de la finestra");
        alert.setResizable(true);
        alert.showAndWait();
    }

    public String Open(ActionEvent actionEvent) {

        String contenido="";
        String texto="";
        String title= "";

        try
        {

            JFileChooser f=new JFileChooser();
            f.showOpenDialog(this);

            File open =f.getSelectedFile();

            title = open.getName();
            Stage stage = (Stage)txt_input.getScene().getWindow();
            stage.setTitle(title);

            if(open!=null)
            {
                FileReader fr=new FileReader(open);
                BufferedReader br=new BufferedReader(fr);
                while((contenido=br.readLine())!=null)
                {
                    texto += contenido + "\n";
                    txt_input.setText(texto);

                }
                br.close();
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e + "fichero no existe ", "info",JOptionPane.WARNING_MESSAGE);
        }
        return texto;

    }

    public void Save(ActionEvent actionEvent) {


        try
        {
            JFileChooser f =new JFileChooser();
            f.showSaveDialog(this);
            File save =f.getSelectedFile();

            if(save !=null)
            {
                FileWriter  fw =new FileWriter(save);
                fw.write(txt_input.getText());
                fw.close();
            }

        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,
                    "fichero se ha guardado ", "info",JOptionPane.WARNING_MESSAGE);
        }


    }
}
