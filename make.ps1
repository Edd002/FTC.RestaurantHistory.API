param(
    [string]$Action = "up",
    [string]$Profile = "docker"
)

$networkName = "ftc_restaurant_net"
$env:LOGGING_LEVEL_ROOT = "INFO"
$env:LOGGING_LEVEL_ORG_HIBERNATE_SQL = "WARN"
$env:LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL_BASICBINDER = "WARN"
$env:LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB = "INFO"
$env:SPRING_JPA_SHOW_SQL = "false"

switch ($Action) {
    "up" {
        $exists = docker network ls --format '{{.Name}}' | Where-Object { $_ -eq $networkName }
        if (-not $exists) {
            Write-Output ">> Criando network $networkName"
            docker network create $networkName
        } else {
            Write-Output ">> Network $networkName já existe"
        }
        docker compose --profile $Profile up --build
    }
    "down" {
        docker compose down -v
    }
    "clean" {
        docker compose down -v
        docker network rm $networkName -f 2>$null
    }
}
