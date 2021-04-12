variable "env_name" {
  description = "The environment for the GKE cluster"
  default     = "dev"
}
variable "image_name" {
  description = "The image from google container registry"
  default     = "devops-demo-ws"
}
variable "container_port" {
  description = "The container port to expose"
  default     = "8084"
}
variable "replicas_number" {
  description = "The number of replicas for pods in GKE"
  default     = "2"
}
variable "project_id" {
  description = "The project ID to host the cluster in"
  default     = "devops-default"
}