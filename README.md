# Ethicost - Services

## Prerequisites

* Maven
* Java 1.8
* PSQL

## Setup

Start docker:

`./create-docker.sh`

## Start

`mvn spring-boot:run -Dmacquarie.oauth.clientId=$MACQUARIE_CLIENT_ID -Dmacquarie.oauth.clientSecret=$MACQUARIE_CLIENT_SECRET`