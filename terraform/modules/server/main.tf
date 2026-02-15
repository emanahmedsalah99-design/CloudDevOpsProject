variable "server_count" {
  default = 2
}

output "server_ips" {
  value = ["10.0.1.10", "10.0.1.11"]
}
