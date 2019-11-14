# Other Project 003

A simple Tic Tac Toe application for Android devices.
This repo purpose is for learning and practice only.

## :smile: **Getting Started/Brief Description**

This is a game of Tic Tac Toe with a 3\*3 playing field. Player 1 go first.\
If anyone got 3 markers in a row, column or 2 cross lines, that player wins. A draw can happen.\
Press the RESET button to erase all data of the game (player's score, the state of the button X/O, ...)\
All data are kept when orientation changed (e.g portrait -> landscape)

## :computer: **Prerequisites/Built With/Technologies used**

- Android Studio version 3.5
- Java

## :page_facing_up: **Installing/Running**

- clone this repo
- run it using Android Studio

## :car: **Deployment**

**This was intentionally left blank.**

## :memo: **Notes**

All buttons are named like this: `button_xy` to access it ez when calling it from a loop.

_What is View_

- View is like elements in HTML where components like buttons, text inputs are view

_What does this line mean `buttons[i][j].setOnClickListener(this);`_

- calling the class itself (here is MainActivity)
- we saw `MainActivity implements View.OnClickListener` -> this class now has a met called `onClick(View v){}`
- -> we write logic code inside that new override method inherited from super class

_How to save data when change orientation_

- in `activity_main.xml`, have this line `android:freezesText="true"` in each element that wanna keep the text unchanged
- save data using this func `protected void onSaveInstanceState(Bundle outState){ // code in here}`
- take data using this func `protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){ // code in here}`
  - use appropriate mets for data type (e.g `putInt` and `getInt`; `putBoolean` and `getBoolean`)

## :bell: **Contributing**

**This was intentionally left blank.**

## :speech_balloon: **Authors**

Check Credits

## :grey_exclamation: **License**

**This was intentionally left blank.**

## :email: **Credits/Acknowledgments/References**

This exercise was done using [this tutorial](https://www.youtube.com/watch?v=apDL78MFR3o)
