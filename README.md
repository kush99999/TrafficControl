# TrafficControl


Traffic Light control using Android-Arduino Bluetooth 


Traffic Control using Arduino Bluetooth and Android

OBJECTIVES

•	Learn Internet of Things (IOT) concept.

•	Create Android Mobile Applications.

•	Control the hardware using mobile apps via Bluetooth Module.  


EQUIPMENTS REQUIRED

1.	HARDWARE

•	PC or lab with windows 7, 8...

•	Arduino UNO kit.

•	Red, yellow and green LEDs.

•	Resistors 300Ω (3x).

•	Jumpers

•	Bluetooth Module (HC-06/other).

•	Android Smartphone with Bluetooth.

•	USB cable for the Arduino.


2.	SOFTWARE

•	Android studio.

•	Java Development kit (JDK)

•	software development kit (SDK)

•	SPP Bluetooth Apk (unWired Lite)

•	C programming skills



Introduction

Arduino is all about electronics connecting things together, but it lacks much in the way of a user interface and wireless connectivity. Android, on the other hand, has great user interface capabilities but no means of directly connecting to electronics.
At Google’s developer conference (Google IO 2011), the Open Accessory standard was announced. This is the standard for creating hardware accessories for Android devices that plug into the Android device through its USB connection. This is available for cell phones and tablets with Android 2.3.4 or later.
Nowadays a new emerging technology is Internet of Things (IoT): this tutorial describes the Internet of things concept with Android and Arduino. In IoT project, all the physical objects (things) are connected together using internet infrastructure.
Part 1: The hardware implementation:
HC-06 and Arduino 


Bluetooth is a type of wireless communication used to transmit voice and data at high
speeds using waves of radio. It’s widely used in mobile phones for making calls, headset and share data. This type of communication is a cheap and easy way to control something remotely using Arduino. 
HC-06 module has 4 pins to be connected to Arduino, they are:

•	RXD

•	TXD

•	VCC

•	GND

RXD will receive data from arduino; TXD will send data to arduino;

VCC is the power supply (3.3V 6.6V) and GND is the ground.
