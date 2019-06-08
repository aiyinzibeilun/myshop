cd ..
cd myshop-dependencies
call mvn clean deploy
cd ..
cd myshop-common
call mvn  clean deploy

cd ..
cd myshop-common-domain
call mvn  clean deploy

cd ..
cd myshop-common-servcie
call mvn  clean deploy

cd ..
cd myshop-common-web
call mvn deploy

cd ..