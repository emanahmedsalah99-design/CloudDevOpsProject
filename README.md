## CloudDevOpsProject
End-to-end Cloud DevOps Project demonstrating modern DevOps practices using
Docker, Kubernetes, Terraform, Ansible, Jenkins, and ArgoCD.

---
## Project Overview
This project showcases a complete DevOps pipeline starting from application containerization to continuous deployment on Kubernetes using GitOps principles.
# Tools & Technologies
- GitHub
- Docker
- Kubernetes (Minikube)
- Terraform
- Ansible
- Jenkins
- ArgoCD

---
# Project Structure
CloudDevOpsProject/
-│
-├── docker/          # Flask app & Dockerfile
-├── kubernetes/      # Kubernetes manifests
-├── terraform/       # Infrastructure as Code
-├── ansible/         # Configuration management
-├── jenkins/         # Jenkins pipeline
-├── argocd/          # ArgoCD application
-└── README.md

___
## Steps & Commands 
🧩 Step 1: GitHub Repository Setup
Created GitHub repository: CloudDevOpsProject
Initialized with README
Created structured directories for each DevOps stage
```bash 
git clone https://github.com/emanahmedsalah99-design/CloudDevOpsProject.git
cd CloudDevOpsProject
```
```bash 
mkdir docker kubernetes terraform ansible jenkins argocd
touch docker/Dockerfile
touch kubernetes/deployment.yaml
touch kubernetes/service.yaml
touch terraform/main.ft
touch ansible/playbook.yaml
touch jenkins/jenkinsfile
touch argocd/app.yaml
```
```bash 
git add .
git commit -m "Initial project structure"
git push
```
