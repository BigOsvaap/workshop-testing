mkdir microservices
cd microservices || exit

spring init \
--boot-version=2.5.2 \
--build=gradle \
--java-version=11 \
--packaging=jar \
--name=civil-service \
--package-name=osvaap.microservices.core.civil \
--groupId=osvaap.microservices.core.civil \
--dependencies=webflux,data-mongodb \
--version=1.0.0-SNAPSHOT \
civil-service

cd ..