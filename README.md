# Junye-Assignment-3
CS3354 – Fall 2019 – Assignment 3 
Due date: Wednesday, Nov. 11 at 11:55 p.m. 
Project Title: Sentiment Analysis (stage 3) 

Goal: The goal of this assignment is to help students understand the concepts of Java GUI development as well as Java Concurrency and Multi-threaded programming   


Description: 
This assignment extends Assignment 2, by replacing console-based user interface with a graphical user interface (GUI). Also, we will modify the program created in Assignment 2 to improve the execution efficiency by introducing multi-threading.

 All the operations remain the same as in assignment 2, but this time the user input/output will be handled through a Swing-based Java GUI. The decision regarding which Swing components should be used to achieve the required functionality is left to the students. 

The students may use the solution of assignment 2 provided by the instructor, but they are free to modify the given code to fit their needs.


Tasks: 
There are two major tasks of this assignment:

GUI: 
In the provided solution, the class SentimentAnalysisApp acts as the main user interface and the class ReviewHandler acts as the main controller class of the program. After adding the GUI, all the user input/output will be handled by the GUI. Hence, the SentimentAnalysisApp class will have to be re-written from scratch, whereas, the ReviewHandler class will not require many changes. 

To visualize a set of movie reviews, use the JTable swing component. With the JTable class you can display tables of data, optionally allowing the user to edit the data. The rest of the components that you use to achieve the required functionality is left at your discretion.

Multi-threading: 

Multithreading should be used in every call of a back-end method. The front-end (GUI) and the back-end should always run on separate threads. To be more specifically, methods in ReviewHandler and AbstractReviewHandler should be run in a new thread. You can get more hints in “Details” section.

Details and hints: 
1. No input or output should be given through console anymore. You should assume that the user can only see and interact with your Java Swing window. (Don’t use System.in and System.out any more).

2. Although you are encouraged to try different Java Swing components, it is OK if your GUI is very simple. Don’t worry about whether it is beautiful or not – concentrate on the functional side first. You won’t lose any score just because I don’t like your design.

3. Remember you should use JTable to show the search results.

4. For multi-threading, the thing you need to do is to create and initialize a runnable/thread inside your ActionListener methods of JButton components. In other words, it should be implemented in SentimentAnalysisApp class, not ReviewHandler.

5. If you are using the provided solution, you don’t need to change anything in AbstractReviewHandler, MovieReview or ReviewHandler except replacing all System.out.println() to output to your GUI.


Logistics: 
•	You may use an IDE (BlueJ, Netbeans, etc) or just an editor and command line operations (javac, java) in Unix or Windows/DOS to develop your program. 
•	Use good design (don’t put everything in one class). 
•	Use a package for your classes and put your files in the appropriate directory structure. 
•	You don't need to create any GUI for this assignment. Command line operations are enough. 
•	Use a standard Java coding style to improve your program’s visual appearance and make it more readable. I suggest the BlueJ coding style: http://www.bluej.org/objectsfirst/styleguide.html 
