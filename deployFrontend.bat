@ECHO OFF 
set CURRENT_DIR=%CD% 
set PROJECT_DIR=%CD%\Frontend

cd %PROJECT_DIR%
call cmd /c "git init && heroku git:remote -a utn-crypto-currency-web"
git add .
git commit --allow-empty -m "Deploy to next version"
git push heroku master

cd %CURRENT_DIR%\ 

:salir 
