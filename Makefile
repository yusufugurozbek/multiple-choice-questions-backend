docker-compose-up:
	docker-compose up -d

docker-compose-down:
	docker-compose down

docker-restart-with-changes:
	docker-compose down
	docker rmi mcq-backend
	docker-compose up -d
