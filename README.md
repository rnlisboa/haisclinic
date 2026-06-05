# HaisClinic

HaisClinic é uma aplicação web para cadastro de pacientes e profissionais, gerenciamento de prontuários clínicos, registro de evoluções clínicas e geração de notificações assíncronas.

O projeto é dividido em dois módulos principais:

- `haisclinic-app-backend`: API REST em Spring Boot.
- `haisclinic-app-frontend`: aplicação React com TypeScript e Vite.

Também há um `docker-compose.yml` na raiz para subir frontend, backend e banco PostgreSQL juntos.

## Requisitos

Para execução com Docker:

- Docker
- Docker Compose

Para execução local sem Docker:

- Java 21
- Maven ou Maven Wrapper
- Node.js
- npm
- PostgreSQL

## Instruções De Execução

### Executar Tudo Com Docker

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Para executar em background:

```bash
docker compose up --build -d
```

Serviços disponíveis:

- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8080`
- PostgreSQL: `localhost:5432`

Credenciais padrão do banco no Docker:

- Database: `haisclinic`
- User: `haisclinic`
- Password: `haisclinic`

### Executar Apenas O Frontend Localmente

Entre na pasta do frontend:

```bash
cd haisclinic-app-frontend
```

Instale as dependências:

```bash
npm install
```

Crie ou ajuste o arquivo `.env`:

```env
VITE_CORE_API_URL=http://localhost:8080
```

Execute em modo desenvolvimento:

```bash
npm run dev
```

Comandos úteis:

```bash
npm run lint
npm run build
npm run preview
```

### Executar Apenas O Backend Localmente

Entre na pasta do backend:

```bash
cd haisclinic-app-backend
```

Execute a aplicação:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Variáveis de ambiente importantes:

```env
SERVER_PORT=8080
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/haisclinic
SPRING_DATASOURCE_USERNAME=haisclinic
SPRING_DATASOURCE_PASSWORD=haisclinic
SPRING_JPA_HIBERNATE_DDL_AUTO=update
APP_STORAGE_IMAGES_DIR=uploads/images
```

## Arquitetura Da Solução

```text
haisclinic/
  docker-compose.yml
  haisclinic-app-backend/
  haisclinic-app-frontend/
  docs/
```

A aplicação segue uma separação clara entre frontend e backend:

- O frontend consome a API REST usando Axios.
- O backend expõe endpoints REST para pacientes, profissionais, prontuários, evoluções e notificações.
- O PostgreSQL armazena os dados da aplicação.
- Imagens enviadas para pacientes e profissionais são armazenadas em volume configurado pelo backend.
- Notificações são geradas de forma assíncrona no backend com Spring Events e `@Async`.

## Arquitetura Do Backend

O backend utiliza Spring Boot com uma organização em camadas, seguindo o padrão já implementado no projeto:

```text
src/main/java/com/haisclinic/haisclinic_app_backend/
  Application/
    Events/
    Listeners/
    UseCases/
    Validators/
  Controllers/
  Domain/
    Entity/
    Repository/
  DTO/
  Exceptions/
  Infrastructure/
    Persistence/
  Mapper/
```

### Camadas

**Controllers**

Recebem as requisições HTTP, chamam os casos de uso e retornam `ResponseEntity`.

Exemplos:

- `PacienteController`
- `ProfissionalController`
- `ProntuarioController`
- `PacienteEvolucaoController`
- `NotificacaoController`

**Application / UseCases**

Concentram as regras de aplicação.

Exemplos:

- criar paciente
- buscar paciente
- criar prontuário
- criar evolução clínica
- listar notificações
- marcar notificação como lida

**Domain / Entity**

Contém as entidades persistidas:

- `Paciente`
- `Profissional`
- `Prontuario`
- `PacienteEvolucao`
- `Notificacao`

As entidades usam JPA, Lombok e métodos estáticos de criação, mantendo o padrão do projeto.

**Domain / Repository**

Define contratos de persistência sem depender diretamente do Spring Data.

**Infrastructure / Persistence**

Implementa os repositórios usando Spring Data JPA.

**DTO**

Define records de entrada e saída para a API.

**Mapper**

Converte entidades de domínio para responses e requests para entidades.

### Notificações Assíncronas

Ao registrar uma nova evolução clínica, o fluxo é:

```text
CriarPacienteEvolucaoUseCase
  -> salva PacienteEvolucao
  -> publica EvolucaoCriadaEvent
  -> EvolucaoCriadaListener recebe o evento com @EventListener
  -> processamento assíncrono com @Async
  -> cria Notificacao
  -> salva Notificacao
```

Esse fluxo garante que a criação da evolução clínica não seja bloqueada pela geração da notificação. Caso ocorra erro ao gerar a notificação, a evolução continua salva normalmente.

### Banco De Dados

O projeto usa PostgreSQL.

Atualmente o backend está configurado com:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Também existe uma migration SQL para a tabela de notificações em:

```text
haisclinic-app-backend/src/main/resources/db/migration/V1__create_notificacoes.sql
```

## Arquitetura Do Frontend

O frontend utiliza React, TypeScript, Vite, Tailwind CSS, Radix UI, Axios, React Icons e React Router DOM.

Estrutura principal:

```text
src/
  assets/
  components/
  dtos/
  hooks/
  pages/
  routes/
  services/
  utils/
```

### Pages

As páginas representam telas completas da aplicação.

Exemplos:

- `pages/register/register.tsx`
- `pages/pacientes/listaPacientes.tsx`
- `pages/paciente/PacienteDetalhe.tsx`

### Components

Componentes visuais reutilizáveis ficam em `src/components/`.

Exemplos:

- `Header`
- `Button`
- `Form`
- `Dialog`
- `Loading`
- `Toast`
- `Paciente`
- `Notificacoes`

Os componentes específicos de paciente, como `PacienteCard`, `EditPacienteDialog`, `PacienteTimeline`, `CreateProntuarioDialog` e `CreateEvolucaoDialog`, ficam em:

```text
src/components/Paciente/
```

### DTOs

Os DTOs TypeScript representam os contratos usados pelo frontend.

Exemplos:

- `paciente.dto.ts`
- `profissional.dto.ts`
- `prontuario.dto.ts`
- `evolucao.dto.ts`
- `notificacao.dto.ts`

### Services

Os services centralizam a comunicação com a API via Axios.

Exemplos:

- `pacienteService.ts`
- `profissionalService.ts`
- `prontuarioService.ts`
- `notificacaoService.ts`

A configuração base do Axios fica em:

```text
src/services/apiService/apiConfig.ts
src/services/apiService/constApiConfig.ts
```

A URL base do backend é lida por variável de ambiente:

```env
VITE_CORE_API_URL=http://localhost:8080
```

### Hooks

Hooks encapsulam estado, carregamento e chamadas de API para evitar páginas muito grandes.

Exemplos:

- `useRegister`
- `useListaPacientes`
- `usePacienteDetalhe`
- `useNotificacoes`

### Utils

Funções utilitárias ficam em `src/utils/`.

Exemplos:

- cálculo de idade
- formatação de data
- helpers de prontuário

## Composition Pattern No Frontend

O frontend usa Composition Pattern para montar telas a partir de componentes pequenos e reutilizáveis.

Exemplo no cadastro:

- `register.tsx` monta a página.
- componentes de formulário ficam em `src/components/Form/`.
- botão fica em `src/components/Button.tsx`.
- campos de paciente ficam em `src/components/Paciente/PacienteFormFields.tsx`.
- lógica de estado e envio fica no hook `useRegister`.

Esse padrão evita telas monolíticas, facilita reaproveitamento e mantém responsabilidades separadas:

- página define layout e composição;
- hook controla estado e efeitos;
- service comunica com API;
- componente renderiza UI;
- DTO define contrato.

## Padrões Utilizados

### Backend

- API REST com Spring Boot.
- Use Cases para regras de aplicação.
- DTOs com records.
- Entidades JPA no domínio.
- Repositories como contratos no domínio.
- Implementações de persistência em `Infrastructure/Persistence`.
- Mappers para conversão entre domínio e DTO.
- Validações com Bean Validation e validators específicos.
- Eventos assíncronos com Spring Events, `@EventListener`, `@Async` e `@EnableAsync`.
- Tratamento de exceções com `ProblemDetail`.

### Frontend

- React com TypeScript.
- Vite para build e desenvolvimento.
- Tailwind CSS para estilos.
- Radix UI para Dialog, Select, Label e Toast.
- React Router DOM para rotas.
- Axios para HTTP.
- React Icons para ícones.
- Composition Pattern para composição de telas.
- Hooks para estado, efeitos e integração com API.
- Services para chamadas HTTP.
- DTOs para tipagem dos contratos.

## Decisões Técnicas

- O backend usa Spring Boot para manter uma API REST simples, testável e aderente ao ecossistema Java.
- A arquitetura do backend separa Controller, UseCase, Repository, Entity, DTO e Mapper para reduzir acoplamento.
- A geração de notificações usa Spring Events + `@Async`, pois o requisito proíbe RabbitMQ e a necessidade atual pode ser atendida com recursos nativos do Spring.
- A criação da notificação é tolerante a falhas: se o listener falhar, a evolução clínica continua registrada.
- O frontend usa Vite pela simplicidade e rapidez no desenvolvimento React.
- Tailwind CSS foi usado para manter consistência visual com tokens de cor e raio.
- Radix UI foi usado em componentes que precisam de comportamento acessível, como Dialog, Select e Toast.
- Axios foi centralizado em service para evitar chamadas HTTP espalhadas nas páginas.
- O Header consome notificações com polling simples a cada 30 segundos, suficiente para o escopo atual.
- O projeto usa Docker Compose para facilitar execução local com frontend, backend e PostgreSQL.

## Funcionalidades E Fluxo De Uso

### 1. Cadastrar Paciente

1. Acesse `http://localhost:3000`.
2. Clique em `Cadastros` no Header.
3. Selecione o tipo `Paciente`.
4. Preencha:
   - foto
   - nome
   - sobrenome
   - data de nascimento
   - e-mail
   - observações
5. Clique em `Enviar`.

O frontend envia os dados para a API e, quando há foto, usa envio multipart para upload da imagem.

### 2. Cadastrar Profissional

1. Acesse a página `Cadastros`.
2. Selecione o tipo `Profissional`.
3. Preencha os dados específicos do profissional, incluindo foto e especialidade.
4. Clique em `Enviar`.

### 3. Listar Pacientes

1. Clique em `Pacientes` no Header.
2. A página lista os pacientes cadastrados.
3. Cada card exibe:
   - foto
   - nome
   - idade calculada pela data de nascimento
   - observações
4. O ícone de olho abre a página de detalhe do paciente.
5. O ícone de edição abre um modal para editar os dados do paciente.

### 4. Ver Detalhes Do Paciente

1. Na listagem de pacientes, clique no ícone de olho.
2. A tela de detalhe exibe:
   - foto
   - nome
   - idade
   - e-mail
   - data de cadastro
   - observação
   - prontuários do paciente

### 5. Editar Paciente

1. Na página de detalhe, clique em `Editar informações do paciente`.
2. O modal abre com os dados atuais.
3. Atualize as informações desejadas.
4. Salve.

### 6. Adicionar Prontuário

1. Na página de detalhe do paciente, clique em `Adicionar prontuário`.
2. O modal de prontuário será aberto.
3. Preencha:
   - profissional responsável
   - queixa
   - história
   - status
4. Salve.

O campo de profissional é um select carregado com os profissionais cadastrados no sistema.

### 7. Cadastrar Evolução Clínica

1. Na seção de um prontuário, clique em `Nova evolução`.
2. Preencha a descrição da evolução.
3. Salve.

Após salvar:

- a evolução aparece na timeline do prontuário;
- o backend publica um evento assíncrono;
- uma notificação é gerada em background;
- o sino no Header passa a mostrar a notificação.

### 8. Consultar Notificações

1. Clique no sino no Header.
2. O dropdown exibe as notificações.
3. Notificações não lidas aparecem destacadas e com badge no sino.
4. Clique em uma notificação não lida para marcar como lida.

## Principais Endpoints

### Pacientes

- `GET /pacientes`
- `GET /pacientes/{id}`
- `POST /pacientes`
- `PUT /pacientes/{id}`
- `DELETE /pacientes/{id}`
- `POST /pacientes/{id}/foto`

### Profissionais

- `GET /profissionais`
- `GET /profissionais/{id}`
- `POST /profissionais`
- `PUT /profissionais/{id}`
- `DELETE /profissionais/{id}`
- `POST /profissionais/{id}/foto`

### Prontuários

- `GET /prontuarios`
- `GET /prontuarios/{id}`
- `GET /prontuarios/paciente/{pacienteId}`
- `POST /prontuarios`

### Evoluções Clínicas

- `GET /paciente-evolucoes`
- `GET /paciente-evolucoes/{id}`
- `GET /paciente-evolucoes/prontuario/{prontuarioId}`
- `POST /paciente-evolucoes`

### Notificações

- `GET /notificacoes`
- `GET /notificacoes/{id}`
- `PATCH /notificacoes/{id}/lida`

## Requisitos Implementados

- Projeto frontend em React com TypeScript e Vite.
- Tailwind CSS configurado com cores principais:
  - primária: `#171E3D`
  - secundária: `#1B2542`
  - botão: `#5EA9FF`
- Border radius padrão de `8px`.
- Radix UI para componentes acessíveis.
- Axios para comunicação HTTP.
- React Icons para ícones.
- React Router DOM para navegação.
- Cadastro de pacientes e profissionais.
- Upload de foto para paciente e profissional.
- Listagem de pacientes com foto real.
- Edição de paciente em modal.
- Página de detalhe do paciente.
- Cadastro de prontuário.
- Cadastro de evolução clínica.
- Timeline de evoluções clínicas.
- Loading spinner.
- Toast para feedback.
- Notificações assíncronas no backend.
- Sino de notificações no Header do frontend.
- Polling de notificações no frontend.
- Docker para frontend, backend e PostgreSQL.

## Melhorias Futuras

- Adicionar autenticação e autorização.
- Criar perfis de usuário, como administrador, profissional e recepção.
- Trocar polling de notificações por WebSocket ou Server-Sent Events.
- Adicionar filtros e busca na listagem de pacientes.
- Adicionar paginação nas listagens.
- Criar testes automatizados de backend e frontend.
- Adicionar validações visuais mais completas nos formulários.
- Criar tela dedicada para todas as notificações.
- Permitir arquivar ou excluir notificações.
- Adicionar auditoria para alterações em pacientes, prontuários e evoluções.
- Evoluir a estratégia de banco para migrations versionadas com Flyway ou Liquibase.
- Criar documentação OpenAPI/Swagger para a API.
