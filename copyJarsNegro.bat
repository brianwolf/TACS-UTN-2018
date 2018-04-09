@ECHO OFF 
set CURRENT_DIR=%CD% 
set PROJECT_DIR=%CD%/Backend 
 
 
cd %PROJECT_DIR%/core 
call mvn clean install -Dmaven.test.skip=true 
cd %PROJECT_DIR%/model 
call mvn clean install -Dmaven.test.skip=true 
cd %PROJECT_DIR%/userService 
call mvn clean install -Dmaven.test.skip=true 
cd %PROJECT_DIR%/adminService 
call mvn clean install -Dmaven.test.skip=true
cd %PROJECT_DIR%/externalService 
call mvn clean install -Dmaven.test.skip=true
cd %PROJECT_DIR%/walletService 
call mvn clean install -Dmaven.test.skip=true   
cd %PROJECT_DIR%/apiWeb 
call mvn clean install -Dmaven.test.skip=true 
 
:salir 
cd %CURRENT_DIR%\ 