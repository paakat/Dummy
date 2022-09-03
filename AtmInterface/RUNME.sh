
COMPARING()
{
  nlines=$(diff $1 $2 | wc -l)                    

  if [ "${nlines}" -ne "0" ]; then
    echo "files: '$1' '$2' no match" 
    echo -e "'${FUNCNAME[0]}' ERROR!\nExit.." 
    ##vim -d $1 $2
    ##cp $1 ../Tests  # < be carreful... 
    exit 1
  fi

  return 1
}


TEST01A()
{ 
  echo -e "3\n2\na\n1" > dummy.txt
  echo -e "0" >> dummy.txt
  echo -e "1" >> dummy.txt
  echo -e "exit" >> dummy.txt
 
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt)  > ${FUNCNAME[0]}.txt 
  rm dummy.txt
  
  echo "'${FUNCNAME[0]}' OK!"
}


LOGIN_TEST01()
{ 
  echo -e "2\n" > dummy.txt # signup
  echo -e "1\n new name and last_name\nnewUser\nnewPassWord\n" >> dummy.txt # create | name | user | pass 
  echo -e "1\nnewUser\nnewPassWord\n" >> dummy.txt # enter | user | pass | atm
  echo -e "1\n" >> dummy.txt # balance   
  echo -e "exit" >> dummy.txt

  #${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) 
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt)  > ${FUNCNAME[0]}.txt  
  rm dummy.txt

  COMPARING newuser.ser ../Tests/${FUNCNAME[0]}.ser 
  echo "'${FUNCNAME[0]}' OK!"
}


ATM_TEST01()
{ 
  echo -e "2\n" > dummy.txt # signup
  echo -e "1\nname B\nuserB\npassWordB\n" >> dummy.txt # create | name | user | pass 
  echo -e "1\nuserB\npassWordB\n\n3\n10.123\n\n2\n0.123\n1\n" >> dummy.txt # enter | user | pass | atm || deposit | withdraw | balance
  echo -e "4\nnewUser\n3.1" >> dummy.txt # transfer 

  #${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) 
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt)  > ${FUNCNAME[0]}.txt 
  rm dummy.txt

  COMPARING userb.ser ../Tests/${FUNCNAME[0]}.ser 
  echo "'${FUNCNAME[0]}' OK!"
}


ATM_TEST02()
{ 
  echo -e "0\n1\n1\nuserB\npassWordB\n1" > dummy.txt # 6.9  
  echo -e "0\n1\n1\nnewUser\nnewPassWord\n1" >> dummy.txt # 3.1  
  echo -e "exit" >> dummy.txt

  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt)  > ${FUNCNAME[0]}.txt  
  rm dummy.txt

  COMPARING newuser.ser ../Tests/${FUNCNAME[0]}.ser
  echo "'${FUNCNAME[0]}' OK!"
}


UBUNTU_JAMMY()
{
  if [[ $OSTYPE == 'linux-gnu'* ]]; then
    JAVA_PATH= 
  fi
 
  JAVA=${JAVA_PATH}"java"
  JAVAC=${JAVA_PATH}"javac -Xlint"
  JAR=${JAVA_PATH}"jar"
  echo "JAVA:'${JAVA}'"
  
  ${JAVA} -version
}


REMOVE_CLASS_FILES() 
{
  find . -name "*.class" -type f  -delete
  find . -name "*.class" -type f
}


TESTS()
{
  TEST01A
  LOGIN_TEST01
  ATM_TEST01
  ATM_TEST02
}


INTERACTIVE()
{
  if [ $# -ne 0 ] 
  then
    echo -e "2\n1\nnamea\na\na\nexit" > dummy.txt # user:'a'; pass:'a' 
   
    ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt)  > ${FUNCNAME[0]}.txt
    rm dummy.txt

    ${JAVA} -jar ${EXEC}  
  fi
  echo "'${FUNCNAME[0]}' OK!"
}


JAVA_RUN() 
{
  ## 1. setup 
  rm -rf RUNME
  cp -r src/main/java RUNME
  cp -r Tests RUNME
  cd RUNME 

  ## 2. compilation 
  rm -rf ./Build
  ${JAVAC} -d ./Build Main.java

  cd ./Build
  echo -e "Manifest-Version: 1.0\nMain-Class: Main" > manifest.mf
  ${JAR} cmf ./manifest.mf ${EXEC} *

  ## 3. execution
  TESTS
  INTERACTIVE $1  

  rm manifest.mf
  REMOVE_CLASS_FILES
}


###
clear 

EXEC="Atm.jar"
##JAVA_PATH="YOUR/JAVA/PATH/HERE"
JAVA_PATH="/Users/poderozita/z2022_2/REPOSITORY/NOVAREPOTENTIAL/jdk-17.0.2.jdk/Contents/Home/bin/"

UBUNTU_JAMMY
JAVA_RUN $1
 
