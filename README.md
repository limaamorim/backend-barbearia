# Barbearia API - Backend

API RESTful para sistema de gerenciamento de barbearia.

## 🚀 Tecnologias

- **Node.js** - Runtime JavaScript
- **Express** - Framework web
- **Prisma** - ORM para banco de dados
- **PostgreSQL** - Banco de dados relacional
- **CORS** - Compartilhamento de recursos entre origens

## 📋 Pré-requisitos

- Node.js (v18+)
- PostgreSQL (v14+)

## 🔧 Instalação

```
bash
# Instalar dependências
npm install

# Configurar variáveis de ambiente
# Crie um arquivo .env com a seguinte variável:
DATABASE_URL="postgresql://usuario:senha@localhost:5432/barbearia"
PORT=3333
```

## 🗄️ Configuração do Banco de Dados

```
bash
# Gerar o cliente Prisma
npx prisma generate

# Executar migrações
npx prisma migrate dev

# (Opcional) Visualizar banco de dados
npx prisma studio
```

## ▶️ Executando o Projeto

```
bash
# Modo desenvolvimento
npm run dev

# Modo produção
npm start
```

O servidor estará disponível em: `http://localhost:3333`

##  API Endpoints

### Saúde
- `GET /health` - Verifica se o servidor está rodando

### Serviços
- `GET /servicos` - Listar todos os serviços
- `POST /servicos` - Criar novo serviço
- `GET /servicos/:id` - Obter serviço por ID
- `PUT /servicos/:id` - Atualizar serviço
- `DELETE /servicos/:id` - Deletar serviço

### Agendamentos
- `GET /agendamentos` - Listar todos os agendamentos
- `POST /agendamentos` - Criar novo agendamento
- `GET /agendamentos/:id` - Obter agendamento por ID
- `PUT /agendamentos/:id` - Atualizar agendamento
- `DELETE /agendamentos/:id` - Cancelar agendamento

### Dashboard
- `GET /dashboard/estatisticas` - Obter estatísticas
- `GET /dashboard/agendamentos-hoje` - Agendamentos do dia

## 📁 Estrutura de Arquivos

```
backend/
├── controller/       # Controladores das rotas
├── prisma/           # Schema e migrações do banco
├── routes/           # Definição das rotas
├── services/         # Lógica de negócio
├── server.js         # Ponto de entrada
└── package.json      # Dependências do projeto
```

## 📝 Modelos do Banco de Dados

### Servico
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Int | ID único |
| nome | String | Nome do serviço |
| preco | Decimal | Preço do serviço |
| duracaoMinutos | Int | Duração em minutos |

### Agendamento
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | Int | ID único |
| nomeCliente | String | Nome do cliente |
| telefoneCliente | String | Telefone do cliente |
| data | DateTime | Data do agendamento |
| horaInicio | Time | Hora de início |
| horaFim | Time | Hora de término |
| status | Enum | Status do agendamento |
| servicoId | Int | ID do serviço |

### StatusAgendamento
- `AGENDADO` - Agendamento realizado
- `CONFIRMADO` - Agendamento confirmado
- `CANCELADO` - Agendamento cancelado

## 🔗 Integração com Frontend

O frontend deve fazer requisições para:
- Desenvolvimento: `http://localhost:3333`
- Produção: Configurar URL do servidor de produção
