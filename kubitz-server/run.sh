# file run.sh
#!/bin/sh
if [-z "$JAVA_OPTS"]; then
   java -jar  /app.jar
else
   service mongodb restart
   java $JAVA_OPTS -jar /app.jar
fi