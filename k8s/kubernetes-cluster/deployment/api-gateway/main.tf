# Development Enviroment
module "development-api-gateway" {
    source     = "./main"
    env_name   = "dev"
    region     = "us-central1"
    project_id = var.project_id
   
}

# Production Enviroment
module "production-api-gateway" {
    source     = "./main"
    env_name   = "prod"
    region     = "us-east1"
    project_id = var.project_id
   
}

variable "project_id" {
  description = "The project ID to host the cluster in"
  default     = "devops-default"
}