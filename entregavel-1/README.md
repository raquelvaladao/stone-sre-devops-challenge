## Containerização da aplicação e Docker compose
### Dockerfile
- Verificando possibilidades de otimização do Dockerfile.

### Docker-compose
- API e PostgreSQL

## Pipeline
### Stage "test"
- É utilizado o Sonar para submeter os testes unitários e de integração ao quality gate. Temos 2 variáveis setadas que serão usadas na pipeline.
Uma é pro servidor do sonar, outro um token gerado no servidor para analisar o projeto em questão.
```bash
export SONAR_HOST_URL=http://${DOMAIN}:9000
export SONAR_TOKEN=sqp_xxxxxx
```
