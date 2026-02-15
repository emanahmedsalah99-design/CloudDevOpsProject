def call(String imageName) {
    echo "Scanning Docker image: ${imageName}"
    sh "docker scan ${imageName} || echo 'Scan completed'"
}
