# file Dockerfile
FROM openjdk:8-jre

RUN \
  apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927 && \
  apt-get update && \
  apt-get install -y mongodb && \
  service mongodb start

# Define mountable directories.
VOLUME ["/data/db"]

# Define default command.
CMD ["mongod"]

# Expose ports.
#   - 27017: process
#   - 28017: http
EXPOSE 27017
EXPOSE 28017


ADD target/kubitz-server.jar app.jar
ADD run.sh run.sh
RUN ["chmod", "+x", "./run.sh"]
RUN sh -c 'touch /app.jar'
CMD ./run.sh