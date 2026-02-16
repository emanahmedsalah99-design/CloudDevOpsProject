
## CloudDevOpsProject
End-to-end Cloud DevOps Project demonstrating modern DevOps practices using
Docker, Kubernetes, Terraform, Ansible, Jenkins, and ArgoCD.

---
## Project Overview
This project showcases a complete DevOps pipeline starting from application containerization to continuous deployment on Kubernetes using GitOps principles.

---
## Tools & Technologies
- GitHub
- Docker
- Kubernetes (Minikube)
- Terraform
- Ansible
- Jenkins
- ArgoCD
---
## Project Structure
CloudDevOpsProject/
│

├── docker/           # Flask app & Dockerfile

├── kubernetes/       # Kubernetes manifests

├── terraform/        # Infrastructure as Code

├── ansible/          # Configuration management

├── jenkins/          # Jenkins pipeline

├── argocd/          # ArgoCD application

└── README.md

---
## Steps & Commands 
#### 🧩 Step 1: GitHub Repository Setup
Created GitHub repository: CloudDevOpsProject
Initialized with README
Created structured directories for each DevOps stage
```bash 
git clone https://github.com/emanahmedsalah99-design/CloudDevOpsProject.git
cd CloudDevOpsProject
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-Clone.png?raw=true)
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
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-Structure.png?raw=true)
```bash 
git add .
git commit -m "Initial project structure"
git push
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-Structure.png?raw=true)

### 🐳 Step 2: Containerization with Docker
Source Code

Cloned Flask application source code:
