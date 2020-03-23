FROM centos:centos6.6

ADD ["jdk-8u172-linux-x64.rpm", "/home/mdmsadmin/"]
WORKDIR /home/mdmsadmin/

RUN rpm -ivh jdk-8u172-linux-x64.rpm

ENV JAVA_HOME "$JAVA_HOME:/usr/java/jdk1.8.0_172-amd64"
ENV PATH "$PATH:$JAVA_HOME/bin"
ENV PATH "$PATH:/usr/bin"
ADD startup.sh /home/mdmsadmin

EXPOSE 8301
ADD target/mdms-settings-mock-1.0-SNAPSHOT.jar mdms-settings-mock.jar

RUN  sed -i -e 's/\r$//' /home/mdmsadmin/startup.sh

CMD [ "/bin/bash", "/home/mdmsadmin/startup.sh"]