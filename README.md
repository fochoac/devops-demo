# devops-demo
Example de DevOps With Google Cloud

## Prerequisites

##### **devops-demo-ws**


|  Requirements                     | Version |Installation|
| ------- | --------------------------------- |-----------------|
|  Java | 8 |  [LINK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)|
| Maven | 3 |  [LINK](https://maven.apache.org/install.html) |
| Git | any version |  [LINK]( https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) |
|Docker| any version| [LINK](https://docs.docker.com/engine/install/) |


#####  **Infrastructure as code (IaC)**

|  Requirements                     | Version |Installation|
| ------- | --------------------------------- |-----------------|
| Terraform | V0.14.10 or mayor| [LINK](https://www.terraform.io/downloads.html)|
|SDK Google Cloud (gcloud)| 334.0.0| [LINK](https://cloud.google.com/sdk?hl=en)|
|Command Line Interface of kubernetes(kubectl) |v1.1 or mayor| [LINK](https://kubernetes.io/es/docs/tasks/tools/install-kubectl/)|

## Run the Rest API
For run the microservice, execute:

```bash
mvn clean install
java -jar target/*.jar
```
Additionally, the API rest was configured with OpenAPI spec 2.0 and you can test the Rest API  in the next url:

- http://localhost:8084/public/openapi/ui 

<img src="./images/swagger-ui.jpg" alt="Operation of Google Cloud" style="zoom:50%;" />

- For download the OpenAPI Spec of the Rest API microservice: http://localhost:8084/public/openapi 

## Integration Testing

For make integration testing, you can use the next endpoint:

|  Enviroment                     | URL|
| ------- | --------------------------------- |
| Production | http://85.19.231.35.bc.googleusercontent.com/public/openapi/ui |
| Development | http://141.93.222.35.bc.googleusercontent.com/public/openapi/ui |



## Architecture

The project manages two models:
##### **devops-demo-ws**

The architecture consideration was "Onion Architecture", where manage the next layers:

|  Layer                     | Description|
| ------- | --------------------------------- |
| Service | Handle the logical business |
| Model | Handle stereotypes used in the requests and responses of web services |
| Configuration | Handle the configuration for API Layer |
| Enumeration | Handle the constants of the project |
| Producer | Additional layer for manage javax.enterprise.inject.Produces |
| Api | Handle the endpoint and additional classes for filter tokens or request methods |
| Util | Handle utility classes for the project |



#####  **Infrastructure as code (IaC)**

The folder structure to manage is:


```bash
IaC/
└── gke
    ├── cluster
    │   ├── kubeconfig-dev
    │   ├── kubeconfig-prod
    │   ├── main
    │   │   ├── main.tf
    │   │   ├── outputs.tf
    │   │   └── variables.tf
    │   ├── main.tf
    │   ├── terraform.tfstate
    │   └── terraform.tfstate.backup
    └── deployment
        ├── api-gateway
        │   ├── devops-dev.yaml
        │   ├── devops-prod.yaml
        │   ├── main
        │   │   ├── api_gateway.tf
        │   │   └── variables.tf
        │   ├── main.tf
        │   └── terraform.tfstate
        └── pods
            ├── main.tf
            ├── terraform.tfstate
            ├── terraform.tfstate.backup
            └── variables.tf


```

Where each folder manage the next considerations:

|  Folder                     | Description|
| ------- | --------------------------------- |
| GKE | Handle the IaC for Google Kubernetes Engine |
| Cluster | Handle the code for generate many clusters with specific environment: Development (dev), Staging (staging), Production etc. |
| Deployment | Handle the code for generate pods and an API Gateway for each cluster environment. |



# CI/CD

## Continuous Integration

The global solution manage the next stages:

|  Stage                     | Description|
| ------- | --------------------------------- |
| Build | Manage the task for build de project |
| Test | Manage the task for execute coverage test and static analysis of code with SonarQube |
| Deploy | Manage the task for push the docker image to Google Container Register and update of pods with new docker image. |

## Continuous Deployment and Delivery

The global solution manage the next stage:

|  Stage                     | Description|
| ------- | --------------------------------- |
| Deploy | Manage the task for push the docker image to Google Container Register and update of pods with new docker image. Depending the branch, the stage can execute automatically (**Continuous Deployment**) or manually (**Continuous Delivery**) |

# Versioning

The global project use **Semantic Versioning and Structure Specification** publish by IETF in the link: https://tools.ietf.org/id/draft-claise-semver-00.html.



# Operation flow

The project uses the  [GitHub Flow](https://guides.github.com/introduction/flow/)  for the operation flow in GitHub Actions.

# Project workflow

### Considerations

When the developer push to development branch on GitHub, the project must be configure the flow for CI/CD with GitHub Actions.

- Before of execute de deploy stage, IaC must be executed in the cloud.

For execute the cluster generation, execute the next commands:

```bash
cd IaC/gke/cluster
terraform init #Initialize and download terraform deependencies
terraform plan # generate a review of the plan to execute in google cloud
terraform apply -auto-approve # Apply the plan in Google Cloud
```
For execute the generation of pods into generate cluster:

```bash
cd IaC/gke/deployment/pods
terraform init #Initialize and download terraform deependencies
terraform plan # generate a review of the plan to execute in google cloud
terraform apply -auto-approve # Apply the plan in Google Cloud
```
For execute the generation of API Gateway, you must provide a OpenApi specification, for this actions, you can donwnload the spec: 



```bash
cd IaC/gke/deployment/api-gateway
terraform init #Initialize and download terraform deependencies
terraform plan # generate a review of the plan to execute in google cloud
terraform apply -auto-approve # Apply the plan in Google Cloud
```



Once executed, the pipeline must be execute for run the next flow in Google Cloud: 

![Operation of Google Cloud](./images/operation-gke.jpg)

