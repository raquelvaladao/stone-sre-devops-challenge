## IaC e Cluster
-   Foi utilizado um cluster zonal na GCP
-   O Makefile ainda está sendo construido, pois ainda não implementei os comandos para um cluster local


## Manifestos
-   Os manifestos estão na pasta /manifests. Eu incluí manifestos do Sonar.
- Assim que eu implementar a pipe de CD provavelmente os manifestos da aplicação estarão no mesmo repo da aplicação. Mas isso ainda é um TO-DO.

## Makefile
-   O Makefile ainda está sendo construido, pois ainda não implementei os comandos para um cluster local. Mas se vc tiver acesso com kubectl ao seu cluster em prod (cloud), vc pode rodar o seguinte comando pra rodar o banco, sonar e app:
```bash
make prod
```

## Aplicação
- Ainda farei alguns ajustes (tipo implementar o Actuator pra melhorar o Readiness Probe ou incluir um /health, pois estou usando um endpoint qualquer pra saúde do app no load balancer do GKE).

## Postgres
- Exposto apenas para aplicação via ClusterIP
- É um Statefulset com seu PVC dinâmico associado