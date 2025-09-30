NETWORK_NAME=ftc_restaurant_net
PROFILE=docker

.PHONY: up down clean network

up: network
	export LOGGING_LEVEL_ROOT=INFO
	export LOGGING_LEVEL_ORG_HIBERNATE_SQL=WARN
	export LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL_BASICBINDER=WARN
	export LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB=INFO
	export SPRING_JPA_SHOW_SQL=false
	docker compose --profile $(PROFILE) up --build

down:
	docker compose down

clean: down
	-@docker network rm $(NETWORK_NAME) 2>/dev/null || true

network:
	@if ! docker network inspect $(NETWORK_NAME) >/dev/null 2>&1; then \
		echo ">> Criando network $(NETWORK_NAME)"; \
		docker network create $(NETWORK_NAME); \
	else \
		echo ">> Network $(NETWORK_NAME) já existe"; \
	fi
