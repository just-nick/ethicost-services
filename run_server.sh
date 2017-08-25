#!/usr/bin/env bash

export MACQUARIE_CLIENT_ID='aqMchxLQ3sldvvKEZxMBmd95lBhnhm4z'
export MACQUARIE_CLIENT_SECRET='JNBQkJ22vcU2ynMf'

mvn spring-boot:run -Dmacquarie.oauth.clientId=${MACQUARIE_CLIENT_ID} -Dmacquarie.oauth.clientSecret=${MACQUARIE_CLIENT_SECRET}
