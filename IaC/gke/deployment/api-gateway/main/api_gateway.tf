provider "google" {
  project = var.project_id
  region = var.region
}

provider "google-beta" {
  project = var.project_id
  region = var.region
}

provider "kubernetes" {
  config_path = format("./../../generate-cluster/kubeconfig-%s",var.env_name)
}

locals {
  api_config_id_prefix     = format("api-%s",var.env_name)
  api_gateway_container_id = format("api-gw-%s",var.env_name)
  gateway_id               = format("gw-%s",var.env_name)
  description_api_gateway  = format("Api Gateway for %s env", var.env_name)
  swagger_spec_path        = format("%s-%s.yaml",var.swagger_spec_name,var.env_name)
}

resource "google_api_gateway_api" "api_gw" {
  provider     = google-beta
  api_id       = local.api_gateway_container_id
  display_name = local.description_api_gateway

}

resource "google_api_gateway_api_config" "api_cfg" {
  provider      = google-beta
  api           = google_api_gateway_api.api_gw.api_id
  api_config_id_prefix = local.api_config_id_prefix
  display_name  = local.description_api_gateway

  openapi_documents {
    document {
      path     = local.swagger_spec_path
      contents = filebase64("${local.swagger_spec_path}")
    }
  }
}

resource "google_api_gateway_gateway" "gw" {
  provider   = google-beta
  region     = var.region

  api_config   = google_api_gateway_api_config.api_cfg.id

  gateway_id   = local.gateway_id
  display_name = local.description_api_gateway

  depends_on = [google_api_gateway_api_config.api_cfg]
}
