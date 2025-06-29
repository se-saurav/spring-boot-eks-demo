# Learn to deploy Spring Boot app to EKS cluster

**Checkout code**

```
git checkout https://github.com/se-saurav/spring-boot-eks-demo.git
```

**Build Spring Boot jar and run in local cluster**

Open the project in IntelliJ IDEA then do Gradle -> build -> build this will create the JAR in build/libs folder.


```
docker build --tag=demo:latest .

docker run --name "demo" --net bridge -p8080:8080 demo:latest
```
Run it in K8s cluster-
```
docker tag demo:latest demo:test

kubectl apply -f local.yaml
```

**Setup AWS & CLI**

Login to your AWS account and setup "AWS Access Key ID" and "AWS Secret Access Key" under "Security credentials".

Open Terminal and run below command-

```
aws configure
```

**Create EKS cluster**

We need to install eksctl to manage EKS cluster from command line.
```
brew tap weaveworks/tap

brew install weaveworks/tap/eksctl
```
Now run below command to create EKS cluster-
```
eksctl create cluster --name demo-cluster --version 1.33 --nodes=1 --node-type=t2.small --region us-east-1
#eksctl delete cluster --name demo-cluster
```
Once the cluster is ready then run below commands-

```
aws eks --region us-east-1 update-kubeconfig --name demo-cluster

kubectl apply -f eks.yaml

kubectl get svc
kubectl get pod
kubectl get deployment
```

**Access your API on AWS**

Once you run the below command then it will show EXTERNAL_IP which can be used to access your microservice API.
```
kubectl get svc
```