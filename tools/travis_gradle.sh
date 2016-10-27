#!/usr/bin/env bash

echo "check the project and assemble it"

./gradlew check

./gradlew build assemble
