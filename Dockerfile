FROM gradle:8.7-jdk21

WORKDIR /app

COPY ./ .

RUN ./gradlew installDist

CMD ./build/install/url-shortener/bin/url-shortener