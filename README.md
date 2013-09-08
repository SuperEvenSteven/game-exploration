game-exploration
================
Demonstration sprite based games written in java using swing.

Run game either as an applet or via a main class.

Getting started
-------------------
To run as a main class:

    git clone https://github.com/SuperEvenSteven/game-exploration.git
    cd game-exploration
    mvn test exec:java
    
About
---------------------------
The demonstration game runs a game loop in a different thread from the Swing Event Dispatcher thread (where swing drawing is performed). Wait/notify is used to calculate the redraw time to make the Frames Per Second measure as constant as possible.

<img src="https://raw.github.com/davidmoten/game-exploration/master/docs/screensnap.png"/>

A standalone example in one class that animates a string across the screen of a JFrame is in the [MyGame](https://github.com/davidmoten/game-exploration/blob/master/src/main/java/com/widgets/big/game/demo/MyGame.java) class.

