# RPAL-interpreter
An interpreter developed for the RPAL language.
This Project was implemented using java (1.8) on Windows OS. It has a class-based structure and the algorithm can be categorized into four parts.

## Structure

#### Reading the input from given text file
Reading the file input is handled by the ‘ReadWriteHandler’ class with is implemented with the singleton pattern. Main method passes the file name into the ‘ReadWriteHandler’ and it returns an Arraylist of lines in the input text file. CountDots method creates an Arraylist of string arrays containing pairs of node name and the level of the node (number of dots) using those lines.
#### Building the AST Structure
This process happens inside the main class. Created Arraylist from the previous step is looped through the createTree method to instantiate separate _Node objects for all lines according to their node names. Every _Node has a parent _Node and all _TrunkNodes does have an Arraylist of children.Structure of the nodes are shown in the picture below.

<img src="https://github.com/chamikaCN/RPAL-interpreter/blob/master/RPAL%20class%20diagram.png" alt="Image Target]" height="650px">  

Root node is applied to identifie the root of the tree after standardization and flattening. The nodes in the squares are all abstract and the lists connected to each of them are the classes which extends that abstract class. Standardizable interface is only implemented by classes (shown with # on the diagram) which need to be standardized before the CSE machine.
#### Standardization
Standardization process happens as a continuous loop until no nodes are standardized in an iteration. Standardizing mechanism is defined inside each standardizable node. If a node is standardized a flag will be set inside the object to prevent restandardization in next iteration.After the standardization nodes will be stored in the the STtree array in the main class.
#### CSE machine
CSE machine is implemented in the CSEmachine class. It takes the Structured tree as the input and flatten the tree into several delta queries.Then the environment is created and root delta is pushed to the control stack. Process continues until the Control stack is empty.

## User Guide

The compiled executable jar file of the interpreter can be downloaded from [this link](https://drive.google.com/file/d/1pictHnjrnRrE_WoFkVsmcRoOzAQChMyG/view?usp=sharing).
It can be run with 
`java -jar myrpal.jar <input filename>`.
Input files should have the Abstract Syntax Tree in [this format](https://drive.google.com/file/d/1HyYsgZSzesI4MevqeHNRYiyYw_283Cu3/view?usp=sharing).
