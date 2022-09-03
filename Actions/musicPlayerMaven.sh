clear 


SYSTEM()
{
  ${JAVA} -version 
  mvn --version 
  date  
}


DESPACITO_BY_SUPERSEARCH()
{ 
  echo -e "\n\n'${0}'..."

  results=$'Results\ndespacito.mp3\nDespacito\nreggaeton\nvida.png\nLuis Fonsi\non\nVida'

  # main | csv | main | spotifoo | main | album | vida | no exist | sola  
  echo -e " 2\n 1\n 0\n 6\n 1\n 5\n sam's town\n 3\n -1\n"  > "dummy.txt"
  ${JAVA} -jar ${JAR} < <(cat dummy.txt)

  content=$(cat result.dummy)
  if [ "${results}" != "${content}" ]; then
    echo "   given:"${content}
    echo "expected:"${results}
    echo -e "'${0}' ERROR!\n" 
    exit 1
  fi

  rm dummy.txt
  rm result.dummy

  echo "'${0}' OK!"
  echo 
  echo  
  return 1
}


WWWY_BY_CSVFILE()
{ 
  echo -e "\n\n'${0}'..."

  results=$'Results\nno file\nSola\nreggaeton\nvida.png\nLuis Fonsi\non\nVida'

  # main | csv | main | spotifoo | main | album | vida | no exist | sola  
  echo -e " 2\n 1\n 0\n 6\n 1\n 3\n 8\n 20\n 15\n -1\n"  > "dummy.txt"
  ${JAVA} -jar ${JAR} < <(cat dummy.txt)

  content=$(cat result.dummy)
  if [ "${results}" != "${content}" ]; then
    echo "   given:"${content}
    echo "expected:"${results}
    echo -e "'${0}' ERROR!\n" 
    exit 1
  fi

  rm dummy.txt
  rm result.dummy

  echo "'${0}' OK!"
  echo 
  echo  
  return 1
}


WWWY_BY_ALBUM()
{ 
  echo -e "\n\n'${0}'..."

  results=$'Results\nwhen-we-were-young.mp3\nWhen we where young\nrock\nno file\nThe Killers\non\nSam\'s town'

  # main | albums | sam's town | When we where young
  echo -e " 1\n 3\n 8\n 20\n -1\n"  > "dummy.txt"
  ${JAVA} -jar ${JAR} < <(cat dummy.txt)

  content=$(cat result.dummy)
  if [ "${results}" != "${content}" ]; then
    echo "   given:"${content}
    echo "expected:"${results}
    echo -e "'${0}' ERROR!\n" 
    exit 1
  fi

  rm dummy.txt
  rm result.dummy

  echo "'${0}' OK!"
  echo 
  echo  
  return 1
}



SOLA_BYSEARCHING()
{
  echo -e "\n\n'${0}'..."
  
  results=$'Results\nno file\nSola\nreggaeton\nvida.png\nLuis Fonsi\non\nVida'
  
  echo -e " 1\n 5\n sol\n 15\n -1\n"  > "dummy.txt"  
  ${JAVA} -jar ${JAR} < <(cat dummy.txt)

  content=$(cat result.dummy)
  if [ "${results}" != "${content}" ]; then
    echo "   given:"${content}
    echo "expected:"${results}
    echo -e "'${0}' ERROR!\n" 
    exit 1
  fi

  rm dummy.txt
  rm result.dummy
  
  echo "'${0}' OK!"
  echo 
  echo  
  return 1
}


TESTS() 
{
  SOLA_BYSEARCHING
  WWWY_BY_ALBUM
  WWWY_BY_CSVFILE
  DESPACITO_BY_SUPERSEARCH 
}


MAVEN_BUILD() 
{
  rm -rf Build  
  cp -r MusicPlayer Build
  cd Build 

  mvn clean package

  cd ..
}


SPOTIFOO() 
{
  tar -xf Inputs/assets.tar 

  mkdir Spotifoo 
  mv assets Spotifoo 

  cp Inputs/nokillers.csv Spotifoo
  cp ${ARTIFACT} Spotifoo/${JAR} 

  cd Spotifoo 

  ## First Test. 
  ## Main -> Songs -> Despacito -> When ... -> Welcome -> sola
  ##   
  ##  1 Main  
  echo " 1 "   > "dummy.txt"
  ##  1 Songs   
  echo " 1 "   >> "dummy.txt"
  ##  3 Despacito (ok)  
  echo " 3 "   >> "dummy.txt"
  ## 20 When we where young (no-picture.png)  
  echo " 20 "  >> "dummy.txt"
  ## 17 Welcome to New York (no mp3 extension)   
  echo " 17 "  >> "dummy.txt"
  ## 15 sola (no mp3) 
  echo " 15 "  >> "dummy.txt"
  ## 
  echo " exit ">> "dummy.txt"

  ${JAVA} -jar ${JAR} < <(cat dummy.txt)  
}



UBUNTU_JAMMY() 
{
  JAVA="java"
  ARTIFACT="Build/target/spotifoo-V1.0.jar"

  rm -rf Spotifoo 
  MAVEN_BUILD 
  SPOTIFOO
  TESTS

  tar -cvf ../Spotifoo.tar Spotifoo.jar nokillers.csv

  SYSTEM
}


MACOS_BIGSUR() 
{
  JAVA_PATH="/WHERE/JAVA/IS/LOCATED?/"
  JAVA=${JAVA_PATH}"/jdk-17.0.2.jdk/Contents/Home/bin/java"
  ARTIFACT="MusicPlayer/target/spotifoo-V1.0.jar"

  SPOTIFOO
  TESTS

  ${JAVA} -version 
}


JAR=Spotifoo.jar

UBUNTU_JAMMY 

#MACOS_BIGSUR

##${JAVA} -jar ${JAR} 
