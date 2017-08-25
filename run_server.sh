#!/usr/bin/env bash

MACQUARIE_CLIENT_ID=ktJRf8rPGyICjSVMQZKDAnKykrPsN81C
MACQUARIE_CLIENT_SECRET=cXbiQ9M487inR7Gc

mvn spring-boot:run -Dmacquarie.oauth.clientId=${MACQUARIE_CLIENT_ID} -Dmacquarie.oauth.clientSecret=${MACQUARIE_CLIENT_SECRET}
