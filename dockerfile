FROM eclipse-temurin:8-jdk
COPY ./config /config
ENV TZ=Asia/Phnom_Penh
ARG num
ENV name ./build/libs/CrmReportServer-${num}.jar
COPY $name /CrmReportServer.jar
CMD ["java", "-Xms256m", "-Xmx512m" ,"-XX:MinHeapFreeRatio=40","-XX:MaxHeapFreeRatio=70", "-jar", "./CrmReportServer.jar"]