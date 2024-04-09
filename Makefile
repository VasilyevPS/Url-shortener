setup:
	gradle wrapper --gradle-version 8.7

clean:
	./gradlew clean

build:
	./gradlew clean build

start:
	./gradlew bootRun

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

.PHONY: build