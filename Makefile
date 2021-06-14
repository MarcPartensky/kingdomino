run:
	# gradle run --args="--skip true"
	gradle run

gradlew:
	./gradlew run

installwindows:
	choco install gradle
	choco install openjdk-13

installunix:
	/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
	brew install gradle
