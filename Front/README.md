# Angular App

Desenvolvimento de uma pequena app em AngularJs (v. 1.6) com intuito de rever alguns tópicos sobre o assunto.

## Recursos Utilizados no Desenvolvimento:

- Angular (version 1.6.5);
- Arquitetura MVC (Model-View-Controller);
- Visual Studio Code;
- Node.Js;
- NPM;

## Executar Localmente

Caso você deseja executar o projeto na sua máquina local, basta seguir os passos abaixo:

## Começando

Para começar você deve simplesmente clonar o repositório do projeto e instalar as dependências.

### Pre-Requisitos

Antes de instalar as dependências no projeto, você precisa já ter instalado na sua máquina o Node.Js. Caso não tenha, basta realizar o download [Aqui](https://nodejs.org/en/)

### Instalando as Dependências

Você precisará de duas dependências para este projeto. A qual poderá ser obtida da seguinte maneira:

Abre o cmd (caso esteja utilizando o Windows) e digite a path do seu projeto

```
cd "C:\Users\NomeDoComputador\Documents\..."
```

Depois, basta digitar no cmd a seguinte instrução:

```
npm install
```

Ao digitar a instrução acima, automaticamente ele irá baixar todas as dependências listadas no arquivo package.Json e mais o bower.Json para o projeto. Criando assim, duas pastas no projeto: 

* `node_modules` - que contêm os packages do npm que precisará para o projeto.
* `bower_components` - contêm os arquivos do angular.

### Executando a Aplicação

Bom, agora na mesma tela do cmd, basta iniciar o server para o projeto ser executado localmente.

```
npm start
```

Agora, abre a página da aplicação em `http://localhost:9000`. E pronto a aplicação será executada de maneira local na sua máquina.
