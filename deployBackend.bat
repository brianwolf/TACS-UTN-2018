@ECHO OFF 
set CURRENT_DIR=%CD% 
set PROJECT_DIR=%CD%\Backend\cryptoCurrency

cd %PROJECT_DIR%
call cmd /c "mvn clean install"
heroku war:deploy %PROJECT_DIR%\apiWeb\target\apiWeb.war --app utn-crypto-currency-rest

cd %CURRENT_DIR%\ 

:salir 
