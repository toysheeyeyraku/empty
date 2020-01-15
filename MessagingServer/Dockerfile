FROM anapsix/alpine-java

ARG JAR_NAME

ENV JAR_NAME ${JAR_NAME}
ENV JAVA_ENV -Xmx256m

ADD $JAR_NAME /

CMD exec java $JAVA_ENV -jar /$JAR_NAME