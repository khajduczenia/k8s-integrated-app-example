#!/bin/bash

gcloud builds submit .

kubectl apply -n k8s-app -f manifests-app/
