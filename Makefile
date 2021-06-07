run:
	gradle run --args="--skip true"

gradlew:
	./gradlew run

installwindows:
	choco install gradle

installunix:
	/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
	brew install gradle
