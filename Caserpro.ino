#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <SPI.h>
#include <MFRC522.h>
#include <Servo.h>
#define SS_PIN D4
#define RST_PIN D3
#define SERVO_PIN D1 //relay pin
#define FIREBASE_HOST "Your-project-name.firebaseio.com"           // Your Firebase Project URL 
#define FIREBASE_AUTH "YourSecretCode"         // Your Firebase Database Secret
#define WIFI_SSID "SSID"           // your WiFi SSID
#define WIFI_PASSWORD "PASSWORD"             // your WiFi
float val;
MFRC522 mfrc522(SS_PIN, RST_PIN);
Servo myservo;

void setup() 
{
  Serial.begin(9600); 
  SPI.begin();        
  mfrc522.PCD_Init();
  myservo.attach(SERVO_PIN);
  myservo.write( 70 );
  delay(7500);
  myservo.write( 0 );
  WiFi.begin(WIFI_SSID,WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status()!=WL_CONNECTED)
  {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected:");
  Serial.println(WiFi.localIP());
 
  Firebase.begin(FIREBASE_HOST);
  Firebase.setInt("number",1);
  
  Serial.println("Put your card to the reader...");
  Serial.println();
}

void firebasereconnect()
{
  Serial.println("Trying to reconnect");
  Firebase.begin(FIREBASE_HOST);
  }

void loop() 
{
  if (Firebase.failed())
      {
      Serial.print("setting number failed:");
      Serial.println(Firebase.error());
      firebasereconnect();
      return;
      }
  val=Firebase.getFloat("number");        //Reading the Status of Variable 1 from the firebase
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) 
  {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) 
  {
    return;
  }
  //Show UID on serial monitor
  Serial.print("UID tag :");
  String content= "";
  byte letter;
  for (byte i = 0; i < mfrc522.uid.size; i++) 
  {
     Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
     Serial.print(mfrc522.uid.uidByte[i], HEX);
     content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
     content.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.println();
  Serial.print("Message : ");
  content.toUpperCase();
  if ((content.substring(1) == "F6 0B 01 24") && (val==1)) //change here the UID of the card
  {
    Serial.println("Authorized access");
    Serial.println(val);
    Serial.println();
    myservo.write(70);
    delay(7500);
    myservo.write(0);
    Firebase.setInt("number",0);
 }
 
 else   {
    Serial.println(" Access denied");
    delay(1000);
    Serial.println(val);
  }
}
