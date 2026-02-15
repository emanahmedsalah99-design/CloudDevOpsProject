def call() {
    echo "Pushing manifests to Git"
    sh "git add ../k8s-manifest.yaml"
    sh "git commit -m 'Update manifests with new image'"
    sh "git push origin main"
}
