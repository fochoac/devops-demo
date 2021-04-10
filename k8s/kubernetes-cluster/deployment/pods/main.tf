provider "google" {
 
}
provider "kubernetes" {
  config_path = "./../../generate-cluster/kubeconfig-dev"
}

resource "kubernetes_namespace" "k8s_namespace" {
  metadata {
    name = "development"
  }
}

resource "kubernetes_deployment" "k8s_deployment" {
  metadata {
    name = "greeting-ws"
    labels = {
      app = "greeting-ws"
    }
  }

  spec {
    replicas = 2

    selector {
      match_labels = {
        app = "greeting-ws"
      }
    }

    template {
      metadata {
        labels = {
          app = "greeting-ws"
        }
      }

      spec {
        container {
          image = "gcr.io/devops-default/greeting-ws"
          name  = "greeting-ws"
          port {
            container_port = 8084
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
    name = "greeting-ws-service"
  }
  spec {
    selector = {
      app = kubernetes_deployment.k8s_deployment.metadata.0.labels.app
    }
    session_affinity = "ClientIP"
    port {
      port        = 80
      target_port = 8084
    }

    type = "LoadBalancer"
  }
}
