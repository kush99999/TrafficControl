/*
  oneRed "OR"
  oneYellow "OY"
  oneGreen "OG" 
  twoRed "TR"
  twoYellow "TY"
  twoGreen "TG"
  stop "TF"
  turnAuto "TA"
*/

#define T1_Red 2
#define T1_Yellow 3
#define T1_Green 4

#define T2_Red 5
#define T2_Yellow 6
#define T2_Green 7

#define RGTime  5
#define YTime 2

String Command;

boolean AutoMode = HIGH;
byte State = 1;
byte Counter = 0;
long LastTic;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(38400);
  pinMode(T1_Red,OUTPUT);
  pinMode(T1_Yellow,OUTPUT);
  pinMode(T1_Green,OUTPUT);
  pinMode(T2_Red,OUTPUT);
  pinMode(T2_Yellow,OUTPUT);
  pinMode(T2_Green,OUTPUT);

  LastTic = millis();
  stopAll();
}

void loop() {

  if(AutoMode){
    if(millis()-LastTic >= 1000){
      autoMode();
      LastTic = millis();
    }
  }
  
  if(Serial.available()){
    Command = Serial.readString();
    handleCommand();
  }
}

void handleCommand(){
  
  stopAll();
  AutoMode = LOW;
  
  if(Command == "OR"){
    turnOnPin(T1_Red);
  }else if(Command == "OY"){
    turnOnPin(T1_Yellow);
  }else if(Command == "OG"){
    turnOnPin(T1_Green);
  }else if(Command == "TR"){
    turnOnPin(T2_Red);
  }else if(Command == "TY"){
    turnOnPin(T2_Yellow);
  }else if(Command == "TG"){
    turnOnPin(T2_Green);
  }else if(Command == "TF"){
    stopAll();
  }else if(Command == "TA"){
    AutoMode = HIGH;
    LastTic = millis();
    State = 1;
    Counter = 0;
    autoMode();
  }else{
    Serial.println("Error");
  }
}

void stopAll(){
  stopOne();
  stopTwo();
}

void stopOne(){
  digitalWrite(T1_Red,LOW);
  digitalWrite(T1_Yellow,LOW);
  digitalWrite(T1_Green,LOW);
}

void stopTwo(){
  digitalWrite(T2_Red,LOW);
  digitalWrite(T2_Yellow,LOW);
  digitalWrite(T2_Green,LOW);
}

void turnOnPin(int pinNumber){
  digitalWrite(pinNumber, HIGH);
  //Serial.println(pinNumber);
}

void autoMode(){
  Counter++;
  if(State==1){ //1R 2G
    stopAll();
    turnOnPin(T1_Red);
    turnOnPin(T2_Green);
    if(Counter >= RGTime){
      State ++;
      Counter = 0;
    }
  }else if(State==2){
    stopAll();
    turnOnPin(T1_Red);
    turnOnPin(T2_Green);
    turnOnPin(T2_Yellow);
    if(Counter >= YTime){
      State ++;
      Counter = 0;
    }
  }else if(State==3){
    stopAll();
    turnOnPin(T1_Green);
    turnOnPin(T2_Red);
    if(Counter >= RGTime){
      State ++;
      Counter = 0;
    }
  }else if(State==4){
    stopAll();
    turnOnPin(T1_Green);
    turnOnPin(T1_Yellow);
    turnOnPin(T2_Red);
    if(Counter >= YTime){
      State ++;
      Counter = 0;
    }
  }
  if(State==5)
    State = 1;
}

