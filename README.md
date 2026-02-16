
### CloudDevOpsProject
End-to-end Cloud DevOps Project demonstrating modern DevOps practices using
Docker, Kubernetes, Terraform, Ansible, Jenkins, and ArgoCD.

---
### Project Overview
This project showcases a complete DevOps pipeline starting from application containerization to continuous deployment on Kubernetes using GitOps principles.

---
### Tools & Technologies
- GitHub
- Docker
- Kubernetes (Minikube)
- Terraform
- Ansible
- Jenkins
- ArgoCD
---
### Project Structure
CloudDevOpsProject/

├── docker/           # Flask app & Dockerfile

├── kubernetes/       # Kubernetes manifests

├── terraform/        # Infrastructure as Code

├── ansible/          # Configuration management

├── jenkins/          # Jenkins pipeline

├── argocd/          # ArgoCD application

└── README.md

---
### Steps & Commands 
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

#### 🐳 Step 2: Containerization with Docker
Cloned Flask application source code:
```bash 
git clone https://github.com/Ibrahim-Adel15/FinalProject.git
mv FinalProject/* docker/
rm -rf FinalProject
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-clone%20app.png?raw=true) 
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-mv%20app%20to%20dockerfile.png?raw=true) 

Create a file named `Dockerfile`:
```bash 
FROM Python:3.9-slim
WORKDIR /app 
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .      
EXPOSE 5000
CMD ["Python" ,"app.py"]
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-Dockerfile.png?raw=true) 
Build & Run Image
``` bash
cd docker
docker build -t cloud-devops-app .
docker run -p 5000:5000 cloud-devops-app
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-Build.png?raw=true)
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-%20run.png?raw=true)
Test 
``` bash
```
![Repository Cloned]()
Push Image to DockerHub
``` bash
docker login
docker tag cloud-devops-app yourusername/cloud-devops-app:latest
docker push yourusername/cloud-devops-app:latest
```
![Repository Cloned]()

