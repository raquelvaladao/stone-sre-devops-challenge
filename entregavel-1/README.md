## Github vs Gitlab?
- A aplicação também está nesse repositório com sua respectiva pipeline no Github Actions. 
- Mas acabei fazendo também no Gitlab: [gitlab.com/raquelvaladao/stone](https://gitlab.com/raquelvaladao/stone)

## Containerização da aplicação e Docker compose
### Dockerfile
- Presente.

### Docker-compose
- API e PostgreSQL

## Pipeline
### Stage "test"
- É utilizado o Sonar para submeter os testes unitários e de integração ao quality gate. Temos 2 variáveis setadas que serão usadas na pipeline.
Uma é pro servidor do sonar, outro um token gerado no servidor para analisar o projeto em questão.
```bash
export SONAR_HOST_URL=http://${DOMAIN}:{cluster_port}
export SONAR_TOKEN=sqp_xxxxxx
```

## Docker-compose
- Presente
