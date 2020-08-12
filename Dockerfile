FROM hub.neshan.org/rajman-java8:latest as staging
MAINTAINER neshan.com
RUN apt install -y jq
ARG JAR_FILE=target/metrics-server*.jar
ARG CURL_FILE=target/classes/getData.sh
ADD ${JAR_FILE} /opt/metrics-server.jar
ADD ${CURL_FILE} /opt/getData.sh
RUN cd /opt; ls -ltrh
WORKDIR /opt/
EXPOSE 443