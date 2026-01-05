# Cadastro Pessoal 📇

Um sistema simples de cadastro de pessoas com interface gráfica, desenvolvido para aprendizado de Java Swing e manipulação de dados em JSON. 

## 📋 Descrição do Projeto

Este projeto é uma **agenda de contatos pessoal** com interface gráfica que permite gerenciar informações de pessoas, incluindo nome, data de nascimento, idade e CPF. Foi desenvolvido como projeto de estudo para praticar: 

- **Java Swing**:  Criação de interfaces gráficas
- **Serialização JSON**: Persistência de dados com Gson
- **Arquitetura MVC**: Organização do código em Model-View-Controller
- **Validações**: Implementação de validadores para CPF e datas

## ✨ Funcionalidades

- ✅ **Adicionar contatos** com nome, data de nascimento e CPF
- ✅ **Remover contatos** da lista
- ✅ **Editar contatos** existentes
- ✅ **Exibir informações detalhadas** ao dar duplo clique na tabela
- ✅ **Cálculo automático de idade** baseado na data de nascimento
- ✅ **Validação de CPF** com algoritmo verificador completo
- ✅ **Validação de data** de nascimento (não permite datas futuras)
- ✅ **Máscaras de entrada** formatadas para data e CPF
- ✅ **Persistência automática** em arquivo JSON
- ✅ **Tratamento de erros** com exceções personalizadas

## 🖼️ Interface

![Interface do Sistema](image1)

A interface é dividida em duas partes: 
- **Esquerda**: Formulário de entrada com campos para Nome, Data de Nascimento e CPF
- **Direita**:  Tabela exibindo todos os contatos cadastrados com suas informações

## 🚀 Como Instalar/Usar

### Pré-requisitos

- **Java JDK 21** ou superior
- **Maven** (para gerenciamento de dependências e build)

### Compilação com Maven

```bash
mvn clean install
```

Isso gerará um arquivo JAR executável na pasta `target/`.

### Execução

```bash
java -jar target/CadastroPessoal-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Ou, usando Maven diretamente:

```bash
mvn exec:java
```

### Como Usar

1. **Adicionar contato**:
   - Preencha os campos Nome, Data de Nascimento e CPF
   - Clique no botão "Adicionar"
   - O contato aparecerá na tabela à direita

2. **Remover contato**:
   - Selecione um contato na tabela
   - Clique no botão "Remover"

3. **Editar contato**:
   - Selecione um contato na tabela
   - Os dados aparecerão nos campos de entrada
   - Modifique as informações desejadas
   - Clique no botão "Editar"

4. **Ver detalhes**:
   - Dê duplo clique em qualquer linha da tabela para ver informações detalhadas

**Formatos de entrada:**
- **Data**:  `DD/MM/AAAA` (exemplo: 11/09/2004)
- **CPF**: `XXX.XXX.XXX-XX` (exemplo: 123.456.789-09)

### Persistência de Dados

Os dados são salvos automaticamente em `data/pessoas.json` após cada operação (adicionar, remover ou editar). O arquivo é criado automaticamente na primeira execução.

## 🛠️ Tecnologias Utilizadas

- **Java 21**:  Linguagem de programação principal
- **Java Swing**: Biblioteca para interface gráfica
- **Gson 2.11.0**: Serialização e deserialização de objetos em JSON
- **MigLayout 5.2**: Gerenciador de layout para Swing
- **Maven**: Gerenciamento de dependências e build

## 📁 Estrutura do Projeto

```
CadastroPessoal/
├── pom.xml                                      # Configuração Maven
├── data/
│   └── pessoas.json                             # Dados persistidos (gerado em runtime)
└── src/
    └── main/
        └── java/
            └── cadastropessoal/
                ├── CadastroPessoal.java         # Classe principal (main)
                ├── model/                        # Modelos de dados
                │   ├── Pessoa.java              # Classe Pessoa
                │   ├── Cpf.java                 # Classe CPF com validação
                │   └── Data.java                # Classe Data
                ├── view/                         # Interface gráfica
                │   └── Tela.java                # Tela principal Swing
                ├── controller/                   # Controladores de ações
                │   ├── Adicionar.java           # Action listener para adicionar
                │   ├── Remover.java             # Action listener para remover
                │   ├── Editar.java              # Action listener para editar
                │   └── ExibirInformacoes.java   # Mouse listener para exibir detalhes
                ├── persistence/                  # Camada de persistência
                │   ├── PessoaPersistence.java   # Gerenciamento de salvamento/leitura
                │   └── Archive.java             # Utilitário para I/O de arquivos
                └── exception/                    # Exceções personalizadas
                    ├── CpfException.java        # Exceção para CPF inválido
                    ├── EmptyStrException.java   # Exceção para campos vazios
                    └── InvalidDataException.java # Exceção para data inválida
```

## 🎯 Arquitetura MVC

O projeto segue o padrão **Model-View-Controller**:

- **Model** (`model/`): Classes de domínio com lógica de negócio e validações
  - Validação de CPF usando algoritmo verificador
  - Cálculo automático de idade
  - Validação de datas

- **View** (`view/`): Interface gráfica com Swing
  - Formulário de entrada com máscaras
  - Tabela para visualização de dados
  - Botões de ação

- **Controller** (`controller/`): Implementação de listeners para eventos
  - ActionListener para botões
  - MouseListener para interações com a tabela

- **Persistence** (`persistence/`): Camada de persistência
  - Serialização/deserialização JSON com Gson
  - Gerenciamento de arquivos

## 🔍 Validações Implementadas

### Validação de CPF

O sistema implementa o algoritmo completo de validação de CPF brasileiro, verificando:
- Formato correto (11 dígitos)
- Dígitos verificadores (calculados usando o algoritmo oficial)

### Validação de Data

- Data não pode ser futura
- Formato deve ser DD/MM/AAAA
- Validação básica de limites (dia 1-30, mês 1-12)

### Validação de Campos

- Campos não podem estar vazios
- Tratamento de exceções com mensagens de erro apropriadas

## 📚 Dependências

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.11.0</version>
</dependency>

<dependency>
    <groupId>com.miglayout</groupId>
    <artifactId>miglayout-swing</artifactId>
    <version>5.2</version>
</dependency>
```

---

⭐ Desenvolvido por [filipemvidal](https://github.com/filipemvidal)
