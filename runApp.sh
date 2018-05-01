local CURRENT_DIR=$1
local PROJECT_DIR=$1/Backend/cryptoCurrency

cd $PROJECT_DIR
export MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
screen mvn clean install tomcat7:run && exit
cd $CURRENT_DIR/Frontend 
screen npm install && npm start && exit