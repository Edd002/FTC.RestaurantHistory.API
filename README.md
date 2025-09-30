# FTC.RestaurantHistory.API

Este serviço é responsável por consumir eventos de pedidos (`Order`) e reservas (`Reservation`) de um tópico Apache Kafka e persistir um histórico de pedidos e reservas para os restaurantes.

## Diagrama de Arquitetura

O diagrama abaixo ilustra a arquitetura e o fluxo de dados do serviço:

![arch-diagram.png](arch-diagram.png)

## Como subir e limpar a aplicação

### Linux / WSL

1. **Instalar o `make` (caso não exista):**

```bash
sudo apt update
sudo apt install make -y
```

2. Subir a aplicação com Makefile:
```bash
make up
```

3. Limpar a aplicação
```bash
make clean
```

### Windows / PowerShell

1. Subir a aplicação:
```bash
.\make.ps1 -Action up
```

2. Limpar a aplicação
```bash
.\make.ps1 -Action clean
```

### Manualmente
Caso opte por subir a aplicação manualmente, sem scripts.

1. Criar a rede manualmente (se necessário):

```bash
docker network create ftc_restaurant_net
```

2. Subir o container manualmente:
```bash
docker compose --profile docker up --build
```

3. Limpar a aplicação e remover a rede
```bash
docker compose down -v
docker network rm ftc_restaurant_net
```