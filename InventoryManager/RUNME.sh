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


SIMPLEST_TEST01()
{
  echo -e "exit" > dummy.txt
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  echo "'${FUNCNAME[0]}' OK!"
}


TEST_ADM01()
{ 
  echo -e "0\n"  >  dummy.txt     # ADMINISTRATOR 
  echo -e "2\n5\nname5\ntype5\n"  >> dummy.txt     # add employee (wrong) 
  echo -e "2\n5\nname5\nmanager\n"  >> dummy.txt     # add employee (wrong) 
  echo -e "exit" >> dummy.txt

  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt
 
  echo "'${FUNCNAME[0]}' OK!"
}


TEST_ADM02()
{ 
  echo -e "0\n"  >  dummy.txt     # ADMINISTRATOR  
  echo -e "3\n\n2\n1"  >> dummy.txt     # add product (wrong) 
  echo -e "9\n9\n9\n9\n"  >> dummy.txt # id | name | price | cost | '0009' created! 

  echo -e "0\n2"  >>  dummy.txt # home 
  echo -e "2\ny\n"  >> dummy.txt  # login 0003  
  echo -e "3\n\n2\n9"  >> dummy.txt  # login 0003 | [selling] '9' cannot be sold! 
  echo -e "exit" >> dummy.txt
  
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt 
  rm dummy.txt
  
  echo "'${FUNCNAME[0]}' OK!"
}


TEST_MAN01()
{ 
  echo -e "1\n"  >  dummy.txt     # MANAGER 
  echo -e "2\ny\n"  >> dummy.txt  # login 
  echo -e "exit" >> dummy.txt

  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt 
  rm dummy.txt
  
  echo "'${FUNCNAME[0]}' OK!"
}


TEST_MAN02()
{
  echo -e "1\n"  >  dummy.txt     # MANAGER 
  echo -e "2\ny\n"  >> dummy.txt  # login 0005 
  echo -e "3\n\n3\n0101\n"  >> dummy.txt  # inventory | return | name0100 +1 
  echo -e "exit" >> dummy.txt

  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  echo "'${FUNCNAME[0]}' OK!"
}


TEST_CASH01()
{
  echo -e "2\n"  >  dummy.txt     # CASHIER
  echo -e "1\ny\n"  >> dummy.txt  # login 0004  
  echo -e "3\n\n2\nname0100"  >> dummy.txt  # INVENTORY | sell |   
  echo -e "0100\n\n "  >> dummy.txt # id 
  echo -e "exit" >> dummy.txt
  
  ${JAVA} -DDEBUG -jar ${EXEC} < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  echo "'${FUNCNAME[0]}' OK!"
}





TESTS()
{
  TEST_ADM01  
  TEST_ADM02
  TEST_MAN01
  TEST_MAN02
  TEST_CASH01 
  echo -e "'${FUNCNAME[0]}' OK!" 
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



INTERACTIVE()
{
  if [ $# -ne 0 ] 
  then

    SIMPLEST_TEST01

    ${JAVA} -DDEBUG -jar ${EXEC}
  fi
  echo "'${FUNCNAME[0]}' OK!"
}


JAVA_RUN_SETUP() 
{ 
  rm -rf RUNME
  cp -r src/main/java RUNME
  cp -r Tests RUNME
  cd RUNME
} 


JAVA_RUN_COMPILATION() 
{
  rm -rf ./Build
  ${JAVAC} -d ./Build Main.java
  cd ./Build

  echo -e "Manifest-Version: 1.0\nMain-Class: Main" > manifest.mf
  ${JAR} cmf ./manifest.mf ${EXEC} * 
}


JAVA_RUN() 
{
  JAVA_RUN_SETUP
  JAVA_RUN_COMPILATION

  ## Execution
  TESTS
  INTERACTIVE $1  

  rm manifest.mf
}


EXECUTE()
{
  clear 
  echo "'${FUNCNAME[0]}' ..."

  EXEC="inventorManager.jar"
  JAVA_PATH="/Users/poderozita/z2022_2/REPOSITORY/NOVAREPOTENTIAL/jdk-17.0.2.jdk/Contents/Home/bin/"

  UBUNTU_JAMMY
  JAVA_RUN $1
}


EXECUTE $1  

${JAVA} -jar ${EXEC}        


## 
## find . -type f -name "*.java" -exec sed -i'' -e 's/CreateOptions/Model/g' {} +
## find . -name "*.java-e" -type f -delete
## find . -name "*.class" -type f  -delete
##   
