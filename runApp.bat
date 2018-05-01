@ECHO OFF 
set CURRENT_DIR=%CD% 
set PROJECT_DIR=%CD%/Backend/cryptoCurrency

cd %PROJECT_DIR% 
set MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
start cmd /c "mvn clean install tomcat7:run && exit"

cd %CURRENT_DIR%/Frontend

start cmd /c "npm install && npm start"

:salir 
cd %CURRENT_DIR%\ 
