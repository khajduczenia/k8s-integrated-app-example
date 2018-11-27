# Example K8s microservice using GCP Marketplace solutions

## Background

This is a Spring Boot based application designed to be run natively on Kubernetes
and show the capabilities of GCP Marketplace solutions.

We are going to deploy two open source applications offered on the Kubernetes Marketplace
(a part of GCP Marketplace) - RabbitMQ and PostgreSQL. Then will run this application with
on Google Kubernetes Engine and integrate it with the ones deployed from the Marketplace.

## Infrastructure setup

### Run RabbitMQ from GCP Marketplace

Navigate to [Click to Deploy RabbitMQ](https://console.cloud.google.com/marketplace/details/google/rabbitmq)
Kubernetes application and follow the on-screen instructions to configure and install 
a RabbitMQ cluster on Kubernetes.
 
You may create a dedicated RabbitMQ user account to be used for application integration.

### Configure local environment to run Kubernetes deployment

We will use the Kubernetes cluster where RabbitMQ was deployed to run the deployment
of this application too. 

#### Authenticate to the cluster with `gcloud`

```shell
CLUSTER=<cluster name>
ZONE=<cluster zone>

gcloud container clusters get-credentials "$CLUSTER" --zone "$ZONE"
```

#### Create the `k8s-app` namespace

```shell
kubectl create namespace k8s-app
``` 

### Create a Kubernetes ConfigMap with the application configuration

RabbitMQ connection properties and credentials need to be configured in a ConfigMap
following the template from `manifests-setup/marketplace-env.yaml`.

Edit `manifests-setup/marketplace-env.yaml` to store the proper information
pointing to the RabbitMQ cluster. Run the creation of the ConfigMap:

```shell
kubectl apply -n k8s-app -f manifests-setup/marketplafce-env.yaml
```

### Build the application containers

Build the containers with cloud build and push them automatically to Google Container Registry:

```shell
gcloud builds submit .
```

### Run the installation of your app in K8s

```shell
kubectl apply -n k8s-app -f manifests-setup/
```

## Deployment result

As a result, you get your microservices connected to RabbitMQ and deployed to
autoscalable `Deployments`:
- an API component listening for HTTP connections on a REST endpoint and sending
  requests to the RabbitMQ topic,
- a backend component listening for incoming messages on a RabbitMQ queue and
  serving the incoming requests.
  
Navigate to the `Applications` tab of the GKE UI to see more information about
the resources created in the Kubernetes cluster and continue with the next steps.