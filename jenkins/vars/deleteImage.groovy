def call(String imageName) {
    echo "Deleting local Docker image: ${imageName}"
    sh "docker rmi ${imageName} || echo 'Image not found locally'"
}
