clear

MAVEN_SETUP() 
{
  if [[ $OSTYPE == 'linux-gnu'* ]]; then
    JAVA_PATH=
    MAVEN_PATH=
  fi

  export JAVA_HOME=${JAVA_PATH}
  MAVEN=${MAVEN_PATH}"mvn"

  echo "MAVEN:'${MAVEN}'"
  ${MAVEN} -version 
}


COMPILATION_SETUP() 
{
  rm -rf MAVEN
  mkdir MAVEN 
  cp -r src MAVEN
  cp pom.xml MAVEN 
  cd MAVEN
}


## 'bin/java' executable; 'release' file.
#export JAVA_HOME
JAVA_PATH="/Users/poderozita/z2022_2/REPOSITORY/NOVAREPOTENTIAL/jdk-17.0.2.jdk/Contents/Home/"

## 'mvn' executable 
MAVEN_PATH="/Users/poderozita/z2022_2/REPOSITORY/NOVAREPOTENTIAL/apache-maven-3.8.6/bin/"

MAVEN_SETUP

COMPILATION_SETUP
${MAVEN} clean 
${MAVEN} package 
