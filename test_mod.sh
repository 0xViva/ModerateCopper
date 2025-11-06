#!/bin/bash


echo "cleanup:"
./gradlew clean
echo "rundata:"
./gradlew runData
echo "build:"
./gradlew build
echo "runGametest:"
./gradlew runGametest
# Set the source and destination paths
SOURCE="build/libs/*.jar"
DEST="$HOME/.minecraft/mods"

# Copy the jar file(s) to the mods folder
echo "Copying jar files from $SOURCE to $DEST"
cp -f $SOURCE $DEST

echo "Done!"
