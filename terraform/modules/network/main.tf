variable "vpc_cidr" {
  default = "10.0.0.0/16"
}

output "vpc_id" {
  value = "vpc-local"
}

output "public_subnet_ids" {
  value = ["subnet-1", "subnet-2"]
}

output "igw_id" {
  value = "igw-local"
}

output "nacl_id" {
  value = "nacl-local"
}
