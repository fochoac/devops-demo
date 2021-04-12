provider "google" {
 
}

provider "kubernetes" {
  config_path = format("./../../generate-cluster/kubeconfig-%s",var.env_name)
}

resource "kubernetes_namespace" "k8s_namespace" {
  metadata {
    name = var.env_name
  }
}
 locals {
name=format("%s-%s",var.image_name,var.env_name)
load_balancer_service_name=format("%s-service",local.name)
}
resource "kubernetes_deployment" "k8s_deployment" {
  metadata {
    name = local.name
    labels = {
      app = local.name
    }
  }

  spec {
    replicas = var.replicas_number

    selector {
      match_labels = {
        app = local.name
      }
    }

    template {
      metadata {
        labels = {
          app = local.name
        }
      }

      spec {
        container {
          image = format("gcr.io/%s/%s",var.project_id,local.name)
          name  = local.name
          port {
            container_port = var.container_port
          }

          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "k8s_service" {
  metadata {
    name = local.load_balancer_service_name
  }
  spec {
    selector = {
      app = kubernetes_deployment.k8s_deployment.metadata.0.labels.app
    }
    session_affinity = "ClientIP"
    port {
      port        = 80
      target_port = var.container_port
    }

    type = "LoadBalancer"
  }
}


