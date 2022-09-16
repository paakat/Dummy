clear 


RUNME_TEST01()
{
  ## execute 
  cd ${ROOT}/AtmInterface
  bash RUNME.sh      

  ## tar 
  rm ${ROOT}/RUNME.tar

  cd RUNME/Build
  tar -cvf Atm.tar Atm.jar *.ser

  mv Atm.tar ${ROOT}/RUNME.tar  
}


MAVEN_TEST01()
{
  ## execute 
  cd ${ROOT}/AtmInterface
  bash MAVEN.sh

  ## tar 
  rm ${ROOT}/MAVEN.tar

  cd MAVEN/target
  cp ../*.ser . 
  tar -cvf Atm.tar AtmApp-V1.0.jar *.ser

  mv Atm.tar ${ROOT}/MAVEN.tar
}


ROOT=${PWD}
RUNME_TEST01
MAVEN_TEST01

