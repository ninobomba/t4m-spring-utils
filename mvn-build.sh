#!/bin/bash

# Check on the pom.xml and here
RELEASE_VERSION="0.0.0.1"
ARTIFACT_ID="t4m-spring-utils"

cleanup()
{
  echo "build::cleanup() -> Executing maven home cleanup for this package"

    mvn clean
    mvn dependency:purge-local-repository -DmanualInclude="io.github.ninobomba:$ARTIFACT_ID:$RELEASE_VERSION"
    rm -rf ~/.m2/repository/io/github/ninobomba/$ARTIFACT_ID

  echo "build::cleanup() -> clean up is complete"
}

package()
{
  echo "build::package() -> packing jar file"

    mvn package -DskipTests
    mvn install -DskipTests

  echo "build::package() -> packing jar file is complete"
}

# deploys to .m2 directory
deploy()
{
  echo "build::deploy() -> Deploying jar file locally"
  mvn install:install-file -Dfile=target/$ARTIFACT_ID-$RELEASE_VERSION.jar -DgroupId=io.github.ninobomba -DartifactId=$ARTIFACT_ID -Dversion=$RELEASE_VERSION -Dpackaging=jar -DgeneratePom=true -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -DupdateReleaseInfo=true
  echo "build::deploy() -> Deploying jar file locally is complete"
}

#################################################
pwd

echo "<<<<<<<<<< ############## cleanup ############## >>>>>>>>>>>"
cleanup

echo "<<<<<<<<<< ############## package ############## >>>>>>>>>>>"
package

echo "<<<<<<<<<< ############## deploy ############## >>>>>>>>>>>"
deploy

echo "<<< complete >>>"

# https://central.sonatype.com/artifact/io.github.ninobomba/t4m-spring-utils