run:
	./gradlew run

installwindows:
	choco install gradle
	choco install openjdk-11

installunix:
	/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
	brew install gradle

docker: dockerbuild dockerrun

dockerbuild:
	docker build . -t marcpartensky/kingdomino

dockerrun:
	docker run --rm --device /dev/dri -e DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix -t marcpartensky/kingdomino
