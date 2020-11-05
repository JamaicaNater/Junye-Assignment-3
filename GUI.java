package movieReviewClassification;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.awt.*;
import javax.swing.*;

/**
  Graphical User Interface for Assignment 3 
  @author Michael Paulson
  @author Francis Williams
 */
public class GUI
  { /**
  * GUI controls I/O with user and method calls
  * @param args command line arguments
  */
    public static void main(String[] args) 
    {
        BuildGUI gui = new BuildGUI();  
        ReviewHandler reviewHandler = new ReviewHandler();
    }
  }

