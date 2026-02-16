
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
🔵1- Cloned Flask application source code:
```bash 
git clone https://github.com/Ibrahim-Adel15/FinalProject.git
mv FinalProject/* docker/
rm -rf FinalProject
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-clone%20app.png?raw=true) 
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-mv%20app%20to%20dockerfile.png?raw=true) 

🔵2- Create a file named `Dockerfile`:
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
🔵3- Build & Run Image
``` bash
cd docker
docker build -t cloud-devops-app .
docker run -p 5000:5000 cloud-devops-app
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-Build.png?raw=true)
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-%20run.png?raw=true)
🔵4- Test 
``` bash
docker ps 
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-Test.png?raw=true)
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-Test.png?raw=true)
🔵5- Push Image to DockerHub
``` bash
docker login
docker tag cloud-devops-app emma175/cloud-devops-app:latest
docker push emma175/cloud-devops-app:latest
```
![Repository Cloned]()

🔵6- Commit
``` bash
git add .
git commit -m "Add Flask app and working Dockerfile"
git push
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/8-Commit.png?raw=true)

#### ☸️ Step 3: Kubernetes Orchestration
🟣1. Start Minikube Cluster
```bash
minikube start
```
Verify the cluster is running:
```bash
kubectl get nodes
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-start.png?raw=true)

🟣2. Create Kubernetes Namespace

Create namespace.yaml:
```bash
apiVersion: v1
kind: Namespace metadata:
name: ivolve
```
Apply it using:
```bash
kubectl apply -f namespace.yaml
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-namespace.png?raw=true)

🟣3. Docker Image Push to DockerHub

Login to DockerHub:
```bash
docker login
```
Tag the local image:
```bash
docker tag cloud-devops-app yourusername/cloud-devops-app:latest
```
Push the image to DockerHub:
```bash
docker push yourusername/cloud-devops-app:latest
```
📌 Replace yourusername with your DockerHub username.

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-%20push%20image.png?raw=true)

🟣4. Flask Deployment Manifest

Create flask-deployment.yaml:
```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: flask-deployment
  namespace: ivolve
spec:
  replicas: 2
  selector:
    matchLabels:
      app: flask-app
  template:
    metadata:
      labels:
        app: flask-app
    spec:
      containers:
      - name: flask-container
        image: emma175/cloud-devops-app:latest
        ports:
        - containerPort: 5000
```
📌 This deployment:
- Runs 2 replicas
- Uses the Docker image pushed to DockerHub
- Exposes port 5000 inside the container

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-Deployment.yaml.png?raw=true)

🟣5. Flask Service Manifest

Create flask-service.yaml:
```bash
apiVersion: v1
kind: Service
metadata:
  name: flask-service
  namespace: ivolve
spec:
  type: NodePort
  selector:
    app: flask-app
  ports:
    - port: 5000
      targetPort: 5000
      nodePort: 30007
```
📌 This service:
- Exposes the Flask app using NodePort
- Makes it accessible outside the cluster

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-servic.yaml.png?raw=true)

🟣6. Apply Kubernetes Manifests
```bash
kubectl apply -f flask-deployment.yaml
kubectl apply -f flask-service.yaml
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-apply%20both.png?raw=true)

🟣7. Verify & Test Application
Check pods:
```bash
kubectl get pods -n ivolve -l app=flask-app
```
Check services:
```bash
kubectl get svc -n ivolve
```
Access the application:
```bash
minikube service flask-service -n ivolve
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/8-test.png?raw=true)

🟣8. Commit and Push Changes
```bash
git add .
git commit -m "Deploy Flask app on Kubernetes using Minikube"
git push
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/9-commit.png?raw=true)

#### 🌍 Step 4: Infrastructure Provisioning with Terraform
🟢1.Download Terraform binary:
```bash
curl -LO https://releases.hashicorp.com/terraform/1.5.7/terraform_1.5.7_linux_amd64.zip
```
Install unzip and extract Terraform:
```bash
sudo yum install -y unzip
unzip terraform_1.5.7_linux_amd64.zip
```
Move Terraform binary to system path:
```bash
sudo mv terraform /usr/local/bin/
terraform -v
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-install%20terraform.png?raw=true)

🟢2.Create Terraform Modules Structure
```bash
mkdir -p modules/network
mkdir -p modules/server
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-Terraform%20Module.png?raw=true)

🟢3.Configure Root Module

Create main.tf in the Terraform root directory:
```bash
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
```
📌 This configuration:
- Uses local backend
- Applies modular architecture
- Separates network and server logic
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-main.tf.png?raw=true)


🟢4.Network Module Configuration

Create modules/network/main.tf:
```bash
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
```
📌 This module simulates:
- VPC
- Public subnets
- Internet Gateway
- Network ACL
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-network.png?raw=true)

🟢5.Server Module Configuration

Create modules/server/main.tf:
```bash
variable "server_count" {
  default = 2
}

output "server_ips" {
  value = ["10.0.1.10", "10.0.1.11"]
}
```
📌 This module simulates:
- Multiple servers
- Private IP addresse

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-server.png?raw=true)

🟢6. Initialize & Review & Apply Terraform
```bash
terraform init
terraform plan
terraform apply
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-%20%D8%AA%D9%86%D9%81%D9%8A%D8%B0%20terraform.png?raw=true)
🟢7.Commit 
```bash
git pull --redase origin main
git push origin main
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-commit.png?raw=true)

⚙️ Step 5: Configuration Management with Ansible
⚪1.Create Ansible Directory Structure
```bash
cd ~/CloudDevOpsProject/ansible
mkdir -p roles/common/tasks
mkdir -p roles/jenkins/tasks
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-Structure%20%20Roles.png?raw=true)

⚪2.Create Inventory File
Create inventory:
```bash
[local]
localhost ansible_connection=local
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-inventory.png?raw=true)

⚪3.Create Main Playbook

Create playbook.yml:
```bash
- name: Configure Local Server
  hosts: local
  become: yes
  roles:
    - common
    - Jenkins
  ```
📌 This playbook applies the common and Jenkins roles to the local host.
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-playbook.yml.png?raw=true)

⚪4.Configure Common Role

Create roles/common/tasks/main.yml:
```ba
- name: Install required packages
  dnf:
    name:
      - git
      - docker
      - java-17-openjdk
    state: present

- name: Start Docker service
  service:
    name: docker
    state: started
    enabled: yes
  ```
📌 This role ensures:
Git, Docker, and Java are installed
Docker service is running and enabled

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-common.png?raw=true)

⚪5.Configure Jenkins Role

Create roles/jenkins/tasks/main.yml:
```bash
- name: Add Jenkins repo
  get_url:
    url: https://pkg.jenkins.io/redhat-stable/jenkins.repo
    dest: /etc/yum.repos.d/jenkins.repo

- name: Import Jenkins key
  rpm_key:
    state: present
    key: https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key

- name: Install Jenkins
  dnf:
    name: jenkins
    state: present

- name: Start Jenkins
  service:
    name: jenkins
    state: started
    enabled: yes
```
📌 This role ensures Jenkins is:
Installed from official repository
Started and enabled to run at boot

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-jenkins.png?raw=true)

⚪6.Run the Playbook
```bash
ansible-playbook -i inventory playbook.yml
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-run%20ansible.png?raw=true)
⚪7.Access Jenkins
Open Jenkins in your browser:
```bash
http://localhost:10000
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-%20test.png?raw=true)

#### 🔁 Step 6: Continuous Integration with Jenkins

🔵1.Create Shared Library Groovy Scripts
jenkins/vars/buildImage.groovy

```bash
def call(String imageName) {
    echo "Building Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-buildImage.groovy.png?raw=true)

🔵2.jenkins/vars/scanImage.groovy
```bash
def call(String imageName) {
    echo "Scanning Docker image: ${imageName}"
    sh "docker scan ${imageName} || echo 'Scan completed'"
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-scanImage.groovy.png?raw=true)

🔵3.jenkins/vars/pushImage.groovy
```bash
def call(String imageName) {
    echo "Pushing Docker image: ${imageName}"
    sh "docker push ${imageName}"
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-pushImage.groovy.png?raw=true)

🔵4.jenkins/vars/deleteImage.groovy
```bash
def call(String imageName) {
    echo "Deleting local Docker image: ${imageName}"
    sh "docker rmi ${imageName} || echo 'Image not found locally'"
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-deleteImage.groovy.png?raw=true)

🔵5.jenkins/vars/updateManifests.groovy
```bash
def call(String imageName) {
    echo "Updating manifests with image: ${imageName}"
    sh "sed -i 's|IMAGE_NAME|${imageName}|g' ../k8s-manifest.yaml"
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-updateManifests.groovy.png?raw=true)
🔵6.jenkins/vars/pushManifests.groovy
```bash
def call() {
    echo "Pushing manifests to Git"
    sh "git add ../k8s-manifest.yaml"
    sh "git commit -m 'Update manifests with new image'"
    sh "git push origin main"
}
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-pushManifests.groovy.png?raw=true)

🔵7.Create Jenkinsfile Using Shared Library
jenkins/Jenkinsfile
```bash
@Library('my-shared-library') _

pipeline {
    agent any

    environment {
        IMAGE_NAME = "myapp:${env.BUILD_NUMBER}"
    }

    stages {
        stage('Build Image') {
            steps {
                buildImage(IMAGE_NAME)
            }
        }
        stage('Scan Image') {
            steps {
                scanImage(IMAGE_NAME)
            }
        }
        stage('Push Image') {
            steps {
                pushImage(IMAGE_NAME)
            }
        }
        stage('Delete Image Locally') {
            steps {
                deleteImage(IMAGE_NAME)
            }
        }
        stage('Update Manifests') {
            steps {
                updateManifests(IMAGE_NAME)
            }
        }
        stage('Push Manifests') {
            steps {
                pushManifests()
            }
        }
    }
}

```
📌 This Jenkinsfile stages:
-Build Docker image
- Scan for vulnerabilities
- Push to Docker registry
- Delete local image to save space
- Update Kubernetes manifests with the new image
- Push updated manifests to GitHub

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-Jenkinsfile.png?raw=true)

#### 🚀 Step 7: Continuous Deployment with ArgoCD
🔴 1.Create ArgoCD Application Manifest

Navigate to the ArgoCD folder:
```bash
cd ~/CloudDevOpsProject/argocd
```
Create app.yaml:

```bash
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
  name: clouddevops-app        
  namespace: argocd
spec:
  project: default
  source:
    repoURL: 'https://github.com/emanahmedsalah99-design/CloudDevOpsProject.git'  
    targetRevision: main      
    path: 'kubernetes'        
  destination:
    server: 'https://kubernetes.default.svc'  
    namespace: default
  syncPolicy:
    automated:
      prune: true
      selfHeal: true
```
📌 This manifest tells ArgoCD to:
- Track the Git repository
- Deploy resources under the kubernetes folder
- Sync automatically (with prune and self-heal enabled)

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-%20app.yaml.png?raw=true)

🔴 2.Verify Cluster Resources

Check nodes:

``bash
kubectl get nodes
```
Check pods and services in the default namespace:
```bash
kubectl get pods -n default
kubectl get svc -n default
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-test.png?raw=true)

🔴 3.Verify ArgoCD Application

Check ArgoCD applications:
``bash 
kubectl get applications -n argocd
```
📌 If the application shows Healthy / Synced, it means ArgoCD successfully applied all Kubernetes manifests from GitHub.

![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/3-test.png?raw=true)

🔴 4.Access the Application
Get the service URL for the deployed app:
``bash
minikube service node-app-service --url
```
![Repository Cloned]()



