def call(String imageName) {
    echo "Updating manifests with image: ${imageName}"
    sh "sed -i 's|IMAGE_NAME|${imageName}|g' ../k8s-manifest.yaml"
}
