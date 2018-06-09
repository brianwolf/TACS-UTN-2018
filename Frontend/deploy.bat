@ECHO OFF

git add .
git commit --allow-empty -m "Deploy to next version"
git push heroku master

:salir
