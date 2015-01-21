#!/bin/bash

TARGET="./deploy/FremadDeploy/"

cp -r ./src $TARGET
cp ./pom.xml $TARGET

find $TARGET -name ".gitignore" | xargs rm

cd $TARGET

git add .
read -p "Commit description: " desc  

git commit -m $desc  
git push
