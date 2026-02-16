
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
- Cloned Flask application source code:
```bash 
git clone https://github.com/Ibrahim-Adel15/FinalProject.git
mv FinalProject/* docker/
rm -rf FinalProject
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-clone%20app.png?raw=true) 
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/2-mv%20app%20to%20dockerfile.png?raw=true) 

- Create a file named `Dockerfile`:
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
- Build & Run Image
``` bash
cd docker
docker build -t cloud-devops-app .
docker run -p 5000:5000 cloud-devops-app
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/4-Build.png?raw=true)
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/5-%20run.png?raw=true)
- Test 
``` bash

docker ps 
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/6-Test.png?raw=true)
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/7-Test.png?raw=true)
- Push Image to DockerHub
``` bash
docker login
docker tag cloud-devops-app yourusername/cloud-devops-app:latest
docker push yourusername/cloud-devops-app:latest
```
![Repository Cloned]()

- commit
``` bash
git add .
git commit -m "Add Flask app and working Dockerfile"
git push
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/8-Commit.png?raw=true)

#### ☸️ Step 3: Kubernetes Orchestration
1. Start Minikube Cluster
```bash
minikube start
```
Verify the cluster is running:
```bash
kubectl get nodes
```
![Repository Cloned](https://github.com/emanahmedsalah99-design/CloudDevOpsProject/blob/main/Sreenshots/1-start.png?raw=true)

2. Create Kubernetes Namespace

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
![Repository Cloned]()

3. Flask Deployment Manifest
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
Runs 2 replicas
Uses the Docker image pushed to DockerHub
Exposes port 5000 inside the container

![Repository Cloned]()

4. Flask Service Manifest
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
Exposes the Flask app using NodePort
Makes it accessible outside the cluster

![Repository Cloned]()

5. Apply Kubernetes Manifests
```bash
kubectl apply -f flask-deployment.yaml
kubectl apply -f flask-service.yaml
```
![Repository Cloned]()
6. Verify Pods and Services
Check running pods:
```bash
kubectl get pods -n ivolve -l app=flask-app
```
Check service details:
```bash
kubectl get svc -n ivolve
```
![Repository Cloned]()

9. Docker Image Push to DockerHub
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

![Repository Cloned]()


