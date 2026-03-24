PROJETO FINAL 
Plataforma de Monitoramento Inteligente para Agricultura de Precisão (AgroTech System)

1. OBJETIVO DO PROJETO
Desenvolver uma aplicação fullstack capaz de:
•	Simular, coletar e armazenar dados de sensores agrícolas 
•	Processar dados históricos e em fluxo contínuo 
•	Detectar condições críticas através de regras de negócio 
•	Gerar alertas e apoiar tomada de decisão 
•	Expor dados via API REST segura 
•	Apresentar informações em dashboards interativos 

2. CONTEXTO DO PROBLEMA
A agricultura moderna exige monitoramento contínuo de variáveis ambientais para:
•	Reduzir perdas 
•	Otimizar irrigação 
•	Evitar pragas e doenças 
•	Aumentar produtividade 
O sistema deve simular esse cenário e oferecer suporte à decisão baseado em dados.

3. ESCOPO TÉCNICO OBRIGATÓRIO
3.1 Backend
•	Java + Spring Boot 
•	API RESTful 
•	Arquitetura em camadas (mínimo) ou hexagonal (diferencial) 
•	Persistência com banco relacional 

3.2 Frontend
•	Angular 
•	Arquitetura modular 
•	Consumo de API REST 

3.3 Segurança
•	Autenticação com JWT 
•	Autorização baseada em roles 

4. SIMULAÇÃO DE SENSORES (OBRIGATÓRIO)
O sistema deve simular sensores para cada área cadastrada:
Tipos mínimos:
•	Temperatura (°C) 
•	Umidade do solo (%) 
•	Umidade do ar (%) 
•	Luminosidade (lux) 
Requisitos:
•	Geração automática via scheduler 
•	Intervalo configurável 
•	Persistência de todas as leituras 

5. MODELAGEM DE DOMÍNIO (OBRIGATÓRIA)
O sistema deve conter, no mínimo, as seguintes entidades:
•	Usuário 
•	Área (talhão) 
•	Sensor 
•	LeituraSensor 
•	Regra 
•	Alerta 

6. FUNCIONALIDADES OBRIGATÓRIAS

6.1 Autenticação e Autorização
•	Cadastro e login de usuários 
•	Perfis: 
o	ADMIN 
o	OPERADOR 
•	Proteção de endpoints 

  6.2 Gestão de Áreas
•	CRUD de áreas agrícolas 
•	Associação com sensores 

6.3 Gestão de Sensores
•	Cadastro de sensores por área 
•	Tipos de sensores 

6.4 Ingestão de Leituras
•	Endpoint para registro de leitura 
•	Validação dos dados 

6.5 Visualização de Dados
•	Listagem de leituras 
•	Filtro por: 
o	período 
o	sensor 
o	área 

6.6 Sistema de Alertas (CORE)
O sistema deve gerar alertas automaticamente com base em regras.
Exemplos:
•	Temperatura > 35°C 
•	Umidade do solo < 30% 
•	Luminosidade < 200 lux 

6.7 Gestão de Alertas
•	Listagem de alertas 
•	Status: 
o	ATIVO 
o	RESOLVIDO 
•	Histórico 

6.8 Dashboard
•	Indicadores: 
o	média por sensor 
o	leituras recentes 
o	alertas ativos 

7. REQUISITOS AVANÇADOS (OBRIGATÓRIO ESCOLHER 2)
Os grupos devem implementar pelo menos 2:

A. Motor de Regras Dinâmico
•	Regras cadastradas no banco 
•	Interpretação em tempo de execução 

B. WebSocket
•	Atualização em tempo real 

C. Upload de Dados
•	Importação de CSV 

D. Estratégias de Processamento
•	Uso de Strategy Pattern 

E. Cache
•	Otimização de consultas 

8. QUALIDADE E BOAS PRÁTICAS
Obrigatório:
•	Separação clara de camadas 
•	Validação de dados 
•	Tratamento global de exceções 
•	Logs básicos 

9. REQUISITOS DE ARQUITETURA
O sistema deve demonstrar:
•	Baixo acoplamento 
•	Alta coesão 
•	Separação de responsabilidades 
•	Facilidade de manutenção 

10. ENTREGÁVEIS

Código
•	Repositório Git organizado 
•	Commits significativos 

Documentação
•	README contendo: 
o	descrição do sistema 
o	arquitetura 
o	instruções de execução 
o	endpoints 

Apresentação Final
O grupo deverá apresentar:
•	Problema proposto
•	Problema resolvido 
•	Arquitetura adotada 
•	Decisões técnicas 
•	Demonstração do sistema 

11. CRITÉRIOS DE AVALIAÇÃO
Critério	Peso
Modelagem de domínio	5
Implementação backend	5
Arquitetura	5
Frontend	5
Qualidade do código	4
Funcionalidades avançadas	4
Apresentação	3

12. DESAFIOS ESPERADOS
O projeto exige que o aluno saiba:
•	Modelar dados complexos 
•	Trabalhar com dados temporais 
•	Implementar regras de negócio 
•	Pensar em arquitetura 
•	Lidar com volume de dados 

13. RESTRIÇÕES
•	Não simplificar o domínio (ex: remover sensores ou regras) 

14. DIFERENCIAL (ALTO NÍVEL)
Projetos de destaque terão:
•	Código limpo e bem estruturado 
•	Arquitetura clara 
•	Boa experiência de uso 
•	Soluções elegantes para regras de negócio

Data final – entrega e apresentação: 13/04/2026 
