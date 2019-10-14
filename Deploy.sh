docker build -t majidshaikh16/rabbit-mq-worker-apis:latest -t majidshaikh16/rabbit-mq-worker-apis:$SHA -f ./SimpleWeb/Dockerfile ./SimpleWeb/
docker build -t majidshaikh16/rabbit-mq-event-apis:latest -t majidshaikh16/rabbit-mq-event-apis:$SHA -f ./msgq/Dockerfile ./msgq/

docker push majidshaikh16/rabbit-mq-worker-apis:latest
docker push majidshaikh16/rabbit-mq-event-apis:latest

docker push majidshaikh16/rabbit-mq-worker-apis:$SHA
docker push majidshaikh16/rabbit-mq-event-apis:$SHA

kubectl apply -f k8s

kubectl set image deployments/worker-api-deployment worker-api=majidshaikh16/rabbit-mq-worker-apis:$SHA
kubectl set image deployments/event-api-deployement event-api=majidshaikh16/rabbit-mq-event-apis:$SHA