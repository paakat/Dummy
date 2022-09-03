clear 


UBUNTU_JAMMY()
{
  JAVA_PATH=
  JAVA=${JAVA_PATH}"java"
  JAVAC=${JAVA_PATH}"javac -Xlint" # extra information with '-Xlint'
  JAR=${JAVA_PATH}"jar"
  echo ${JAVA}

  ${JAVA} -version 
}


MACOS_BIGSUR()
{
  JAVA_PATH=""
  JAVA=${JAVA_PATH}"java"
  JAVAC=${JAVA_PATH}"javac -Xlint" # extra information with '-Xlint'
  JAR=${JAVA_PATH}"jar"
  echo ${JAVA}

  ${JAVA} -version 
}


REMOVE_CLASS_FILES() 
{
  find . -name "*.class" -type f  -delete
  find . -name "*.class" -type f  
}


COMPARING()
{
  nlines=$(diff $1 $2 | wc -l)                    

  if [ "${nlines}" -ne "0" ]; then
    echo "files: '$1' '$2' no match" 
    #echo -e "'${FUNCNAME[0]}' ERROR!\nExit.." 
    #vim -d $1 $2
    #cp $1 ../Tests  # < be carreful... 
    #exit 1
  fi

  #echo "'${FUNCNAME[0]}' OK!"
  return 1
}


MANAGER_TEST01() 
{
  echo -e " 1\nx\n  \n3\n2\nexit"  > "dummy.txt"

  ${JAVA} -jar $1 < <(cat dummy.txt) 
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt 
  rm dummy.txt

  #${JAVA} -jar $1 
  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt 
  echo "'${FUNCNAME[0]}' OK!"
}


DIETICIAN_TEST01() 
{
  echo -e "2\n2\n0" > dummy.txt # dietician/recipeView/main  
  echo -e "2\n3\n0" >> dummy.txt # dietician/recipesNew/main  
  echo -e "2\n1" >> dummy.txt # dietician/recipesList
  echo "exit" >> dummy.txt 

  ${JAVA} -jar $1 < <(cat dummy.txt) 
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt 
  rm dummy.txt

  #${JAVA} -jar $1 
  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


DIETICIAN_TEST02() 
{
  echo "2" > dummy.txt # dietician  
  echo "0" >> dummy.txt # manager 
  echo "2" >> dummy.txt # dietician  
  echo "3" >> dummy.txt # recipeNew  
  echo "0" >> dummy.txt # manager 
  echo "exit" >> dummy.txt

  ${JAVA} -jar $1 < <(cat dummy.txt) 
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


RECIPENEW_TEST01() 
{
  echo "2" > dummy.txt # dietician  
  echo "3" >> dummy.txt # recipeNew  
  echo -e "1\n  nameNew   1    \n" >> dummy.txt # 
  echo -e "2\ntagNew 1\n" >> dummy.txt # 
  echo -e "2\ntagNew 2\n" >> dummy.txt # 
  echo -e "3\ningredientNew 1\n1.0\nKG\n" >> dummy.txt # 
  echo -e "3\ningredientNew 2\n2.0\nPC\n" >> dummy.txt # 
  echo -e "3\ningredientNew 3\na\nL \n" >> dummy.txt # 
  echo -e "4\nstepNew 1\n" >> dummy.txt # 
  echo -e "4\nstepNew 2\n" >> dummy.txt # 
  echo -e "4\nstepNew 3\n" >> dummy.txt # 
  echo -e "4\nstepNew 4\n" >> dummy.txt # 
  echo -e "10\n53\n-1" >> dummy.txt # done/week/day  
  echo -e "10\n 1 \na" >> dummy.txt # done/week/day  
  echo -e "10\n1\n1" >> dummy.txt # done/week/day  
  echo "exit" >> dummy.txt

  #${JAVA} -jar $1 < <(cat dummy.txt); rm week01_day01.ser
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


RECIPENEW_TEST02() 
{
  echo "2" > dummy.txt # dietician  
  echo "3" >> dummy.txt # recipeNew  

  echo "4" >> dummy.txt # stepNew  
  echo " step a  " >> dummy.txt # 

  echo "4" >> dummy.txt # stepNew  
  echo " step x  " >> dummy.txt # 

  echo "4" >> dummy.txt # stepNew  
  echo " step b  " >> dummy.txt # 

  echo "6" >> dummy.txt # stepRemove 
  echo " 2.1 " >> dummy.txt # 

  echo "6" >> dummy.txt # stepRemove 
  echo "2" >> dummy.txt # 

  echo "3" >> dummy.txt # ingredientNew 
  echo "Ingredient  X" >> dummy.txt # Ingredient
  echo " 2.0" >> dummy.txt # Amount 
  echo " L" >> dummy.txt # Unit 

  echo "2" >> dummy.txt # tagNew 
  echo " tag   a " >> dummy.txt # 

  echo "3" >> dummy.txt # ingredientNew 
  echo "   Ingredient  A " >> dummy.txt # Ingredient
  echo " 1.0" >> dummy.txt # Amount 
  echo " KGS" >> dummy.txt # Unit (only allowed) 

  echo "3" >> dummy.txt # ingredientNew 
  echo "   Ingredient  A " >> dummy.txt # Ingredient
  echo " 1.0" >> dummy.txt # Amount 
  echo " PC" >> dummy.txt # Unit 

  echo "1" >> dummy.txt # nameNew  
  echo "   Recipe   Name  " >> dummy.txt #  

  echo "5" >> dummy.txt # ingredientRemove
  echo "3" >> dummy.txt # 

  echo "5" >> dummy.txt # ingredientRemove
  echo "1" >> dummy.txt # 

  echo "   ??" >> dummy.txt # 

  echo -e "10\n1\n3" >> dummy.txt # done/week/day  
  echo "exit" >> dummy.txt


  #${JAVA} -jar $1 < <(cat dummy.txt); rm week01_day03.ser  
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  #${JAVA} -jar $1 
  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


RECIPELIST_TEST01() 
{
  echo -e "2\n3\n1\n  Recipe  emPTy  \n10\n2\n4" > dummy.txt # dietician/recipeNew/nameNew/week/day 
  echo -e "0\n2\n1\n3" >> dummy.txt  # main/dietician/recipesList/week02_day04
  echo -e "1\n0" >> dummy.txt # week01_day01/manager
  echo "exit" >> dummy.txt

  #${JAVA} -jar $1 < <(cat dummy.txt); rm week02_day04.ser 
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


RECIPEVIEW_TEST01() 
{
  echo -e "2\n2\n2\n" > dummy.txt # dietician/recipeView/week01_day03
  echo -e "4\n1\n New Extra  Step 1 " >> dummy.txt # stepNew/' New Extra Step  ' (fail) 
  echo -e "4\n2\n  New Extra Step  2" >> dummy.txt # stepNew/' New Extra Step  ' 
  echo -e "3\n 5 \n  New Extra ingredient   A  \n 2.0 \n  KG \n" >> dummy.txt # fail  
  echo -e "3\n5\n  New Extra ingredient   A  \n 2.0 \n  KG \n" >> dummy.txt # good 
  echo -e "1\n  New Menu  name  \n" >> dummy.txt # nameNew/'New Menu  name'
  echo -e "10\n52\n7" >> dummy.txt # done/week/day  
  echo -e "0\n2\n2" >> dummy.txt # main/dietician/recipesList 
  echo "exit" >> dummy.txt

  #${JAVA} -jar $1 < <(cat dummy.txt); rm week52_day07.ser 
  ${JAVA}  -jar $1 < <(cat dummy.txt) > ${FUNCNAME[0]}.txt
  rm dummy.txt

  #${JAVA} -jar $1 
  COMPARING ${FUNCNAME[0]}.txt ../Tests/${FUNCNAME[0]}.txt
  echo "'${FUNCNAME[0]}' OK!"
  #exit
}


TESTS()
{
     MANAGER_TEST01 ${EXEC} 
   DIETICIAN_TEST01 ${EXEC} 
   DIETICIAN_TEST02 ${EXEC}
   RECIPENEW_TEST01 ${EXEC}
   RECIPENEW_TEST02 ${EXEC} 
  RECIPELIST_TEST01 ${EXEC}
  RECIPEVIEW_TEST01 ${EXEC}
}


JAVA_RUN_DEBUG() 
{
  EXEC="FoodMora.jar" 

  REMOVE_CLASS_FILES

  rm -rf ./Build
  cp -r RecipeManager/src Build
  cd Build  
  #exit 

  ${JAVAC} -d ./Build Main.java # --> *.class's 

  cd ./Build
  echo -e "Manifest-Version: 1.0\nMain-Class: Main" > manifest.mf
  ${JAR} cmf ./manifest.mf ${EXEC} *

  time TESTS 
  #${JAVA} -jar ${EXEC} 

  tar -cvf ../../FoodMora.tar $EXEC  
  rm manifest.mf 

  cd ../../
}


JAVA_RUN()
{
  EXEC="FoodMora.jar"

  REMOVE_CLASS_FILES

  rm -rf ./Build
  cp -r RecipeManager/src Build
  cd Build

  ${JAVAC} -d ./Build Main.java # --> *.class's 

  cd ./Build
  echo -e "Manifest-Version: 1.0\nMain-Class: Main" > manifest.mf
  ${JAR} cmf ./manifest.mf ${EXEC} *

  #${JAVA} -jar ${EXEC} 

  rm manifest.mf
}


RUNME_TEST01()
{
  cd /home/RecipeManager
  bash RUNME.sh
}


#MACOS_BIGSUR
UBUNTU_JAMMY

JAVA_RUN_DEBUG  
JAVA_RUN 
RUNME_TEST01

REMOVE_CLASS_FILES
