# CASIER PRO 🔐 

![Logo](https://ik.imagekit.io/pjbsfzv5ci/compresspng/casierpro-min_OC8rWOY60a3W.png?updatedAt=1636279621242)

This smart lock based IoT system will give you a two step authentication process. First thing here is instead keys we will have RFID tags and the second thing is you need to have a mobile app which is designed by us. You should successfully authenticate the app using your fingerprint then if you place the respective RFID tag, the door will be unlocked. These two things will be in



## Authors

- [@Roshan K](https://github.com/Roshanteja21)
- [@Gokul Krishnan](https://github.com/gokulkkr)
- [@Kavita Nampothri](https://github.com/Kavita1013)

## Features

The features that we worked on are concerned with neophytes, so we used RFID - Android Fingerprint series based locker, which can be unlocked with just your fingerprints from your respective phone which has a fingerprint sensor and a RFID tag. We also worked on the things , if the user’s android phone was stolen or not with him , then he can add his account to any of the phones having fingerprint sensor, and he can login with his email and password. Also if the user’s RFID is lost somewhere, then he was requested to add a secret pin, and should answer three questions which had been added before when he login for the first time, then after that he should press a secret button with will be present inside of the locker from inside of the locker door. If that button is not pressed, then the buzzer sounds, and also the user gets mail that someone tried to open the locker. These features make our project unique and most secure. This is not it, but we are going to add more features, and make it much more secure and beginner friendly.



## Tech Stack
**Software** The app was developed in android studio and was supported as a database by firebase. The android app  code was written in Java with XML involved in GUI designing. We have also used two external dependencies named Biometric Prompt which helps in the detection and verification of user’s biometrics. We have initiated the automated mail upon not meeting necessary conditions using JavaMail API and GMAIL’s SMTP server. 
For the above-mentioned feature, 3 libraries namely additional, mail, activation libraries have also been added externally.

**Hardware**From the hardware side , we used NODEMCU which is apparently the best and cheapest option for this project. We had chosen the RC522 module for detecting the RFID tags or the cards. Used a normal tactile switch (push button) as an emergence switch and we used an active 3.3V-5V buzzer module for the alarm purposes. Since the support for Arduino firebase library has ended, there were some web script fingerprint issues and we solved it by using GRC SSL fingerprint website and by editing the raw HTTPClient file of Arduino Firebase library. 


**Server:** Firebase

**Components:** RC522 RFID sensor, Arduino Uno, Buzzer, Pushbutton, Relay + Motor + Door(Servo Motor)

**EDITOR:** Arduino IDE, Firebase, Nodered, Android Studio


## Software Requirements Specification


[Document](https://docs.google.com/document/d/1j5wQLIzuZLvQ9qbQKK_J_F0XSGbNTi-HTr1VL6BV25Q/edit#heading=h.stua369yf65j)

## Project Report
[Document_2](https://docs.google.com/document/d/1g20hfzdE_la1eOiLf8r2TO3naLY6qRrl23q43dJWKGE/edit?usp=sharing)

## Flow Chart
![FLOW CHART](https://user-images.githubusercontent.com/76561059/143824961-5be66252-fae8-4136-8724-792339ac45a0.png)

## Circuit Diagram
![Circuit Diagram](https://user-images.githubusercontent.com/76561059/143825110-17ff7a93-670b-4701-883d-24e9e362317e.png)

## App Layout
![Applayout](https://user-images.githubusercontent.com/76561059/143825312-47b3627e-eecf-4bbc-acbd-ec9c7aea12d7.png)

## Working Video
[Video](https://drive.google.com/file/d/1VN_Dye0wNOsVNOWISeAaJHrYX8rLMunO/view?usp=sharing)
