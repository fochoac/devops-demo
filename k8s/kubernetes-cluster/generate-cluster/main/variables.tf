variable "project_id" {
  description = "The project ID to host the cluster in"
  default = "devops-default"
}
variable "cluster_name" {
  description = "The name for the GKE cluster"
  default     = "devops-cluster"
}
variable "instance_type" {
  default     = "e2-medium"
}
variable "subnet_ip" {
  description = "The main subnet for the cluster"
  default     = "10.10.0.0/16"
}
variable "secondary_ip_cidr_range_pods" {
  description = "The secondary ip cidr for define pods network"
  default     = "10.20.0.0/16"
}
variable "secondary_ip_cidr_range_services" {
  description = "The secondary ip cidr for define services network"
  default = "10.30.0.0/16"
}
variable "env_name" {
  description = "The environment for the GKE cluster"
  default     = "dev"
}
variable "region" {
  description = "The region to host the cluster in"
  default     = "us-east1"
}
variable "network" {
  description = "The VPC network created to host the cluster in"
  default     = "gke-network"
}
variable "subnetwork" {
  description = "The subnetwork created to host the cluster in"
  default     = "gke-subnet"
}
variable "ip_range_pods_name" {
  description = "The secondary ip range to use for pods"
  default     = "ip-range-pods"
}
variable "ip_range_services_name" {
  description = "The secondary ip range to use for services"
  default     = "ip-range-services"
}