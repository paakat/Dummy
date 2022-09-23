clear 


RUNME_TEST01()
{
  ## execute 
  cd ${ROOT}/${PROJECT}
  bash RUNME.sh      

  ## tar 
  rm ${ROOT}/RUNME.tar

  cd RUNME/Build
  tar -cvf ${APP}.tar ${APP}.jar *.ser

  mv ${APP}.tar ${ROOT}/RUNME.tar  
}


MAVEN_TEST01()
{
  ## execute 
  cd ${ROOT}/${PROJECT}
  bash MAVEN.sh

  ## tar 
  rm ${ROOT}/MAVEN.tar

  cd MAVEN/target
  cp ../*.ser . 
  tar -cvf ${APP}.tar ${PROJECT}-V1.0.jar *.ser

  mv ${APP}.tar ${ROOT}/MAVEN.tar
}


ROOT=${PWD}
PROJECT=InventoryManager
APP=inventorManager

RUNME_TEST01
MAVEN_TEST01

