
# ATM interface 



This project you emulates the basic functionality of an actual ATM. 

To use the system users need to be authenticated before being allowed into the system. 

The credentials consist of a password and a unique username. 

After entering the correct credentials, users can see their bank details like account balance and perform actions such as transfer, withdraw, deposit and quit. 

![example workflow](https://github.com/spicytechs/NovarePotential/actions/workflows/workflowc.yml/badge.svg)


[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=paakat_Dummy&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=paakat_Dummy)


# 1. Mandatory requirements

### Functionalities

- [x] The home menu will initially offer users two options: 
1. - [x] login , and 
2. - [x] signup.

- [x] The user is prompted to **login** with : 
1. - [x] username, and 
2. - [x] password.

- [x] When **signing up**, a user has to enter : 
1. - [x] full name
2. - [x] username and 
3. - [x] password.

- [x] Once the user is correctly identified in the system, ATM functionalities are unlocked

- [x] ATM functionality must include : 
1. - [x] view balance, 
2. - [x] withdraw money, 
3. - [x] deposit, 
4. - [x] transfer money and 
5. - [x] Logout.  


- [] ??The user interface must display possible options in each stage of the application.


- [] *full name* is unchangeable 


- [] *username* and *password* can change



### Implementation details  


- [] Create different classes for different functionality (i.e. User.java, Account. Java, Transaction.java, etc)

- [x] Code must include data security 

- [x] Code must include reusability features of object oriented concepts.

- [x] Project must verify the authentication code for the user.

- [x] You can use the MessageDigest package to store the password


# 2. User histories and tasks 

## Summary 

```mermaid
stateDiagram-v2    
direction LR

%% history 2

h1 : AS A banker I WANT TO have the greatest possible security SO THAT my clients feel safe when using my bank
h1a : AS A banker I WANT TO know the full name of my users SO THAT i can verify their identity
h1b : AS a banker I WANT TO a unique username for each client SO THAT I can identify each of them 
h1c : As a banker I WANT TO each client choose a password SO THAT I can verify the veracity his/her identity


Login --> h1
h1 --> h1a 
h1 --> h1b
h1 --> h1c

```

```mermaid
stateDiagram-v2    
direction LR

SU --> h2 

SU : SingingUp

h2 : As a user I want to be able to create a password so that I am sure that I am the only one who can access my account.

%%h2 : AS A user I WANT TO have the greatest possible security SO THAT my clients feel safe when using my bank
%%h2a : AS A banker I WANT TO know the full name of my users SO THAT i can verify their identity
%%h2b : AS a banker I WANT TO my users to choose a username SO THAT they can quickly access their bank account
%%h2c : As a banker I WANT TO my users to choose a password SO THAT they are sure that their bank account is protected.

```

```mermaid
stateDiagram-v2    
direction LR

B : view balance
W : withdraw money
D : deposit
T : transfer money

h3 : As a user I want to see my account statement so that I can be sure that everything is correct.
h4 : As a user I want to make money transfers so that I can pay my bills
h5 : As a user I want to be able to deposit money to my account so that I can be sure I have enough money.
h6 : As a user I want to make money transfers to other bank accounts so that I can easily pay my clients

B --> h3 
W --> h4 
D --> h5 
T --> h6 
```

## [Jira](https://jmake2.atlassian.net/issues/?jql=created+%3E%3D+-30d+AND+project+%3D+ATM+order+by+created+DESC&atlOrigin=eyJpIjoiMTgyYmQzMDA1NDMyNDZlMjk0NGQ1NGIxY2QxOTRkMGYiLCJwIjoiaiJ9)


![alt text](../Inputs/jiraDashBoardATM01a.png "Dash Board")



# 3. Diagrams 

### State 

```mermaid
stateDiagram-v2
direction LR

SU : SingingUp
ATM : ATM functionality 

B : view balance
W : withdraw money
D : deposit
T : transfer money
L : Logout

[*] --> Home 

Home --> Login
Home --> SU 

SU --> Login
Login --> ATM 

ATM --> B 
ATM --> W 
ATM --> D 
ATM --> T 
ATM --> L 
            

```

### Class 


_Atm_

```mermaid
classDiagram
direction LR


ATM --* Scheduler

Scheduler --o MenuManager : depends 
Scheduler --o MenuLogin : depends 
Scheduler --o MenuSign : depends 
Scheduler --o MenuAtm : depends 

MenuLogin --o AccountFind : depends
MenuLogin --o AccountCurrentUser 

MenuSign --o AccountCreate 

MenuAtm --o LMapStringString  
MenuAtm --o AccountCurrentUser
MenuAtm --o Serializing~T~


AccountFind --o HashValues 
AccountFind --o FilesTools 
AccountFind --o DeSerializing~T~
AccountFind --o LMapStringLMapStringString 
AccountFind --o LMapStringString 


AccountCreate --o LMapStringString
AccountCreate --o HashValues
AccountCreate --o Serializing~T~ 


LMapStringLMapStringString -- LMapStringString
LMapStringLMapStringString -- LinkedHashMapExtented~k,v~

LMapStringString -- LinkedHashMapExtented~k,v~

MenuManager --|>Controller: extends 
MenuLogin --|>Controller: extends 
MenuSign --|>Controller: extends 
MenuAtm --|>Controller: extends 

class LMapStringLMapStringString{
+Put(String,StLMapStringStringring)
+Get(String) LMapStringString
+Exist(String) boolean
}


class LMapStringString{
+Put(String,String)
+Get(String)String
+Exist(String) boolean
}


class AccountCurrentUser{
+account : LMapStringString
}

class Serializing~T~{
+Serializing(T,String) 
}

class DeSerializing~T~{
+DeSerializing(String) 
+ShowObj()
+GetObj() T
}

class FilesTools{
+GetFilesName() LMapIntegerString
+OpenFile(String, String) boolean
+CouldBeOpen(String) boolean
}


class HashValues{
+Create(String) String
}

class LinkedHashMapExtented~k,v~{
#name : String 
+Put(k,v)
+Get(k) v
+Exist(k) boolean
}

```


_Scheduler_

```mermaid
classDiagram
direction LR


Scheduler --* LMapStringController : depends 
Scheduler --* RecursiveScanner : depends 


LMapStringController --|> LinkedHashMapExtented~k,v~ : extends 
LMapStringController --*Controller: depends 


Controller--o RecursiveScanner : depends 
Controller--* LMapIntegerString : depends 

RecursiveScanner --* LMapIntegerString : depends 
LMapIntegerString --|> LinkedHashMapExtented~k,v~ : depends 

LinkedHashMapExtented~k,v~ ..|> Serializable : depends 
LinkedHashMapExtented~k,v~ --|> LinkedHashMap~k,v~ : extends 


class Scheduler{
+runner : Controller
+runners : LMapStringController 
+rScanner : RecursiveScanner
+CreateMenus()
+CreateOptions()
+ExecutingRecursively()
}

class Serializable {
<<interface>>
}

class LinkedHashMap~k,v~{
+put(k,v)*
+get(k)* v
}

class LMapIntegerString{
+Put(Integer,String)
+Get(Integer)String
+Exist(Integer) boolean
}



class LinkedHashMapExtented~k,v~{
#name : String 
+Put(k,v)
+Get(k) v
+Exist(k) boolean
}


class Controller{
-name : String 
-chosen : String 
-options :LMapIntegerString
-rscanner : RecursiveScanner
+SetName(String)
+CreateOptions()* LMapIntegerString
+Recursive(RecursiveScanner)*
+ChosenOptionFound(String, RecursiveScanner)* String
+SetData(String, RecursiveScanner) String
+BottomMessage(RecursiveScanner) 
}


class LMapStringController{
+Put(String,Controller)
+Get(String) Controller
}

class RecursiveScanner{
+SetOptions(LMapIntegerString)
+GetChosen() String
+Execute() String
+GetNexLine() String
}

```


```mermaid
stateDiagram-v2
direction LR 


```

# 4. [Code quality](https://docs.sonarcloud.io/improving/overview/) 

See code smells' long-term risks. The closer a bubble's color is to red, the higher the ratio of technical debt is. Bubble size indicates code smell volume, and each bubble's vertical position reflects the estimated time to address the code smells. Small green bubbles on the bottom edge are best.

![alt text](../Inputs/sonarCloudATM01b.png)

![alt text](../Inputs/sonarCloudATM01a.png)



