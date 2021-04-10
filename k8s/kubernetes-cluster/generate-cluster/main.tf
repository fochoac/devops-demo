# Structure by enviroment

# Machine Types: https://cloud.google.com/compute/docs/machine-types

# Development Enviroment
module "dev_cluster" {
    source     = "./main"
    env_name   = "dev"
    project_id = var.project_id
    instance_type = "e2-medium"
    subnet_ip  =  "11.10.0.0/16"
    secondary_ip_cidr_range_pods = "11.20.0.0/16"
    secondary_ip_cidr_range_services = "11.30.0.0/16"
    region = "us-central1"
}

# Staging Enviroment 
# Commeting lines because there are problems with the quota for the free layer
# module "staging_cluster" {
#     source     = "./main"
#     env_name   = "staging"
#     project_id = var.project_id
#     instance_type = "e2-small"
#     subnet_ip  =  "20.10.0.0/16"
#     secondary_ip_cidr_range_pods = "20.20.0.0/16"
#     secondary_ip_cidr_range_services = "20.30.0.0/16"
# }

# Production Enviroment 
# module "prod_cluster" {
#     source     = "./main"
#     env_name   = "prod"
#     project_id = var.project_id
#     instance_type = "e2-medium"
#     subnet_ip  =  "30.10.0.0/16"
#     secondary_ip_cidr_range_pods = "30.20.0.0/16"
#     secondary_ip_cidr_range_services = "30.30.0.0/16"
# }
 
variable "project_id" {
  description = "The project ID to host the cluster in"
  default     = "devops-default"
}