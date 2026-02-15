terraform {
  required_version = ">= 1.5.0"
  backend "local" {
    path = "terraform.tfstate"
  }
}

module "network" {
  source = "./modules/network"
}

module "server" {
  source = "./modules/server"
}
