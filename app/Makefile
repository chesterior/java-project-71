.PHONY: build report run-dist

build:
	./gradlew build

report:
	./gradlew jacocoTestReport

run-dist:
	./gradlew :app:installDist
	./app/build/install/app/bin/app -h