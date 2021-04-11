variable "env_name" {
  description = "The environment for the GKE cluster"

}
variable "swagger_spec_name" {
  description = "The swagger spec file name"
  default     = "devops"
}
variable "project_id" {
  description = "The project ID to host the cluster in"

}
variable "cluster_name" {
  description = "The name for the GKE cluster"
  default     = "devops-cluster"
}
variable "region" {
  description = "The region to host the cluster in"

}