#!/bin/bash

TARGET="./deploy/FremadDeploy/"

cd ./src/main/dart
pub build

cd ../../../
cp -r ./src $TARGET

cp ./pom.xml $TARGET
