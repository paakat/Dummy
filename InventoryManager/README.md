## A. [Jira](https://jmake2.atlassian.net/jira/software/projects/WAR/boards/6/roadmap?shared=&atlOrigin=eyJpIjoiOTlhODdmM2NhZTU2NDIzZjlhM2JlZTQ1ZmE3MmFhNmQiLCJwIjoiaiJ9)


![alt text](../Inputs/jiraInventoryManager01a.png "Dash Board")



# B. Menu Sequences 


## Employee - Inventory 

```mermaid  
sequenceDiagram
%%autonumber

actor Employee 
participant Home

Employee ->> Home : login 

rect rgba(255,0,0,0.2)

Home ->>+ Inventory : Accessing inventory 

rect rgba(0,255,0,0.2)
    opt list   
        Note left of Inventory : Getting product information
        Inventory ->> Employee : Displaying inventory 
    end 
end

rect rgba(0,255,0,0.2)
    opt sell    
        Employee ->> Inventory : Product name 
        alt name exists 
            Employee ->> Inventory : Product id 
            alt id exists 
                alt there are at least two
                Note left of Inventory : Removing product
                end 
            end 
        end 
    end   
end 

rect rgba(0,255,0,0.2)
    opt return     
        Employee ->> Inventory : Product id 
        alt id exists 
            Note left of Inventory : Refresing products information
        end 
    end   
end 

Inventory ->>- Home : Closing inventory

end 
```


## Administrator - Product 

```mermaid
sequenceDiagram
%%autonumber

actor Administrator 
participant Home


Administrator ->> Home : login 


rect rgba(255,0,0,0.2)
  opt productList  
  Inventory ->> Product : Getting products properties
  Product ->> Administrator : Displaying product identifiers 

    rect rgba(0,255,0,0.2)
        Administrator ->> Product : Choosing an identifier

        opt display    
            Product ->> Administrator : Displaying product properties 
        end 
        opt price    
            Administrator ->> Product  : Changing selling price
        end 
        opt cost    
            Administrator ->> Product  : Changing purchase price
        end 
    end   
  end 

  Product ->> Inventory : Recording changes
  Inventory ->> Administrator : Displaying inventory
  
end 


rect rgba(0,255,0,0.2)
  opt productAdd  

    rect rgba(0,0,255,0.2)
        opt create    
            Administrator ->> Product  : Product identifier
            Administrator ->> Product  : Product name 
            Administrator ->> Product  : Purchase price
            Administrator ->> Product  : Selling price
        end 
    end   
    
    Product   ->> Inventory     : Adding product properties
    Inventory ->> Administrator : Displaying inventory
    end 
  
end 

rect rgba(0,0,255,0.2)
  opt productRemove  
  Inventory ->> Product : Getting products properties
  Product ->> Administrator : Displaying product identifiers 

    rect rgba(255,0,0,0.2)
        Administrator ->> Product : Choosing an identifier
        opt remove    
            Product ->> Inventory : Removing product 
            
        end 
    end   
  end 

  Product ->> Inventory : Recording changes
  Inventory ->> Administrator : Displaying inventory
  
end 

```




# C. States 


```mermaid
stateDiagram-v2


[*] --> Home 

Home --> Cashier
Home --> Manager
Home --> Administrator
```


```mermaid
stateDiagram-v2
direction LR

Cashier --> CashierID 

CashierID --> Product
CashierID --> Statistics
CashierID --> Inventory


%%%%%%%%%%%%%%%%%%%%%%%%%%%
Statistics --> amounts

%%%%%%%%%%%%%%%%%%%%%%%%%%%
Inventory --> list
Inventory --> sell
Inventory --> return 


%%%%%%%%%%%%%%%%%%%%%%%%%%%
Product --> productList

productList --> productID 

productID --> display 
productID --> price
productID --> cost 
```



```mermaid
stateDiagram-v2
direction LR

%%%%%%%%%%%%%%%%%%%%%%%%%%%
Manager --> ManagerID 

ManagerID --> Product
ManagerID --> Statistics
ManagerID --> Inventory


%%%%%%%%%%%%%%%%%%%%%%%%%%%
Statistics --> amounts

%%%%%%%%%%%%%%%%%%%%%%%%%%%
Inventory --> list
Inventory --> sell
Inventory --> return 


%%%%%%%%%%%%%%%%%%%%%%%%%%%
productCode2 : productID
productCode3 : productID


Product --> productList
Product --> productAdd
Product --> productRemove

productAdd --> create 
create --> productCode3

productList --> productID 

productID --> display 
productID --> price
productID --> cost 

productRemove --> productCode2 
productCode2 --> remove

```



```mermaid
stateDiagram-v2
direction LR


%%%%%%%%%%%%%%%%%%%%%%%%%%%
Administrator --> Home
Administrator --> EmployeeList
Administrator --> EmployeeAdd
Administrator --> Product
Administrator --> Statistics
Administrator --> Inventory

%%%%%%%%%%%%%%%%%%%%%%%%%%%
Statistics --> amounts

%%%%%%%%%%%%%%%%%%%%%%%%%%%
Inventory --> list
Inventory --> sell
Inventory --> return 


%%%%%%%%%%%%%%%%%%%%%%%%%%%
productCode2 : productID
productCode3 : productID


Product --> productList
Product --> productAdd
Product --> productRemove

productAdd --> create 
create --> productCode3

productList --> productID 

productID --> display 
productID --> price
productID --> cost 

productRemove --> productCode2 
productCode2 --> remove

```


```mermaid
stateDiagram-v2
direction LR

item --> id  
item --> name
item --> price 
item --> cost 
```


# D. Classes 


## Main 

```mermaid
classDiagram

direction LR


Main --> Scheduler 

Scheduler --> Menus
Scheduler --> Controller 
Scheduler --> RecursiveScanner
Scheduler --> LMapStringRunnable


Menus --> ProductRunners
Menus --> EmployeeRunners
Menus --> InventoryRunners
Menus --> StatisticsRunners
Menus --> AdministratorRunners
Menus --> Controller

```


## Administrator 

```mermaid
classDiagram

direction LR


%% Menus --> AdministratorRunners
AdministratorRunners --> Administrator

Administrator --> Controller
Administrator --> RecursiveScanner
Administrator --> LMapIntegerString


Administrator --> AdministratorOperations
Administrator --> AdministratorController
Administrator --> RecursiveScanner


AdministratorController --> InventoryModel
AdministratorController --> LMapStringString

AdministratorOperations --> AdministratorController 


InventoryModel --> Model 

Model --> LMapStringLMapStringString
Model --> LMapStringString

Model --> Serializing
Model --> DeSerializing
Model --> FilesTools

```

## Inventory 

```mermaid
classDiagram

direction LR


%% Menus --> InventoryRunners
InventoryRunners --> Inventory 

Inventory --> InventoryController 
Inventory --> InventoryOperations
Inventory --> LMapIntegerString 
Inventory --> RecursiveScanner
Inventory --> Controller


InventoryController --> LMapStringString
InventoryController --> StatisticsModel
InventoryController --> InventoryModel
InventoryController --> ItemModel


InventoryOperations --> RecursiveScanner
InventoryOperations --> LMapStringString


InventoryModel --> Model 

Model --> LMapStringLMapStringString
Model --> LMapStringString

Model --> Serializing
Model --> DeSerializing
Model --> FilesTools

```


## Employee 

```mermaid
classDiagram



%% Menus --> EmployeeRunners

EmployeeRunners --> Employee
EmployeeRunners --> EmployeeMenus


Employee -->  LMapIntegerString
Employee -->  LMapStringString
Employee -->  RecursiveScanner
Employee -->  Generic

Employee -->  Names
Employee -->  Controller

Employee -->  AdministratorController


EmployeeMenus --> Controller
EmployeeMenus --> LMapIntegerString
EmployeeMenus --> RecursiveScanner

```





## Product

```mermaid
classDiagram



%% Menus --> ProductRunners


ProductRunners --> LMapIntegerString
ProductRunners --> Product
ProductRunners --> ProductAdd
ProductRunners --> ProductList
ProductRunners --> ProductRemove
ProductRunners --> ProductModify


Product --> LMapIntegerString 
Product --> RecursiveScanner
Product --> InventoryController
Product --|> Controller 


ProductAdd --> Menus 
ProductAdd --> LMapIntegerString 
ProductAdd --> RecursiveScanner
ProductAdd --> InventoryController
ProductAdd --|> Controller 


ProductList --> InventoryModel
ProductList --> ItemFind
ProductList --> Menus 
ProductList --> LMapIntegerString 
ProductList --> RecursiveScanner
ProductList --> InventoryController
ProductList --|> Controller


ProductRemove --> ItemFind
ProductRemove --> LMapIntegerString 
ProductRemove --> RecursiveScanner
ProductRemove --> InventoryController
ProductRemove --|> Controller


ProductModify --> ItemController
ProductModify --> InventoryController
ProductModify --> LMapIntegerString 
ProductModify --> RecursiveScanner
ProductModify --> InventoryController
ProductModify --|> Controller 
```



## Details 

```mermaid
classDiagram

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class InventoryController {
+ create(String, String, String, String)
+ setAmounts(String, Double)
+ setAvailability(String, String)
+ setAvailability(String, String, Stringame)
+ setRegistration(String, String, String, String)
+ get(String) LMapStringString
+ getParent(String) String
+ exist(String) boolean
+ remove(String)
+ back(String)
+ view()
}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class Model {
- obj : LMapStringLMapStringString
+ Model(String)
+ exist(String) 
+ save(String) 
+ get(String) LMapStringLMapStringString
+ get() LMapStringLMapStringString
+ view()
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class AdministratorController {
+ view() 
+ exist(String) boolean
+ getParent(String) String
+ get(String) LMapStringString
+ setRegistration(String, String, String, String)
}




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class Scheduler {
+ rScanner : RecursiveScanner
+ runners  : LMapStringRunnable
+ menus : Menus
+ runner: Controller 
}



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class InventoryOperations {
+ selling(RecursiveScanner) 
+ returning(RecursiveScanner)
+ SetData(String, RecursiveScanner)
}


```



```mermaid
classDiagram

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class InventoryModel {
+ save()
+ getParent(String)
+ Add(String, String, String)
}



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class ProductRunners {
+ runners : LMapStringRunnable 
+ find() LMapIntegerString 
}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class ProductRemove {
+ find() LMapIntegerString
+ remove() String 
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class Employee {
+  login(String, RecursiveScanner) String
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class AdministratorOperations {
+ creating(RecursiveScanner) String
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class ProductModify {
+ modify(String, ItemController, RecursiveScanner)
}


```

##  Scheduler

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
