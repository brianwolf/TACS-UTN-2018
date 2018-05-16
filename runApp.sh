###########################################
#VARS
###########################################

#name of console istalled in your linux distro
CONSOLE="konsole -e"

CURRENT_DIR="$PWD"
PROJECT_DIR=$CURRENT_DIR'/Backend/cryptoCurrency'

###########################################
#TOMCAT
###########################################
cd "$PROJECT_DIR"
export MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
#screen mvn clean install tomcat7:run && exit
$CONSOLE "mvn clean install tomcat7:run" &

###########################################
#NODEJS
###########################################
cd "$CURRENT_DIR/Frontend"
#screen npm install && npm start && exit
npm install
npm start