deploy: backend_stop_service frontend_stop_service remove_preexisting_containers backend_build backend_start_service frontend_build frontend_start_service push_docker_containers

remove_preexisting_containers:
	-docker rm backend
	-docker rm frontend

backend_stop_service:
	-docker stop backend

backend_build:
	mvn -B -DskipTests clean package spring-boot:repackage
	-docker build -t eposkk/backend .

backend_start_service:
	@docker run -d --name backend -p 8001:8001 eposkk/backend

frontend_build:
	-docker build -t eposkk/frontend ./src/frontend

frontend_start_service:
	-docker run -d --name frontend -p 80:80 eposkk/frontend

frontend_stop_service:
	-docker stop frontend
	
push_docker_containers:
	-docker push eposkk/frontend:latest
	-docker push eposkk/backend:latest
