#!/usr/bin/env bash

export MACQUARIE_CLIENT_ID=
export MACQUARIE_CLIENT_SECRET=

mvn spring-boot:run -Dmacquarie.oauth.clientId=${MACQUARIE_CLIENT_ID} -Dmacquarie.oauth.clientSecret=${MACQUARIE_CLIENT_SECRET}
